package bbdd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	public void changePassword(String username, String password) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String hql = "update Usuario e set e.password = :password WHERE e.login = :login";
			Query<?> query = session.createQuery(hql);
			query.setParameter("login", username);
			query.setParameter("password", password);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}