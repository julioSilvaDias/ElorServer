package bbdd.pojos.DTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import bbdd.pojos.Cita;
import bbdd.pojos.Reunion;

public class ReunionDTO {
    private int id;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private String titulo; 
    private String estado;
    private String comentario;
    private Integer numAceptadas;
    private String aula;
    private List<CitaDTO> citas;

    public ReunionDTO(int id, Timestamp fechaHoraInicio, Timestamp fechaHoraFin, String titulo, String estado, 
                      String comentario, Integer numAceptadas, String aula, List<CitaDTO> citas) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.titulo = titulo; 
        this.estado = estado;
        this.comentario = comentario;
        this.numAceptadas = numAceptadas;
        this.aula = aula;
        this.citas = citas;
    }

    // Constructor que recibe un objeto Reunion
    public ReunionDTO(Reunion reunion) {
        this.id = reunion.getId();
        this.fechaHoraInicio = reunion.getFechaHoraInicio();
        this.fechaHoraFin = reunion.getFechaHoraFin();
        this.titulo = reunion.getTitulo(); // Obtener el nuevo campo
        this.estado = reunion.getEstado();
        this.comentario = reunion.getComentario();
        this.numAceptadas = reunion.getNumAceptadas();
        this.aula = reunion.getAula();
        this.citas = reunion.getCitas() != null ? 
                     ((Set<Cita>) reunion.getCitas()).stream()
                     .map(CitaDTO::new)
                     .collect(Collectors.toList()) : null;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Timestamp getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Timestamp fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getNumAceptadas() {
        return numAceptadas;
    }

    public void setNumAceptadas(Integer numAceptadas) {
        this.numAceptadas = numAceptadas;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public List<CitaDTO> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaDTO> citas) {
        this.citas = citas;
    }
}