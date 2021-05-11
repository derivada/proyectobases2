package aplicacion;

public class EntradaParticipacion {
    private final String poseedor;
    private final String empresa;
    private final int numero;

    public EntradaParticipacion(String poseedor, String empresa, int numero) {
        this.poseedor = poseedor;
        this.empresa = empresa;
        this.numero = numero;
    }

    public String getPoseedor() {
        return poseedor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public int getNumero() {
        return numero;
    }
}