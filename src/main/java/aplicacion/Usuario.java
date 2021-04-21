package aplicacion;

public class Usuario {
    private String idUsuario;
    private String clave;
    private boolean autorizado;
    private boolean solicitadobaja;

    public Usuario(String idUsuario, String clave, boolean autorizado, boolean solicitadobaja) {
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.autorizado = autorizado;
        this.solicitadobaja = solicitadobaja;
    }

    public Usuario(String idUsuario, boolean solicitadobaja, boolean autorizado) {
        this.idUsuario = idUsuario;
        this.clave = null;
        this.solicitadobaja = solicitadobaja;
        this.autorizado = autorizado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isSolicitadobaja() {
        return solicitadobaja;
    }

    public void setSolicitadobaja(boolean solicitadobaja) {
        this.solicitadobaja = solicitadobaja;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }
}
