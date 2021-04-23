package aplicacion;

import java.sql.Timestamp;

public class EmitirParticipaciones {
    private String empresa;
    private Integer numeroparticipaciones;
    private Timestamp fechaemision;

    public EmitirParticipaciones(String empresa, Timestamp fechaemision) {
        this.empresa = empresa;
        this.fechaemision = fechaemision;
        this.numeroparticipaciones = null;
    }

    public EmitirParticipaciones(String empresa, Integer numeroparticipaciones, Timestamp fechaemision) {
        this.empresa = empresa;
        this.numeroparticipaciones = numeroparticipaciones;
        this.fechaemision = fechaemision;
    }

}
