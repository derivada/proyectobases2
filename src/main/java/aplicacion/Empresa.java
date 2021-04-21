/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 * @author migue
 */
public class Empresa extends Usuario {
    private String nombre;
    private String CIF;
    private String direccion;
    private String telefono;


    private Integer participacionesbloqueadas; 

    private float saldo;
    private float saldobloqueado;



    public Empresa(String idUsuario, String nombre, String CIF, float saldo, float saldobloqueado, String direccion, String telefono, boolean solicitadobaja, boolean autorizado) {
        super(idUsuario, solicitadobaja, autorizado);
        this.nombre = nombre;
        this.CIF = CIF;
        this.direccion = direccion;
        this.telefono = telefono;
        this.saldo=saldo;
        this.saldobloqueado=saldobloqueado;
    }
    
    public Empresa(String idUsuario, String nombre, String CIF, Float saldo, Float saldobloqueado, String direccion, String telefono, String clave, boolean solicitadobaja, boolean autorizado) {
        super(idUsuario, clave, solicitadobaja, autorizado);
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
    
    @Override
    public String getClave() {
        return super.getClave();
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

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public float getSaldobloqueado() {
        return saldobloqueado;
    }

    public void setSaldobloqueado(float saldobloqueado) {
        this.saldobloqueado = saldobloqueado;
    }

    public Integer getParticipacionesbloqueadas() {
        return participacionesbloqueadas;
    }

    public void setParticipacionesbloqueadas(Integer participacionesbloqueadas) {
        this.participacionesbloqueadas = participacionesbloqueadas;
    }
    


}
