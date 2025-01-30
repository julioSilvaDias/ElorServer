package bbdd.pojos.DTO;

import bbdd.pojos.Usuario;

public class UsuarioDTO {

	private Integer id;
	private String login;
	private String password;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private String tipoUsuario;
	private String cicloFormativo;
	private Integer curso;
	private Integer duales;
	private String dni;
	private String telefono1;
	private String telefono2;

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.password = usuario.getPassword();
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellidos();
		this.email = usuario.getEmail();
		this.telefono = usuario.getTelefono();
		this.tipoUsuario = usuario.getTipoUsuario();
		this.cicloFormativo = usuario.getCicloFormativo();
		this.curso = usuario.getCurso();
		this.duales = usuario.getDuales();
		this.dni = usuario.getDni();
		this.telefono1 = usuario.getTelefono1();
		this.telefono2 = usuario.getTelefono2();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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
	
	public Integer getDuales() {
		return duales;
	}

	public void setDuales(Integer duales) {
		this.duales = duales;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
}
