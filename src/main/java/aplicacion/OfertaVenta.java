package aplicacion;

import java.sql.Date;
import java.sql.Timestamp;

public class OfertaVenta {
    private String vendedor;
    private String empresa;
    private Timestamp fecha;
    private Integer numParticipaciones;
    private float precio;

    public OfertaVenta(String usuario, String empresa, Timestamp fecha, Integer numParticipaciones, float precio) {
        this.vendedor = usuario;
        this.empresa = empresa;
        this.fecha = fecha;
        this.numParticipaciones = numParticipaciones;
        this.precio = precio;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Integer getNumParticipaciones() {
        return numParticipaciones;
    }

    public void setNumParticipaciones(Integer numParticipaciones) {
        this.numParticipaciones = numParticipaciones;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
