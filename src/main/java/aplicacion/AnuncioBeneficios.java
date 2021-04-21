
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;
import java.sql.Date; 
import java.sql.Timestamp;

/**
 *
 * @author Usuario
 */
public class AnuncioBeneficios {
    private String empresa; 
    private Timestamp fechaPago; 
    private Timestamp fechaAnuncio; 
    private Float importeparticipacion; 
    private boolean soicitadobaja; 

    public AnuncioBeneficios(String empresa, Timestamp fechaPago, Timestamp fechaAnuncio, Float importeparticipacion,boolean solicitadobaja) {
        this.empresa = empresa;
        this.fechaPago = fechaPago;
        this.fechaAnuncio = fechaAnuncio;
        this.importeparticipacion = importeparticipacion;
        this.soicitadobaja=solicitadobaja; 
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Timestamp getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Timestamp getFechaAnuncio() {
        return fechaAnuncio;
    }

    public void setFechaAnuncio(Timestamp fechaAnuncio) {
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

   

}
