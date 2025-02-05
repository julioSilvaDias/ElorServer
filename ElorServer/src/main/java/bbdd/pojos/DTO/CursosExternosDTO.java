package bbdd.pojos.DTO;

import java.sql.Date;

import bbdd.pojos.CursosExternos;

public class CursosExternosDTO {

	private Integer id;
	private String titulo;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String horario;
	private String lugar;
	private String contacto;
	private String cicloFormativo;
	private Integer curso;
	private String asignatura;

	public CursosExternosDTO() {
	}

	public CursosExternosDTO(Integer id, String titulo, String descripcion, Date fechaInicio, Date fechaFin,
			String horario, String lugar, String contacto, String cicloFormativo, Integer curso, String asignatura) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.horario = horario;
		this.lugar = lugar;
		this.contacto = contacto;
		this.cicloFormativo = cicloFormativo;
		this.curso = curso;
		this.asignatura = asignatura;
	}

	public CursosExternosDTO(CursosExternos cursoExterno) {
		this.id = cursoExterno.getId();
		this.titulo = cursoExterno.getTitulo();
		this.descripcion = cursoExterno.getDescripcion();
		this.fechaInicio = cursoExterno.getFechaInicio();
		this.fechaFin = cursoExterno.getFechaFin();
		this.horario = cursoExterno.getHorario();
		this.lugar = cursoExterno.getLugar();
		this.contacto = cursoExterno.getContacto();
		this.cicloFormativo = cursoExterno.getCicloFormativo();
		this.curso = cursoExterno.getCurso();
		this.asignatura = cursoExterno.getAsignatura();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getCicloFormativo() {
		return cicloFormativo;
	}

	public void setCicloFormativo(String cicloFormativo) {
		this.cicloFormativo = cicloFormativo;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
}