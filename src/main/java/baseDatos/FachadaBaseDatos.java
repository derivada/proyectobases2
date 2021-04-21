package baseDatos;

import aplicacion.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private DAOParticipaciones daoParticipaciones;
    private DAOHistorial daoHistorial;
    private static final String nombreArchivo = "baseDatos.properties";

    public FachadaAplicacion getFachadaAplicacion() {
        return this.fa;
    }

    public FachadaBaseDatos(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
        conectar();
    }

    private void conectar() {
        /*
         * Intenta conectarse a la base de datos
         */
        Properties configuracion = new Properties();
        FileInputStream arqConfiguracion;
        try {
            arqConfiguracion = new FileInputStream(nombreArchivo);
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://" +
                            configuracion.getProperty("servidor") + ":" +
                            configuracion.getProperty("puerto") + "/" +
                            configuracion.getProperty("baseDatos"),
                    usuario);
            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoParticipaciones = new DAOParticipaciones(conexion, fa);
            daoHistorial = new DAOHistorial(conexion, fa);
            System.out.println("Conexión con la base de datos \"" + configuracion.getProperty("baseDatos")
                    + "\" realizada con éxito!");
        } catch (FileNotFoundException f) {
            System.err.println("Archivo de configuración" + nombreArchivo + " no encontrado:");
            System.err.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i) {
            System.err.println("Error al leer el archivo:");
            System.err.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        } catch (java.sql.SQLException e) {
            System.err.println("Error al conectarse a la base de datos:");
            System.err.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
        }
    }

    public Usuario validarUsuario(String nombre, String clave) {
        return daoUsuarios.validarUsuario(nombre, clave);
    }

    public Empresa obtenerDatosEmpresa(Usuario user) {
        return daoUsuarios.obtenerDatosEmpresa(user);
    }

    public Inversor obtenerDatosInversor(Usuario user) {
        return daoUsuarios.obtenerDatosInversor(user);
    }

    public Regulador obtenerDatosRegulador(Usuario user) {
        return daoUsuarios.obtenerDatosRegulador(user);
    }

    public java.util.List<Usuario> obtenerListaUsuarios() {
        return daoUsuarios.obtenerListaUsuarios();
    }

    public java.util.List<Usuario> obtenerListaEmpresas() {
        return daoUsuarios.obtenerListaEmpresas();
    }

    public java.util.List<Usuario> obtenerListaInversores() {
        return daoUsuarios.obtenerListaInversores();
    }

    public java.util.List<Usuario> obtenerListaReguladores() {
        return daoUsuarios.obtenerListaReguladores();
    }

    public java.util.List<String> obtenerListaNombresUsuarios() {
        return this.obtenerListaUsuarios().stream().map(u -> u.getIdUsuario()).collect(Collectors.toList());
    }

    public boolean registroUsuario(Usuario u) {
        return daoUsuarios.registroUsuario(u);
    }

    public boolean registroInversor(Inversor i) {
        return daoUsuarios.registroInversor(i);
    }

    public boolean registroEmpresa(Empresa e) {
        return daoUsuarios.registroEmpresa(e);
    }


    public void bajaParticipaciones(Empresa e, int baja) {
        daoParticipaciones.bajaParticipaciones(e, baja);
    }

    public ArrayList<Inversor> obtenerInversorPorAutorizacion() {
        return daoUsuarios.obtenerInversoresPorAutorizacion();
    }

    public ArrayList<Empresa> obtenerEmpresaPorAutorizacion() {
        return daoUsuarios.obtenerEmpresaPorAutorizacion();
    }

    public void autorizarUsuario(String id_usuario) {
        daoUsuarios.autorizarUsuario(id_usuario);
    }

    public ArrayList<Inversor> obtenerInversorBaja() {
        return daoUsuarios.obtenerInversorBaja();
    }

    public ArrayList<Empresa> obtenerEmpresaBaja() {
        return daoUsuarios.obtenerEmpresaBaja();
    }


    public void modificarEmpresa(String id_usuario, Empresa u) {
        daoUsuarios.modificarDatosEmpresa(id_usuario, u);
    }

    public void modificarInversor(String id_usuario, Inversor u) {
        daoUsuarios.modificarDatosInversor(id_usuario, u);
    }
    
    public java.util.List<OfertaVenta> getOfertasVenta(String Empresa, float precio){
        return daoUsuarios.getOfertasVenta(Empresa, precio);
    }

    public int getParticipacionesTotales(Usuario u) {
        return daoParticipaciones.getParticipacionesTotales(u);
    }

    public int getParticipacionesEmpresa(Usuario u, Empresa e) {
        return daoParticipaciones.getParticipacionesEmpresa(u, e);
    }

    public int getPartPropEmpresa(Empresa e) {
        return daoParticipaciones.getPartPropEmpresa(e);
    }
    
    public void comprarParticipaciones(Usuario comprador, Empresa vendedor, int cantidad, float precioMax){
        daoParticipaciones.comprarParticipaciones(comprador, vendedor, cantidad, precioMax);
    }

    public void emitirParticipaciones(Empresa e, int emision, float precio) {
        daoParticipaciones.emitirParticipaciones(e, emision, precio);
    }

    public void crearOfertaVenta(Usuario u, Empresa empresa, int numero, float precioVenta) {
        daoParticipaciones.crearOfertaVenta(u, empresa, numero, precioVenta);
    }

    public void eliminarInversor(String idUsuario) {
        daoUsuarios.eliminarInversor(idUsuario);
    }

    public void eliminarEmpresa(String idUsuario) {
        daoUsuarios.eliminarEmpresa(idUsuario);
    }

    public void solicitarBaja(String idUsuario) {
        daoUsuarios.solicitarBaja(idUsuario);
    }
    
    public java.util.List<Historial> actualizarHistorial(Usuario u){
        return daoHistorial.actualizarHistorial(u);
    }
    
    public void insertarHistorial(Historial h){
        daoHistorial.insertaHistorial(h);
    }
}


