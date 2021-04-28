package aplicacion;

public class Regulador extends Usuario {
    private float comision; 
    private float saldo;

    public Regulador(String idUsuario, boolean solicitadobaja, boolean autorizado,float saldo, float comision) {
        super(idUsuario, solicitadobaja, autorizado);
        this.saldo = saldo;
        this.comision = comision; 
    }

    @Override
    public String getIdUsuario() {
        return super.getIdUsuario();
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
    
    
}
