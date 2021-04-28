package aplicacion;

import java.sql.Timestamp;

public class OfertaVenta {
    private String vendedor;
    private String empresa;
    private Timestamp fecha;
    private Integer numParticipaciones;
    private float precio;
    private float comision; 

    public OfertaVenta(String usuario, String empresa, Timestamp fecha, Integer numParticipaciones, float precio,float comision) {
        this.vendedor = usuario;
        this.empresa = empresa;
        this.fecha = fecha;
        this.numParticipaciones = numParticipaciones;
        this.precio = precio;
        this.comision=comision; 
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

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
    
    
}
