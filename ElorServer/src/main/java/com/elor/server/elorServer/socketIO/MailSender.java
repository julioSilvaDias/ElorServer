package com.elor.server.elorServer.socketIO;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;

public class MailSender {

	public static boolean sendEmail(String recipient, String newPassword) {
        String toEmail = recipient;
        String subject = "Restablecimiento de Contraseña";
        String body = "Tu nueva contraseña es: " + newPassword;
        
        String host = "smtp.gmail.com";
        String senderEmail = "";
        String senderPassword = "";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(senderEmail);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);
            
            Transport.send(message);
            System.out.println("Correo enviado con éxito");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
		
}