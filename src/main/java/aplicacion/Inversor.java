package aplicacion;

public class Inversor extends Usuario {
    private String nombre;
    private String dni;
    private String direccion;
    private String telefono;
    private float saldo;

    public Inversor(String idUsuario, String nombre, String dni, String direccion, String telefono, float saldo, boolean solicitadobaja, boolean autorizado) {
        super(idUsuario, solicitadobaja, autorizado);
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.saldo = saldo;
    }
    
    public Inversor(String idUsuario, String nombre, String dni, String direccion, String telefono, Float saldo, String clave, boolean solicitadobaja, boolean autorizado) {
        super(idUsuario, clave, solicitadobaja, autorizado);
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.saldo = saldo;
    }


    @Override
    public String getIdUsuario() {
        return super.getIdUsuario();
    }
    
    @Override
    public String getClave(){
        return super.getClave();
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

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
