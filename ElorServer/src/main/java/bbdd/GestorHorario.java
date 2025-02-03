package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import bbdd.pojos.DTO.HorarioDTO;
import bbdd.utils.Utils;

public class GestorHorario {
	public List<HorarioDTO> getHorarioById(int id) {
		List<HorarioDTO> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(Utils.DRIVER);
			connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASS);
			String sql = "SELECT * FROM Horario WHERE id_usuario = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			ret = new ArrayList<HorarioDTO>();
			while (resultSet.next()) {
				HorarioDTO horarioDTO = new HorarioDTO();

				horarioDTO.setId(resultSet.getInt("id"));
				horarioDTO.setId_usuario(resultSet.getInt("id_usuario"));
				horarioDTO.setDia(resultSet.getString("dia"));
				horarioDTO.setAsignatura(resultSet.getString("asignatura"));
				horarioDTO.setTipo(resultSet.getString("tipo"));
				horarioDTO.setAula(resultSet.getString("aula"));
				horarioDTO.setSemana(resultSet.getInt("semana"));
				horarioDTO.setHora(resultSet.getString("hora"));

				ret.add(horarioDTO);
			}
		} catch (SQLException sqle) {
			System.out.println("ERROR EN LA BASE DE DATOS: " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR GENERICO: " + e.getMessage());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {

			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {

			}
			;
		}

		return ret;
	}
}
