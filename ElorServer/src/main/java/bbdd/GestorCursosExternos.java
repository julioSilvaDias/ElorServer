package bbdd;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import bbdd.pojos.CursosExternos;
import bbdd.pojos.DTO.CursosExternosDTO;

public class GestorCursosExternos {

	public List<CursosExternosDTO> getAllCursos() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		List<CursosExternosDTO> ret = null;

		try {
			session = sessionFactory.openSession();
			String hql = "FROM CursosExternos";
			Query<CursosExternos> query = session.createQuery(hql, CursosExternos.class);
			
			List<CursosExternos> cursosExternos = query.list();
			ret = new ArrayList<CursosExternosDTO>();
			ret = cursosExternos.stream().map(CursosExternosDTO::new).collect(Collectors.toList());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return ret;
	}
}
