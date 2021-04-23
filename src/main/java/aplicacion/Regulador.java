package aplicacion;

public class Regulador extends Usuario {
    private float saldo;

    public Regulador(String idUsuario, boolean solicitadobaja, boolean autorizado) {
        super(idUsuario, solicitadobaja, autorizado);
        this.saldo = (float) 0.0;
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
}
