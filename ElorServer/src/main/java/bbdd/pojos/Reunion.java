package bbdd.pojos;
// Generated 15 ene 2025, 23:24:11 by Hibernate Tools 6.5.1.Final

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Reunion implements java.io.Serializable {

    private static final long serialVersionUID = -8372831231721733675L;
    private int id;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private String titulo; 
    private String estado;
    private String comentario;
    private Integer numAceptadas = 0;
    private String aula;
    private Set citas = new HashSet(0);

    public Reunion() {
    }

    public Reunion(int id) {
        this.id = id;
    }

    public Reunion(
        int id, 
        Timestamp fechaHoraInicio, 
        Timestamp fechaHoraFin, 
        String titulo,
        String estado, 
        String comentario,
        Integer numAceptadas, 
        String aula, 
        Set citas
    ) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.titulo = titulo; // Inicializar nuevo campo
        this.estado = estado;
        this.comentario = comentario;
        this.numAceptadas = numAceptadas;
        this.aula = aula;
        this.citas = citas;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
	public Integer getNumAceptadas() {
		return this.numAceptadas;
	}

	public void setNumAceptadas(Integer numAceptadas) {
		this.numAceptadas = numAceptadas;
	}

	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaHoraInicio() {
		return this.fechaHoraInicio;
	}

	public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public Timestamp getFechaHoraFin() {
		return this.fechaHoraFin;
	}

	public void setFechaHoraFin(Timestamp fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Set getCitas() {
		return this.citas;
	}

	public void setCitas(Set citas) {
		this.citas = citas;
	}

}