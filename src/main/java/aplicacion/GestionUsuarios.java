/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 * @author migue
 */

import vista.FachadaGui;
import baseDatos.FachadaBaseDatos;
import vista.componentes.DialogoInfo;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class GestionUsuarios {
    FachadaGui fgui;
    FachadaBaseDatos fbd;


    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public Usuario validarUsuario(String nombre, String clave) {

        List<String> listaNombresUsuarios = fbd.obtenerListaNombresUsuarios();

        if (!listaNombresUsuarios.contains(nombre.replaceAll("\\s+$", ""))) {
            fbd.getFachadaAplicacion().muestraExcepcion("Error al autentificar usuario", "El usuario no está registrado!",
                    DialogoInfo.NivelDeAdvertencia.ERROR);
            return null;
        }

        // El usuario está registrado, intentar validarlo
        Usuario u = fbd.validarUsuario(nombre, clave);

        // Si el método devuelve null es que la clave no es correcta
        if (u == null) {
            // TODO: Ventana emergente que muestre que la clave no es correcta
            fbd.getFachadaAplicacion().muestraExcepcion("Error al autentificar usuario", "La clave no es correcta!",
                    DialogoInfo.NivelDeAdvertencia.ERROR);
            return null;
        }

        // Si el método no devuelve null ya tenemos al usuario completo
        return u;
    }

    public void iniciaInversor(Inversor i, FachadaAplicacion fa) {
        fgui.iniciaInversores(i, fa);
    }

    public void iniciaEmpresa(Empresa e, FachadaAplicacion fa) {
        fgui.iniciaEmpresa(e, fa);
    }

    public void iniciaRegulador(Regulador r, FachadaAplicacion fa) {
        fgui.iniciaRegulador(r, fa);
    }

    public boolean registroUsuario(Usuario u) {
        return fbd.registroUsuario(u);
    }

    public boolean registroInversor(Inversor i) {
        return fbd.registroInversor(i);
    }

    public boolean registroEmpresa(Empresa e) {
        return fbd.registroEmpresa(e);
    }
    
    public int getPartPropEmpresa(Empresa e) {
        return fbd.getPartPropEmpresa(e);
    }
    
    public void emitirParticipaciones(Empresa e, int emision, int precio){
        fbd.emitirParticipaciones(e, emision, precio);
    }
    
    public void bajaParticipaciones(Empresa e, int baja){
        fbd.bajaParticipaciones(e, baja);
    }
    /*
    public ArrayList<Usuario> obtenerUsuarioPorAutorizacion(boolean autorizado){
        ArrayList<Usuario> resultado = new ArrayList<>();

        resultado.addAll(fbd.obtenerInversorPorAutorizacion(autorizado));
        resultado.addAll(fbd.obtenerEmpresaPorAutorizacion(autorizado));
        
        return resultado;
    }
    */
    public void modificarUsuario(String id_usuario, Usuario u){
        if(u instanceof Inversor){
            fbd.modificarInversor(id_usuario, (Inversor)u);
        } else {
            fbd.modificarEmpresa(id_usuario, (Empresa)u);
        }
    }
}
