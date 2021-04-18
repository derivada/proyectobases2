
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
public class AnuncioBeneficios {
    private String empresa; 
    private Date fechaPago; 
    private Date fechaAnuncio; 
    private Float importeparticipacion;
    private Integer numeroparticipaciones; 
    private boolean soicitadobaja; 

    public AnuncioBeneficios(String empresa, Date fechaPago, Date fechaAnuncio, Float importeparticipacion,Integer numeroparticipaciones,boolean solicitadobaja) {
        this.empresa = empresa;
        this.fechaPago = fechaPago;
        this.fechaAnuncio = fechaAnuncio;
        this.importeparticipacion = importeparticipacion;
        this.soicitadobaja=solicitadobaja;
        this.numeroparticipaciones=numeroparticipaciones; 
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

    public Float getImporteparticipacion() {
        return importeparticipacion;
    }

    public void setImporteparticipacion(Float importeparticipacion) {
        this.importeparticipacion = importeparticipacion;
    }


    public boolean isSoicitadobaja() {
        return soicitadobaja;
    }

    public void setSoicitadobaja(boolean soicitadobaja) {
        this.soicitadobaja = soicitadobaja;
    }
     public Object getImporteParticipacion() {
        // TODO
        return null;
    }
     
        public Integer getNumeroparticipaciones() {
        return numeroparticipaciones;
    }

    public void setNumeroparticipaciones(Integer numeroparticipaciones) {
        this.numeroparticipaciones = numeroparticipaciones;
    }

   

}
