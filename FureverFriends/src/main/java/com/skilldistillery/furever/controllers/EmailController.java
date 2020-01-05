package com.skilldistillery.furever.controllers;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.furever.services.EmailServiceImpl;

@RestController
@RequestMapping("api/mail")
@CrossOrigin({"*", "http://localhost:4400"})
public class EmailController {
	
	private String username = "fureverfriendsemail@gmail.com";
	private String password = "Fureverfriends123";

	@PostMapping
	public String sendEmail(@RequestBody EmailServiceImpl emailmessage, HttpServletResponse response) throws AddressException, MessagingException {
		sendMail(emailmessage);
		return "Email sent successfully";
	}
	
	private void sendMail(EmailServiceImpl emailmessage) throws AddressException, MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailmessage.getToAddress()));
		msg.setSubject(emailmessage.getSubject());
		msg.setContent(emailmessage.getBody(), "text/html");
		msg.setSentDate(new Date());
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(emailmessage.getBody(), "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		msg.setContent(multipart);
		// sends the e-mail
		Transport.send(msg);
	}
}
