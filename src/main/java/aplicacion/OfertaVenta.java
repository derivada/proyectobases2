package aplicacion;

import java.sql.Date;

public class OfertaVenta {
    private String usuario;
    private String empresa;
    private Date fecha;
    private Integer numParticipaciones;
    private Double precio;

    public OfertaVenta(String usuario, String empresa, Date fecha, Integer numParticipaciones, Double precio) {
        this.usuario = usuario;
        this.empresa = empresa;
        this.fecha = fecha;
        this.numParticipaciones = numParticipaciones;
        this.precio = precio;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumParticipaciones() {
        return numParticipaciones;
    }

    public void setNumParticipaciones(Integer numParticipaciones) {
        this.numParticipaciones = numParticipaciones;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
