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
public class EmitirParticipaciones {
    private String empresa; 
    private Integer numeroparticipaciones; 
    private Timestamp fechaemision; 

    public EmitirParticipaciones(String empresa, Timestamp fechaemision) {
        this.empresa = empresa;
        this.fechaemision = fechaemision;
        this.numeroparticipaciones=null; 
    }

    public EmitirParticipaciones(String empresa, Integer numeroparticipaciones, Timestamp fechaemision) {
        this.empresa = empresa;
        this.numeroparticipaciones = numeroparticipaciones;
        this.fechaemision = fechaemision;
    }
    
}
