package com.fs.cta.bom.listener;

import java.util.Optional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fs.cta.bom.Marketing;
import com.fs.cta.db.MarketingRepository;
import com.fs.cta.dto.CustomerDTO;
import com.fs.cta.email.EmailService;

@Component
public class MQListener {

	@Autowired
	private MarketingRepository marketingRepository;
	
	@Autowired 
	private EmailService emailService;
	
	@Value("${cta.email.subject}")
	private String subject;
	
	@Value("${cta.mkt.id}")
	private String sMktId;
	
	@JmsListener(destination = "${cta.mq.queue-name}")
	public void receiveMessage(final Message xmlMessage) throws JMSException, JsonMappingException, JsonProcessingException{
		
		
		try {
		String messageData = null;
		if(xmlMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)xmlMessage;
			messageData = textMessage.getText();
			System.out.println("CTA MQ received message: " + messageData);
			
			CustomerDTO customerDTO = extract(messageData);
			
            if ( customerDTO != null && sMktId != null && sMktId.compareTo("") > 0) {
              Long lMktId = Long.valueOf(sMktId);
			  Optional<Marketing> mkt = marketingRepository.findById(lMktId);
			  if (mkt.isPresent()) {
				  String template = mkt.get().getTemplate();
				if (template == null) {
						System.out.println("CTA MQ not found sent template: " + template);
						return;
				  }
				  if (customerDTO.getEmail() == null) {
						System.out.println("CTA MQ not found email message to send : " + customerDTO.getEmail());
						return;
				  }
				 
				  template = StringUtils.replace(template.toString(), "<customer>", customerDTO.getName());
				  template = StringUtils.replace(template.toString(), "<identification>", customerDTO.getId().toString());
				  template = StringUtils.replace(template.toString(), "<email>", customerDTO.getEmail());
				  
				  emailService.sendHtmlMessage(customerDTO.getEmail(), subject, template.toString());
				  
				  System.out.println("Message response: " + template);
				}
			} else {
            	System.err.println("CTA MQ error in the config properties: " + messageData);
            }
            System.out.println("CTA MQ finished process!");
		}
		} catch (Exception e) {
            e.printStackTrace();
		}
	}

	private CustomerDTO extract(String messageData) throws JsonProcessingException, JsonMappingException {
		
		ObjectMapper objectMapper = new XmlMapper();
		CustomerDTO customerDTO = objectMapper.readValue(messageData, CustomerDTO.class);
		return customerDTO;
	}
}
