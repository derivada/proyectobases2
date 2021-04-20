package aplicacion;

import java.util.ArrayList;

import baseDatos.FachadaBaseDatos;
import vista.FachadaGui;
import vista.componentes.DialogoInfo;

import javax.swing.*;
import java.util.Calendar;

/**
 * Fachada principal de la aplicación
 */
public class FachadaAplicacion {

    FachadaGui fgui;
    FachadaBaseDatos fbd;
    GestionUsuarios cu;
    private final long fechaInicio;

    public FachadaAplicacion() {
        fechaInicio = Calendar.getInstance().getTimeInMillis();
        fgui = new FachadaGui(this);
        fbd = new FachadaBaseDatos(this);
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

    public void muestraExcepcion(String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        fgui.muestraExcepcion(null, titulo, descripcion, nivel, false);
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

    public java.util.List<Usuario> obtenerListaUsuarios() {
        return cu.obtenerListaUsuarios();
    }

    public java.util.List<Usuario> obtenerListaEmpresas() {
        return cu.obtenerListaEmpresas();
    }

    public java.util.List<Usuario> obtenerListaInversores() {
        return cu.obtenerListaInversores();
    }

    public java.util.List<Usuario> obtenerListaReguladores() {
        return cu.obtenerListaReguladores();
    }

    public Empresa obtenerDatosEmpresa(Usuario user) {
        return cu.obtenerDatosEmpresa(user);
    }

    public Inversor obtenerDatosInversor(Usuario user) {
        return cu.obtenerDatosInversor(user);
    }

    public Regulador obtenerDatosRegulador(Usuario user) {
        return cu.obtenerDatosRegulador(user);
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
    
    public void menuModificarInversor(Inversor inversor){
        cu.iniciaModificarInversor(inversor,this);
    }
    
    public void menuModificarEmpresa(Empresa empresa){
        cu.iniciaModificarEmpresa(empresa, this);
    }

    public void salir() {
        System.out.println("Tiempo transcurrido: " + (Calendar.getInstance().getTimeInMillis() - fechaInicio) + " ms");
        System.out.println("Fin del programa...");
        System.exit(0);
    }

    public boolean registroInversor(Inversor i) {
        return cu.registroInversor(i);
    }

    public boolean registroEmpresa(Empresa e) {
        return cu.registroEmpresa(e);
    }

    public int getParticipacionesTotales(Usuario u) {
        return cu.getParticipacionesTotales(u);
    }

    public int getParticipacionesEmpresa(Usuario u, Empresa e) {
        return cu.getParticipacionesEmpresa(u, e);
    }

    public int getPartPropEmpresa(Empresa e) {
        return cu.getPartPropEmpresa(e);
    }

    public void emitirParticipaciones(Empresa e, int emision, int precio) {
        cu.emitirParticipaciones(e, emision, precio);
    }

    public void bajaParticipaciones(Empresa e, int baja) {
        cu.bajaParticipaciones(e, baja);
    }

    public ArrayList<Usuario> obtenerUsuariosPorAutorizacion() {
        return cu.obtenerUsuarioPorAutorizacion();
    }

    public void autorizarUsuarios(String idUsuario) {
        cu.autorizarUsuario(idUsuario);
    }

    public void modificarUsuario(String id_usuario, Usuario u) {
        cu.modificarUsuario(id_usuario, u);
    }

    public ArrayList<Usuario> obtenerUsuariosBaja() {
        return cu.obtenerUsuarioBaja();
    }

    public java.util.List<OfertaVenta> getOfertasVenta(String empresa, int precio) {
        return cu.getOfertasVenta(empresa, precio);
    }

    public void _tests() {
        StringBuilder log = new StringBuilder();
        for (Usuario u : fbd.obtenerListaUsuarios()) {
            Usuario temp = fbd.obtenerDatosEmpresa(u);
            if (temp == null) {
                temp = fbd.obtenerDatosInversor(u);
            }
            if (temp == null) {
                temp = fbd.obtenerDatosRegulador(u);
            }
            u = temp;
            if (u == null) {
                continue;
            }
            int part = fbd.getParticipacionesTotales(u);
            log.append("Usuario: " + u.getIdUsuario() + " Tipo: "
                    + u.getClass().getSimpleName() + " Participaciones totales: " + part + "\n");
        }
        Usuario elena = fbd.validarUsuario("Elena", "432");
        Usuario HP = fbd.validarUsuario("HP", "778");
        int partHP = fbd.getParticipacionesEmpresa(elena, (Empresa) HP);
        log.append("Elena tiene " + partHP + " participaciones de HP\n");
        this.muestraExcepcion(log.toString(), DialogoInfo.NivelDeAdvertencia.INFORMACION);
    }

    public void crearOfertaVenta(Usuario u, Empresa empresa, int numero, float precioVenta) {
        cu.crearOfertaVenta(u, empresa, numero, precioVenta);
    }

    public void comprarParticipaciones(Usuario comprador, Empresa vendedor, int numero, float precioMaximo) {
        // TODO
        System.out.println("Comprador: " + comprador.getIdUsuario()
                + "\nVendedor: " + vendedor.getIdUsuario()
                + "\nCantidad: " + numero
                + "\nPrecio máximo: " + precioMaximo);
    }

    public void bajaUsuario(Usuario u) {
        cu.bajaUsuario(u);
    }

    public void solicitarBaja(String idUsuario) {
        cu.solicitarBaja(idUsuario);
    }
    
    public boolean comprobarID(String id){
        return cu.comprobarID(id);
    }
    
    public boolean modificarInversor(Inversor i, String pass, String idviejo){
        return cu.modificarInversor(i, pass, idviejo);
    }
    
    public boolean modificarEmpresa(Empresa e, String pass, String idviejo){
        return cu.modificarEmpresa(e, pass, idviejo);
    }
}
