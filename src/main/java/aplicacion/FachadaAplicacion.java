package aplicacion;

import java.util.ArrayList;
import vista.FachadaGui;
import vista.componentes.DialogoInfo;

import javax.swing.*;
import java.util.Calendar;

/**
 * Fachada principal de la aplicación
 */
public class FachadaAplicacion {

    FachadaGui fgui;
    baseDatos.fachadaBaseDatos fbd;
    GestionUsuarios cu;
    private final long fechaInicio;

    public FachadaAplicacion() {
        fechaInicio = Calendar.getInstance().getTimeInMillis();
        fgui = new FachadaGui(this);
        fbd = new baseDatos.fachadaBaseDatos(this);
        cu = new GestionUsuarios(fgui, fbd);
    }


    public void inicializarGUI() {
        fgui.iniciaVista(this);
    }

    public void muestraExcepcion(JFrame padre, String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel, boolean bloqueaInput) {
        fgui.muestraExcepcion(padre, titulo, descripcion, nivel, bloqueaInput);
    }

    public void muestraExcepcion(JFrame padre, String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        fgui.muestraExcepcion(padre, titulo, descripcion, nivel, false);
    }

    public void muestraExcepcion(JFrame padre, String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        fgui.muestraExcepcion(padre, "Mercado de Valores", descripcion, nivel, false);
    }

    public void muestraExcepcion(String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        fgui.muestraExcepcion(null, "Mercado de valores", descripcion, nivel, false);
    }

    public void muestraExcepcion(String descripcion) {
        fgui.muestraExcepcion(null, "Mercado de valores", descripcion, DialogoInfo.NivelDeAdvertencia.ERROR, false);
    }


    public Usuario validarUsuario(String nombre, String clave) {
        return cu.validarUsuario(nombre, clave);
    }

    public void menuInversor(Inversor inversor) {
        cu.iniciaInversor(inversor, this);
    }

    public void menuEmpresa(Empresa empresa) {
        cu.iniciaEmpresa(empresa, this);
    }

    public void menuRegulador(Regulador regulador) {
        cu.iniciaRegulador(regulador, this);
    }

    public void salir() {
        System.out.println("Tiempo transcurrido: " + (Calendar.getInstance().getTimeInMillis() - fechaInicio) + " ms");
        System.out.println("Fin del programa...");
        System.exit(0);
    }

    public boolean registroUsuario(Usuario u) {
        return cu.registroUsuario(u);
    }

    public boolean registroInversor(Inversor i) {
        return cu.registroInversor(i);
    }

    public boolean registroEmpresa(Empresa e) {
        return cu.registroEmpresa(e);
    }
    
    public int getPartPropEmpresa(Empresa e){
        return cu.getPartPropEmpresa(e);
    }
    
    public ArrayList<Usuario> obtenerUsuariosPorAutorizacion(boolean autorizacion){
        return cu.obtenerUsuarioPorAutorizacion(autorizacion);
    }
    
    public void modificarUsuario(String id_usuario, Usuario u){
        cu.modificarUsuario(id_usuario, u);
    }
}
