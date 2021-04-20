/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;
import java.sql.Date; 

/**
 *
 * @author Usuario
 */
public class Historial {
    private String empresa; 
    private String comprador; 
    private Date fecha; 
    private Integer cantidad; 
    private Float precio;
    private String tipo;

    public Historial(String empresa, String comprador, Date fecha, Integer cantidad, Float precio, String tipo) {
        this.empresa = empresa;
        this.comprador = comprador;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipo = tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
}
