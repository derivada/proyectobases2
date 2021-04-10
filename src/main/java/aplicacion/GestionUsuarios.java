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

import vista.FachadaGui;
import baseDatos.fachadaBaseDatos;

public class GestionUsuarios {
    FachadaGui fgui;
    fachadaBaseDatos fbd;
    
   
    public GestionUsuarios(FachadaGui fgui, fachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }
    
    public String validarUsuario(String nombre, String clave){
        Usuario u;
        
        java.util.List<Usuario> inversores = new java.util.ArrayList<Usuario>();
        inversores = fbd.obtenerInversores();
        java.util.List<Usuario> empresas = new java.util.ArrayList<Usuario>();
        empresas = fbd.obtenerEmpresas();
        java.util.List<Usuario> regulador = new java.util.ArrayList<Usuario>();
        regulador = fbd.obtenerRegulador();
        
        u = fbd.validarUsuario(nombre, clave);
        
        if(inversores.contains(u)){
            return "Inversor";
        }else if(empresas.contains(u)){
            return "Empresa";
        }else if(regulador.contains(u)){
            return "Regulador";
        }else{
            return null;
        }
    }
    
    public void iniciaUsuario(String tipo, FachadaAplicacion fa){
        fgui.iniciaUsuarios(fa, tipo);
    }
}
