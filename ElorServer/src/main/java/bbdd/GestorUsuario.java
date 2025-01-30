package bbdd;

import java.net.PasswordAuthentication;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import bbdd.pojos.Usuario;

public class GestorUsuario {
	public Usuario login(String name, String password) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Usuario ret = null;

		try {
			session = sessionFactory.openSession();
			String hql = "FROM Usuario e WHERE e.login = :login AND e.password = :password";
			Query<Usuario> query = session.createQuery(hql, Usuario.class);
			query.setParameter("login", name);
			query.setParameter("password", password);

			ret = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			return ret;
		}
	}
/*
	private boolean sendPasswordResetEmail(String username) {
        // Configuración para el envío del correo
        final String senderEmail = "tu_correo@gmail.com"; // Cambiar por tu dirección de correo
        final String senderPassword = "tu_contraseña";   // Cambiar por la contraseña de tu correo
        final String smtpHost = "smtp.gmail.com";        // Servidor SMTP de Gmail
        final int smtpPort = 587;                        // Puerto SMTP de Gmail

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        Session mailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("destinatario@example.com")); // Cambiar por el correo del destinatario
            message.setSubject("Restablecimiento de contraseña");

            String resetLink = "https://example.com/reset-password?user=" + username;
            String body = "Hola, " + username + ".\n\nHaz clic en el siguiente enlace para restablecer tu contraseña:\n" + resetLink;
            message.setText(body);

            Transport.send(message);
            System.out.println("Correo de restablecimiento enviado a " + username);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }*/


	private Usuario obtenerUsuarioPorUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario getUserId(String userName) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Usuario ret = null;

		try {
			session = sessionFactory.openSession();
			String hql = "FROM Usuario e WHERE e.login = :login";
			Query<Usuario> query = session.createQuery(hql, Usuario.class);
			query.setParameter("login", userName);
			ret = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();

			}
			return ret;
		}

	}

}
