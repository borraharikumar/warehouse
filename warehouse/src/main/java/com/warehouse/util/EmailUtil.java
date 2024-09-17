package com.warehouse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmai(String to, String subject, String text,
						 String[] cc, String[] bcc, MultipartFile file) throws MessagingException { 
		//1. Create empty message - MimeMessage (MIME = Multi-purpouse Internet Mail Extension)
		MimeMessage message = mailSender.createMimeMessage();
		
		//2. Use MimeMessageHelper class to set details to MimeMessage
		MimeMessageHelper helper = new MimeMessageHelper(message, file!=null);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		if(cc!=null) helper.setCc(cc);
		if(bcc!=null) helper.setBcc(bcc);
		if(file!=null) helper.addAttachment(file.getOriginalFilename(), file);
		//3. Send Message
		mailSender.send(message);
	}
	
	public void sendEmai(String to, String subject, String text) throws MessagingException { 
		sendEmai(to, subject, text, null, null, null);
	}

}
