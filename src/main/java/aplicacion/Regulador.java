package aplicacion;

/**
 *
 * @author migue
 */
public class Regulador extends Usuario{

    public Regulador(String idUsuario, boolean autorizadoAlta, boolean solicitadoBaja) {
        super(idUsuario, autorizadoAlta, solicitadoBaja);
    }

    @Override
    public String getIdUsuario() {
        return super.getIdUsuario();
    }
    
    
}
