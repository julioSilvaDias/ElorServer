package bbdd.pojos;

public class Cita implements java.io.Serializable {

    private CitaId id;
    private Reunion reunion;
    private Usuario usuarioPropietario; 
    private Usuario usuarioDestinatario; 

    public Cita() {}

    public Cita(CitaId id, Reunion reunion, Usuario usuarioPropietario, Usuario usuarioDestinatario) {
        this.id = id;
        this.reunion = reunion;
        this.usuarioPropietario = usuarioPropietario;
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public CitaId getId() {
        return this.id;
    }

    public void setId(CitaId id) {
        this.id = id;
    }

    public Reunion getReunion() {
        return this.reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    public Usuario getUsuarioPropietario() {
        return this.usuarioPropietario;
    }

    public void setUsuarioPropietario(Usuario usuarioPropietario) {
        this.usuarioPropietario = usuarioPropietario;
    }

    public Usuario getUsuarioDestinatario() {
        return this.usuarioDestinatario;
    }

    public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }
}