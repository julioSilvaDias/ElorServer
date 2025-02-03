package com.elor.server.elorServer.socketIO;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    private String user;
    private String password;
    private String smtpHost;
    private int smtpPort;

    public EmailService(String user, String password, String smtpHost, int smtpPort) {
        this.user = user;
        this.password = password;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public void sendMail(String receiver, String subject, String text) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.trust", smtpHost);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        message.setSubject(subject);
        message.setText(text); // Texto plano

        Transport.send(message);
        System.out.println("Correo enviado con éxito a " + receiver);
    }
}
	/*public static boolean sendEmail(String recipient, String newPassword) {
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
    }*/