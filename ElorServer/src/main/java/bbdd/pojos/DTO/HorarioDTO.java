package bbdd.pojos.DTO;

import java.util.Objects;

public class HorarioDTO {
	int id;
	int id_usuario;
	String dia;
	String asignatura;
	String tipo;
	String aula;

	@Override
	public int hashCode() {
		return Objects.hash(asignatura, aula, dia, id, id_usuario, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorarioDTO other = (HorarioDTO) obj;
		return Objects.equals(asignatura, other.asignatura) && Objects.equals(aula, other.aula)
				&& Objects.equals(dia, other.dia) && id == other.id && id_usuario == other.id_usuario
				&& Objects.equals(tipo, other.tipo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	@Override
	public String toString() {
		return "HorarioDTO [id=" + id + ", id_usuario=" + id_usuario + ", dia=" + dia + ", asignatura=" + asignatura
				+ ", tipo=" + tipo + ", aula=" + aula + "]";
	}

}
