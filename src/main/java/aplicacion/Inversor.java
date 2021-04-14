package aplicacion;

/**
 *
 * @author migue
 */
public class Inversor extends Usuario{
    private String nombre;
    private String dni;
    private String direccion;
    private String telefono;
    private Float cuenta;

    public Inversor(String idUsuario, String nombre, String dni, String direccion, String telefono, boolean autorizadoAlta, boolean solicitadoBaja, Float cuenta) {
        super(idUsuario, autorizadoAlta, solicitadoBaja);
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuenta = cuenta;
    }

    
    @Override
    public String getIdUsuario() {
        return super.getIdUsuario();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public boolean isAutorizado(){
        Usuario u = (Usuario)this;
        return u.isAutorizadoAlta();
    }

    public Float getCuenta() {
        return cuenta;
    }

    public void setCuenta(Float cuenta) {
        this.cuenta = cuenta;
    }
    
    
    
    
}
