package bbdd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bbdd.pojos.Reunion;
import bbdd.pojos.DTO.ReunionDTO;

public class GestorReunion {
	public List<ReunionDTO> getReunionesByUsuarioId(int usuarioId) {
	    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    Session session = null;
	    List<ReunionDTO> reunionesDTO = new ArrayList<>();

	    try {
	        session = sessionFactory.openSession();
	        String hql = "SELECT DISTINCT c.reunion FROM Cita c " +
	                     "LEFT JOIN FETCH c.reunion.citas " + 
	                     "WHERE c.usuarioPropietario.id = :usuarioId " +
	                     "OR c.usuarioDestinatario.id = :usuarioId";
	        Query<Reunion> query = session.createQuery(hql, Reunion.class);
	        query.setParameter("usuarioId", usuarioId);

	        List<Reunion> reuniones = query.list();

	        reunionesDTO = reuniones.stream()
	                .map(ReunionDTO::new)
	                .collect(Collectors.toList());

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close(); // Cerramos la sesión después de convertir a DTO
	        }
	    }

	    return reunionesDTO;
	}

}
