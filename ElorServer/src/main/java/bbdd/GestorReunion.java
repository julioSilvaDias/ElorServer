package bbdd;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bbdd.pojos.Reunion;

public class GestorReunion {
	public List<Reunion> getReunionesByUsuarioId(int usuarioId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		List<Reunion> ret = null;

		try {
			session = sessionFactory.openSession();
			String hql = "SELECT DISTINCT c.reunion FROM Cita c " + "WHERE c.usuarioByIdAlumno.id = :usuarioId "
					+ "OR c.usuarioByIdProfesor.id = :usuarioId";
			Query<Reunion> query = session.createQuery(hql, Reunion.class);
			query.setParameter("usuarioId", usuarioId);

			ret = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return ret;

	}
}
