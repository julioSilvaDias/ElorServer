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

}
