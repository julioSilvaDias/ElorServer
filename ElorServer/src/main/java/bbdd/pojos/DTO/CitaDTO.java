package bbdd.pojos.DTO;

import bbdd.pojos.Cita;
import bbdd.pojos.CitaId;

public class CitaDTO {
    private CitaId id;
    private UsuarioDTO usuarioPropietario;
    private UsuarioDTO usuarioDestinatario;

    public CitaDTO(Cita cita) {
        this.id = cita.getId();
        this.usuarioPropietario = new UsuarioDTO(cita.getUsuarioPropietario());
        this.usuarioDestinatario = new UsuarioDTO(cita.getUsuarioDestinatario());
    }

    public CitaId getId() {
        return id;
    }

    public void setId(CitaId id) {
        this.id = id;
    }

    public UsuarioDTO getUsuarioPropietario() {
        return usuarioPropietario;
    }

    public void setUsuarioPropietario(UsuarioDTO usuarioPropietario) {
        this.usuarioPropietario = usuarioPropietario;
    }

    public UsuarioDTO getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(UsuarioDTO usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }
}

