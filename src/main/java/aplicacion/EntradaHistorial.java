package aplicacion;

import java.sql.Timestamp;

public class EntradaHistorial {
    private String empresa;
    private String comprador;
    private Timestamp fecha;
    private Integer cantidad;
    private Float precio;
    private TipoEntradaHistorial tipo;

    public EntradaHistorial(String empresa, String comprador, Timestamp fecha, Integer cantidad, Float precio, String tipo) {
        this.empresa = empresa;
        this.comprador = comprador;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
        for (TipoEntradaHistorial t : TipoEntradaHistorial.values())
            if (t.toString().equals(tipo))
                this.tipo = t;

        if (this.tipo == null)
            throw new IllegalArgumentException("Tipo no válido al crear entrada del historial!");
    }

    public EntradaHistorial(String empresa, String comprador, Timestamp fecha, Integer cantidad, Float precio, TipoEntradaHistorial tipo) {
        this.empresa = empresa;
        this.comprador = comprador;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipo = tipo;
    }

    public void setTipo(TipoEntradaHistorial tipo) {
        this.tipo = tipo;
    }

    public TipoEntradaHistorial getTipo() {
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
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

    public enum TipoEntradaHistorial {
        COMPRA, VENTA, EMISION, BENEFICIOS, BAJA, PAGO;

        @Override
        public String toString() {
            switch (this) {
                case COMPRA:
                    return "Compra";
                case VENTA:
                    return "Venta";
                case EMISION:
                    return "Emision";
                case BENEFICIOS:
                    return "Beneficios";
                case BAJA:
                    return "Baja";
                case PAGO: 
                    return "Pago"; 
                    
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
