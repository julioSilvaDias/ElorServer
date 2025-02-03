package bbdd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.elor.server.elorServer.socketIO.EmailService;

import bbdd.pojos.Usuario;

public class GestorUsuario {
	
	private EmailService emailService;
	
	public GestorUsuario(EmailService emailService) {
		this.emailService = emailService;
	}
	
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


	public Usuario obtenerUsuarioPorEmail(String email) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Usuario ret = null;

        try {
            session = sessionFactory.openSession();
            String hql = "FROM Usuario e WHERE e.email = :email";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("email", email);

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
	
	public String generarNuevaClave() {
        return "ElorrietaNueva";
    }
	
	public boolean restablecerClave(String email) {
        Usuario usuario = obtenerUsuarioPorEmail(email);
        if (usuario != null) {
            String nuevaContraseña = generarNuevaClave();

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = null;
            Transaction transaction = null;

            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();

                usuario.setPassword(nuevaContraseña);
                session.update(usuario);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return false;
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            
            /**
             * Para mandar el correo
             */
            try {
                String subject = "Restablecimiento de contraseña";
                String message = "Su nueva contraseña es: " + nuevaContraseña;
                emailService.sendMail(email, subject, message);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
	
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
