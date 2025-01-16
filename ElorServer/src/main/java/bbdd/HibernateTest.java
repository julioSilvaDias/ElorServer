package bbdd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bbdd.pojos.Alumno;
import bbdd.pojos.Asignatura;
import bbdd.pojos.Ciclo;
import bbdd.pojos.Cita;
import bbdd.pojos.CursosExternos;
import bbdd.pojos.Horario;
import bbdd.pojos.Profesor;
import bbdd.pojos.Reunion;
import bbdd.pojos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class HibernateTest {
	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			String hql = "FROM Reunion";
			List<Reunion> usuarios = session.createQuery(hql).list();

			for (Reunion usuario : usuarios) {
				System.out.println(usuario.getId());
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
