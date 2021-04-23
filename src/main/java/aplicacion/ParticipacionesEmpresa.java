package aplicacion;

public class ParticipacionesEmpresa {
    private String usuario; 
    private String empresa; 
    private Integer numeroparticipaciones; 

    public ParticipacionesEmpresa(String usuario, String empresa) {
        this.usuario = usuario;
        this.empresa = empresa;
        this.numeroparticipaciones=null; 
    }

    public ParticipacionesEmpresa(String usuario, String empresa, Integer numeroparticipaciones) {
        this.usuario = usuario;
        this.empresa = empresa;
        this.numeroparticipaciones = numeroparticipaciones;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getNumeroparticipaciones() {
        return numeroparticipaciones;
    }

    public void setNumeroparticipaciones(Integer numeroparticipaciones) {
        this.numeroparticipaciones = numeroparticipaciones;
    }
    
    
}
