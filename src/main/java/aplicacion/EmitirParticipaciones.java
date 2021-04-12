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
public class EmitirParticipaciones {
    private String empresa; 
    private Integer numeroparticipaciones; 
    private Date fechaemision; 

    public EmitirParticipaciones(String empresa, Date fechaemision) {
        this.empresa = empresa;
        this.fechaemision = fechaemision;
        this.numeroparticipaciones=null; 
    }

    public EmitirParticipaciones(String empresa, Integer numeroparticipaciones, Date fechaemision) {
        this.empresa = empresa;
        this.numeroparticipaciones = numeroparticipaciones;
        this.fechaemision = fechaemision;
    }
    
}
