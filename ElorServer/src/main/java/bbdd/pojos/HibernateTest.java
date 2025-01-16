package bbdd.pojos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bbdd.HibernateUtil;

import java.util.List;

public class HibernateTest {
    public static void main(String[] args) {
    	
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrir una sesión
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Realizar una consulta (Ejemplo: listar usuarios)
            List usuarios = session.createQuery("FROM Usuario").list();

            // Mostrar resultados
            for (Object usuario : usuarios) {
                System.out.println(usuario);
            }

            // Confirmar la transacción
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

