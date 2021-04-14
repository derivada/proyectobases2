/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 *
 * @author migue
 */
public class Empresa extends Usuario{
    private String nombre;
    private String CIF;
    private String direccion;
    private String telefono;
    private final Float cuenta;

    public Empresa(String idUsuario, String nombre, String CIF, String direccion, String telefono, boolean autorizadoAlta, boolean solicitadoBaja, Float cuenta) {
        super(idUsuario, autorizadoAlta, solicitadoBaja);
        this.nombre = nombre;
        this.CIF = CIF;
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

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
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
    
}
