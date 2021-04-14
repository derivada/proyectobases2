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
    private Float saldo; 
    private Float saldobloqueado; 


    public Empresa(String idUsuario, String nombre, String CIF, String direccion, String telefono,Float saldo,Float saldobloqueado) {
        super(idUsuario);
        this.nombre = nombre;
        this.CIF = CIF;
        this.direccion = direccion;
        this.telefono = telefono;
        this.saldo=saldo; 
        this.saldobloqueado=saldobloqueado; 
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

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Float getSaldobloqueado() {
        return saldobloqueado;
    }

    public void setSaldobloqueado(Float saldobloqueado) {
        this.saldobloqueado = saldobloqueado;
    }
    


    
}
