package aplicacion;

import java.util.ArrayList;

import baseDatos.FachadaBaseDatos;
import java.sql.Date;
import java.sql.Timestamp;
import vista.FachadaGui;
import vista.componentes.DialogoInfo;

import javax.swing.*;
import java.util.Calendar;

/**
 * Fachada principal de la aplicación
 */
public class FachadaAplicacion {

    private static FachadaAplicacion _instance;

    public static FachadaAplicacion getInstance() {
        if (_instance == null) {
            _instance = new FachadaAplicacion();
        }
        return _instance;
    }

    FachadaGui fgui;
    FachadaBaseDatos fbd;
    GestionUsuarios cu;
    private final long fechaInicio;

    private FachadaAplicacion() {
        fechaInicio = Calendar.getInstance().getTimeInMillis();
        fgui = FachadaGui.getInstance();
        fbd = FachadaBaseDatos.getInstance();
        cu = new GestionUsuarios(fgui, fbd);
    }

    public void inicializarGUI() {
        fgui.iniciaVista(this);
    }
    
    public void conectarBaseDeDatos() {
        fbd.conectar();
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

    public void menuModificarInversor(Inversor inversor) {
        cu.iniciaModificarInversor(inversor, this);
    }

    public void menuModificarEmpresa(Empresa empresa) {
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

    public void emitirParticipaciones(Empresa e, int emision) {
        cu.emitirParticipaciones(e, emision);
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

    public java.util.List<OfertaVenta> getOfertasVenta(String empresa, float precio) {
        return cu.getOfertasVenta(empresa, precio);
    }

    public void crearOfertaVenta(Usuario u, Empresa empresa, int numero, float precioVenta) {
        cu.crearOfertaVenta(u, empresa, numero, precioVenta);
    }

    public void comprarParticipaciones(Usuario comprador, Empresa empresa, int numero, float precioMaximo) {
        cu.comprarParticipaciones(comprador, empresa, numero, precioMaximo);
    }

    public void bajaUsuario(Usuario u) {
        cu.bajaUsuario(u);
    }

    public void solicitarBaja(String idUsuario) {
        cu.solicitarBaja(idUsuario);
    }

    public boolean comprobarID(String id) {
        return cu.comprobarID(id);
    }

    public boolean modificarInversor(Inversor i, String pass, String idviejo) {
        return cu.modificarInversor(i, pass, idviejo);
    }

    public boolean modificarEmpresa(Empresa e, String pass, String idviejo) {
        return cu.modificarEmpresa(e, pass, idviejo);
    }

    public void crearAnuncio(Float importe, Empresa e, Date fecha, Integer numeroParticipaciones) {
        cu.crearAnuncio(importe, e, fecha, numeroParticipaciones);
    }

    public void pagarBeneficios(Float importe, Integer participaciones, Empresa empresa, AnuncioBeneficios a) {
        cu.pagarBeneficios(importe, participaciones, empresa, a);
    }

    public java.util.List<AnuncioBeneficios> obtenerAnuncios(String empresa) {
        return cu.obtenerAnuncios(empresa);
    }

    public void solicitarBajaAnuncio(String empresa, Timestamp fechaPago) {
        cu.solicitarBajaAnuncio(empresa, fechaPago);
    }

    public java.util.List<AnuncioBeneficios> obtenerAnunciosRegulador() {
        return cu.obtenerAnunciosRegulador();
    }

    public void bajaAnuncio(String empresa, Timestamp fecha, Float importe) {
        cu.bajaAnuncio(empresa, fecha, importe);
    }

    public java.util.List<EntradaHistorial> obtenerHistorial() {
        return cu.obtenerHistorial();
    }
    public java.util.List<EntradaHistorial> obtenerHistorial(Usuario u) {
        return cu.obtenerHistorial(u);
    }

    public void insertarHistorial(EntradaHistorial h) {
        cu.insertarHistorial(h);
    }

    public float obtenerComision(Regulador r) {
        return cu.obtenerComision(r);
    }
    
    public void modificarComision(Regulador r, float comision){
        cu.modificarComision(r, comision);
    }
    
}
