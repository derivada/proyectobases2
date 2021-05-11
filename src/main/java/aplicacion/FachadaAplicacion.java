package aplicacion;

import baseDatos.FachadaBaseDatos;
import vista.FachadaGUI;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    FachadaGUI fgui;
    FachadaBaseDatos fbd;
    GestionUsuarios cu;
    GestionHistorial ch;
    GestionParticipaciones cp;

    private final long fechaInicio;

    private FachadaAplicacion() {
        fechaInicio = Calendar.getInstance().getTimeInMillis();
        fgui = FachadaGUI.getInstance();
        fbd = FachadaBaseDatos.getInstance();
        cu = new GestionUsuarios(fgui, fbd);
        ch = new GestionHistorial(fgui, fbd);
        cp = new GestionParticipaciones(fgui, fbd);
    }

    public void inicializarGUI() {
        fgui.iniciaVista(this);
    }

    public void conectarBaseDeDatos() {
        fbd.conectar();
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
        return cp.getParticipacionesTotales(u);
    }

    public int getParticipacionesEmpresa(Usuario u, Empresa e) {
        return cp.getParticipacionesEmpresa(u, e);
    }

    public int getParticipacionesVendibles(Usuario u, Empresa e) {
        return cp.getParticipacionesVendibles(u, e);
    }

    public int getPartPropEmpresa(Empresa e) {
        return cp.getPartPropEmpresa(e);
    }

    public void emitirParticipaciones(Empresa e, int emision) {
        cp.emitirParticipaciones(e, emision);
    }

    public void bajaParticipaciones(Empresa e, int baja) {
        cp.bajaParticipaciones(e, baja);
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


    public void bajaUsuario(Usuario u, float saldo) {
        cu.bajaUsuario(u, saldo);
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

    public java.util.List<AnuncioBeneficios> obtenerAnunciosRegulador() {
        return cu.obtenerAnunciosRegulador();
    }

    public void bajaAnuncio(String empresa, Timestamp fecha, Float importe, Integer numparticipaciones) {
        cu.bajaAnuncio(empresa, fecha, importe, numparticipaciones);
    }

    public float obtenerComision(String r) {
        return cu.obtenerComision(r);
    }

    public void modificarComision(Regulador r, float comision) {
        cu.modificarComision(r, comision);
    }

    public void modificarSaldo(String id, float saldo, String tipo) {
        cu.modificarSaldo(id, saldo, tipo);
    }

    public Float obtenerSaldo(Usuario u, String tipo) {
        return cu.obtenerSaldo(u, tipo);
    }

    public void bloquearSaldo(Empresa e, int cantidad) {
        cu.bloquearSaldo(e, cantidad);
    }

    public void liberarSaldo(Empresa e, int cantidad) {
        cu.liberarSaldo(e, cantidad);
    }

    public int getNumeroParticipaciones(String idUsuario, String tipo) {
        return cp.getNumeroParticipaciones(idUsuario, tipo);
    }

    public java.util.List<OfertaVenta> getOfertasVenta(String empresa, float precio) {
        return cp.getOfertasVenta(empresa, precio);
    }

    public java.util.List<OfertaVenta> getOfertasVentaPropias(String usuario) {
        return cp.getOfertasVentaPropias(usuario);
    }

    public void crearOfertaVenta(Usuario u, Empresa empresa, int numero, float precioVenta) {
        cp.crearOfertaVenta(u, empresa, numero, precioVenta);
    }

    public void comprarParticipaciones(Usuario comprador, Empresa empresa, int numero, float precioMaximo) {
        cp.comprarParticipaciones(comprador, empresa, numero, precioMaximo);
    }

    public void crearAnuncio(Float importe, Empresa e, Timestamp fecha, Integer numeroParticipaciones) {
        cp.crearAnuncio(importe, e, fecha, numeroParticipaciones);
    }

    public void pagarBeneficios(Float importe, Integer participaciones, Empresa empresa, AnuncioBeneficios a) {
        cp.pagarBeneficios(importe, participaciones, empresa, a);
    }

    public java.util.List<AnuncioBeneficios> obtenerAnuncios(String empresa) {
        return cp.obtenerAnuncios(empresa);
    }

    public void solicitarBajaAnuncio(String empresa, Timestamp fechaPago) {
        cp.solicitarBajaAnuncio(empresa, fechaPago);
    }

    public void bajaOfertaVenta(Usuario usuario, Timestamp fecha) {
        cp.bajaOfertaVenta(usuario, fecha);
    }

    public java.util.List<EntradaHistorial> obtenerHistorial() {
        return ch.obtenerHistorial();
    }

    public java.util.List<EntradaHistorial> obtenerHistorial(Usuario u) {
        return ch.obtenerHistorial(u);
    }

    public void insertarHistorial(EntradaHistorial h) {
        ch.insertarHistorial(h);
    }

    public List<EntradaParticipacion> obtenerDatosParticipaciones(Usuario u){
        return cp.obtenerDatosParticipaciones(u);
    }
}
