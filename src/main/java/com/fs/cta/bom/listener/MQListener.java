package com.fs.cta.bom.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MQListener {
	@JmsListener(destination = "${cta.mq.queue-name}")
	public void receiveMessage(final Message jsonMessage) throws JMSException{
		String messageData = null;
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			System.out.println("CTA MQ received message: " + messageData);
		}
	}
}
