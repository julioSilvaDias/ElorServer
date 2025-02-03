package bbdd.pojos;

public class CitaId implements java.io.Serializable {

    private static final long serialVersionUID = -3259913589188567277L;
    private int idPropietario; // Antes idAlumno
    private int idDestinatario; // Antes idProfesor
    private int idReunion;

    public CitaId() {}

    public CitaId(int idPropietario, int idDestinatario, int idReunion) {
        this.idPropietario = idPropietario;
        this.idDestinatario = idDestinatario;
        this.idReunion = idReunion;
    }

    public int getIdPropietario() {
        return this.idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getIdDestinatario() {
        return this.idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public int getIdReunion() {
        return this.idReunion;
    }

    public void setIdReunion(int idReunion) {
        this.idReunion = idReunion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitaId citaId = (CitaId) o;

        if (idPropietario != citaId.idPropietario) return false;
        if (idDestinatario != citaId.idDestinatario) return false;
        return idReunion == citaId.idReunion;
    }

    @Override
    public int hashCode() {
        int result = idPropietario;
        result = 31 * result + idDestinatario;
        result = 31 * result + idReunion;
        return result;
    }
}