package com.fs.cta.email;

public interface EmailService {
	String sendHtmlMessage(String to, String subject, String text);
}
