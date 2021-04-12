package aplicacion;

import java.sql.Date;

public class AnuncioBeneficios {
    private String empresa;
    private Date fechaPago;
    private Date fechaAnuncio;
    private Double importeParticipacion;

    public AnuncioBeneficios(String empresa, Date fechaAnuncio, Double importeParticipacion) {
        this.empresa = empresa;
        this.fechaAnuncio = fechaAnuncio;
        this.importeParticipacion = importeParticipacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaAnuncio() {
        return fechaAnuncio;
    }

    public void setFechaAnuncio(Date fechaAnuncio) {
        this.fechaAnuncio = fechaAnuncio;
    }

    public Double getImporteParticipacion() {
        return importeParticipacion;
    }

    public void setImporteParticipacion(Double importeParticipacion) {
        this.importeParticipacion = importeParticipacion;
    }
}
