package bbdd;

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
		}
		return ret;
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
		}
		return ret;
	}

	public void changePassword(String username, String password) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String hql = "update Usuario e set e.password = :password WHERE e.login = :login";
			Query<?> query = session.createQuery(hql);
			query.setParameter("login", username);
			query.setParameter("password", password);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void updateUser(String user, String name, String surname, String dni, String email, String telefono,
			String telefono2, String username) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String hql = "update Usuario e set e.login = :login, e.nombre = :name, e.apellidos = :surname, e.email = :email, e.dni = :dni,"
					+ "e.telefono1 = :telefono, e.telefono2 = :telefono2 WHERE e.login = :login2";
			Query<?> query = session.createQuery(hql);
			query.setParameter("login", user);
			query.setParameter("name", name);
			query.setParameter("surname", surname);
			query.setParameter("email", email);
			query.setParameter("dni", dni);
			query.setParameter("telefono", telefono);
			query.setParameter("telefono2", telefono2);
			query.setParameter("login2", username);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}