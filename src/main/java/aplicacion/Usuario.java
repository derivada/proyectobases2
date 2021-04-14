/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.util.Objects;

/**
 * @author migue
 */
public class Usuario {
    private String idUsuario;
    private String clave;

    private boolean solicitadobaja; 
    private boolean autorizado; 

    public Usuario(String idUsuario, String clave,boolean solicitadobaja,boolean autorizado) {
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.solicitadobaja = solicitadobaja;
        this.autorizado=autorizado; 
    }
    
    public Usuario(String idUsuario, boolean solicitadobaja, boolean autorizado) {
        this.idUsuario = idUsuario;
        this.clave = null;
        this.solicitadobaja = solicitadobaja;
        this.autorizado=autorizado; 
    }
    
   
    public Usuario(String idUsuario){
        this.idUsuario=idUsuario;

    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }



    public boolean isSolicitadobaja() {
        return solicitadobaja;
    }

    public void setSolicitadobaja(boolean solicitadobaja) {
        this.solicitadobaja = solicitadobaja;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
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
