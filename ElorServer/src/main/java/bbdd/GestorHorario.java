package bbdd;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bbdd.pojos.Horario;

public class GestorHorario {
	public List<Horario> getHorarioById(int id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		List<Horario> ret = null;

		try {
			session = sessionFactory.openSession();
			String hql = "FROM Horario h WHERE h.usuario.id = :idUsuario";
			Query query = session.createQuery(hql, Horario.class);
			query.setParameter("idUsuario", id);
			
			ret = query.list();
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
