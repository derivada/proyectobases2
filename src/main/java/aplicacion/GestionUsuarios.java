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
        return  fbd.validarUsuario(nombre, clave);
    }

    public java.util.List<Usuario> obtenerListaUsuarios() {
        return fbd.obtenerListaUsuarios();
    }

    public java.util.List<Usuario> obtenerListaEmpresas() {
        return fbd.obtenerListaEmpresas();
    }

    public java.util.List<Usuario> obtenerListaInversores() {
        return fbd.obtenerListaInversores();
    }

    public java.util.List<Usuario> obtenerListaReguladores() {
        return fbd.obtenerListaReguladores();
    }

    public Empresa obtenerDatosEmpresa(Usuario user) {
        return fbd.obtenerDatosEmpresa(user);
    }

    public Inversor obtenerDatosInversor(Usuario user) {
        return fbd.obtenerDatosInversor(user);
    }

    public Regulador obtenerDatosRegulador(Usuario user) {
        return fbd.obtenerDatosRegulador(user);
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
    
    public void iniciaModificarInversor(Inversor i, FachadaAplicacion fa){
        fgui.iniciaModificarInversor(i,fa);
    }
    
    public void iniciaModificarEmpresa(Empresa e, FachadaAplicacion fa){
        fgui.iniciaModificarEmpresa(e,fa);
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

    public void emitirParticipaciones(Empresa e, int emision, int precio) {
        fbd.emitirParticipaciones(e, emision, precio);
    }

    public void bajaParticipaciones(Empresa e, int baja) {
        fbd.bajaParticipaciones(e, baja);
    }

    public ArrayList<Usuario> obtenerUsuarioPorAutorizacion() {
        ArrayList<Usuario> resultado = new ArrayList<>();

        resultado.addAll(fbd.obtenerInversorPorAutorizacion());
        resultado.addAll(fbd.obtenerEmpresaPorAutorizacion());

        return resultado;
    }

    public void autorizarUsuario(String id_usuario) {
        fbd.autorizarUsuario(id_usuario);
    }

    public ArrayList<Usuario> obtenerUsuarioBaja() {
        ArrayList<Usuario> resultado = new ArrayList<>();

        resultado.addAll(fbd.obtenerInversorBaja());
        resultado.addAll(fbd.obtenerEmpresaBaja());

        return resultado;
    }


    public void modificarUsuario(String id_usuario, Usuario u) {
        if (u instanceof Inversor) {
            fbd.modificarInversor(id_usuario, (Inversor) u);
        } else {
            fbd.modificarEmpresa(id_usuario, (Empresa) u);
        }
    }
    
    public java.util.List<OfertaVenta> getOfertasVenta(String empresa, int precio){
        return fbd.getOfertasVenta(empresa, precio);
    }

    public int getParticipacionesEmpresa(Usuario u, Empresa e) {
        return fbd.getParticipacionesEmpresa(u, e);
    }

    public int getParticipacionesTotales(Usuario u) {
        return fbd.getParticipacionesTotales(u);
    }

    public void crearOfertaVenta(Usuario u, Empresa empresa, int numero, float precioVenta) {
        fbd.crearOfertaVenta(u, empresa, numero, precioVenta);

    }

    //TODO haría falta comprobar que el saldo es 0??
    public void bajaUsuario(Usuario u) {
        if(u instanceof Inversor){
            fbd.eliminarInversor(u.getIdUsuario());

        } else{
            fbd.eliminarEmpresa(u.getIdUsuario());
        }
    }

    public void solicitarBaja(String idUsuario) {
        fbd.solicitarBaja(idUsuario);
    }
    
    public boolean comprobarID(String id){
        return fbd.comprobarID(id);
    }
    
    public boolean modificarInversor(Inversor i, String pass, String idviejo){
        return fbd.modificarInversor(i, pass, idviejo);
    }
    
    public boolean modificarEmpresa(Empresa e, String pass, String idviejo){
        return fbd.modificarEmpresa(e, pass, idviejo);
    }
}
