package com.fs.cta.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	public String username;
	
	@Override
	public String sendHtmlMessage(String to, String subject, String text) {
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(username);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			mailSender.send(mail);
			return "Email sucessfully sent to :" + to;
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro to send html e-mail";
		}
    }	
}
