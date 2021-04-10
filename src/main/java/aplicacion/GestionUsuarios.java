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
    
    public void iniciaUsuario(String tipo, FachadaAplicacion fa,String id_usuario,String clave){
        Usuario u=fbd.validarUsuario(id_usuario, clave); 
        fgui.iniciaUsuarios(fa, tipo,u);
    }
    
    public void iniciaEmpresa(String tipo, FachadaAplicacion fa,String id_usuario,String clave){
        Usuario u=fbd.validarUsuario(id_usuario, clave); 
        fgui.iniciaEmpresa(fa, tipo,u);
    }
    
    public void iniciaRegulador(String tipo, FachadaAplicacion fa,String id_usuario,String clave){
        Usuario u=fbd.validarUsuario(id_usuario, clave); 
        fgui.iniciaRegulador(fa, tipo,u);
    }
    
    public boolean registroUsuario(Usuario u){
        return fbd.registroUsuario(u);
    }
    
    public boolean registroInversor(Inversor i){
        return fbd.registroInversor(i);
    }
    
    public boolean registroEmpresa(Empresa e){
        return fbd.registroEmpresa(e);
    }
}
