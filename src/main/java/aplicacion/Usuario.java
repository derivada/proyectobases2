/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.util.Objects;

/**
 *
 * @author migue
 */
public class Usuario {

    private String idUsuario;
    private String clave;
    private boolean autorizadoAlta;
    private boolean solicitadoBaja;

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
        this.clave = null;
    }

    public Usuario(String idUsuario, String clave) {
        this.idUsuario = idUsuario;
        this.clave = clave;
    }
    
    

    public Usuario(String idUsuario, boolean autorizadoAlta, boolean solicitadoBaja) {
        this.idUsuario = idUsuario;
        this.autorizadoAlta = autorizadoAlta;
        this.solicitadoBaja = solicitadoBaja;
    }



    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isAutorizadoAlta() {
        return autorizadoAlta;
    }

    public void setAutorizadoAlta(boolean autorizadoAlta) {
        this.autorizadoAlta = autorizadoAlta;
    }

    public boolean isSolicitadoBaja() {
        return solicitadoBaja;
    }

    public void setSolicitadoBaja(boolean solicitadoBaja) {
        this.solicitadoBaja = solicitadoBaja;
    }

    public String getClave() {
        return clave;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.idUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }

}
