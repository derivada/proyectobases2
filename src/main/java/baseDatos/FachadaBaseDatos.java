package baseDatos;

import aplicacion.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

import vista.componentes.DialogoInfo;

public class FachadaBaseDatos {

    private static FachadaBaseDatos _instance;

    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private DAOParticipaciones daoParticipaciones;
    private DAOHistorial daoHistorial;
    private static final String nombreArchivo = "baseDatos.properties";

    public static FachadaBaseDatos getInstance() {
        if (_instance == null) {
            _instance = new FachadaBaseDatos();
        }
        return _instance;
    }

    private FachadaBaseDatos() {
        // Bucle infinito!
        //this.fa = FachadaAplicacion.getInstance();
    }

    public void conectar() {
        this.fa = FachadaAplicacion.getInstance();
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
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://"
                            + configuracion.getProperty("servidor") + ":"
                            + configuracion.getProperty("puerto") + "/"
                            + configuracion.getProperty("baseDatos"),
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

    public java.util.List<OfertaVenta> getOfertasVenta(String Empresa, float precio) {
        return daoUsuarios.getOfertasVenta(Empresa, precio);
    }

    public java.util.List<OfertaVenta> getOfertasVentaPropias(String usuario) {
        return daoUsuarios.getOfertasVentaPropias(usuario);
    }

    public int getParticipacionesTotales(Usuario u) {
        return daoParticipaciones.getParticipacionesTotales(u);
    }

    public int getParticipacionesEmpresa(Usuario u, Empresa e) {
        return daoParticipaciones.getParticipacionesEmpresa(u, e.getIdUsuario());
    }

    public int getParticipacionesVendibles(Usuario u, Empresa e) {
        return daoParticipaciones.getParticipacionesVendibles(u, e);
    }

    public int getPartPropEmpresa(Empresa e) {
        return daoParticipaciones.getPartPropEmpresa(e);
    }

    public void comprarParticipaciones(Usuario comprador, Empresa empresa, int cantidad, float precioMax) {

        daoParticipaciones.comprarParticipaciones(comprador, empresa.getIdUsuario(), cantidad, precioMax,
                daoUsuarios.obtenerComision(daoUsuarios.obtenerListaReguladores().get(0).getIdUsuario()),
                daoUsuarios.obtenerListaReguladores().get(0), daoUsuarios.obtenerAnuncios(empresa.getIdUsuario()));
    }

    public void emitirParticipaciones(Empresa e, int emision) {
        daoParticipaciones.emitirParticipaciones(e, emision);
    }

    public void crearOfertaVenta(Usuario u, Empresa empresa, int numero, float precioVenta) {
        daoParticipaciones.crearOfertaVenta(u, empresa.getIdUsuario(), numero, precioVenta);
    }

    public void bajaOfertaVenta(Usuario usuario, Timestamp fecha) {
        daoParticipaciones.bajaOfertaVenta(usuario, fecha);
    }

    public void eliminarInversor(String idUsuario, float saldo) {
        daoUsuarios.eliminarInversor(idUsuario, saldo);
    }

    public void eliminarEmpresa(String idUsuario, float saldo) {
        daoUsuarios.eliminarEmpresa(idUsuario, saldo);
    }

    public void solicitarBaja(String idUsuario) {
        daoUsuarios.solicitarBaja(idUsuario);
    }

    public void crearAnuncio(Float importe, Empresa e, Timestamp fecha, Integer numeroParticipaciones) {
        int aux = daoUsuarios.crearAnuncio(importe, e, fecha, numeroParticipaciones);

        switch (aux) {
            case 1:
                getFachadaAplicacion().muestraExcepcion("Anuncio creado correctamente ",
                        DialogoInfo.NivelDeAdvertencia.INFORMACION);

                break;
            case 2:
                getFachadaAplicacion().muestraExcepcion("El importe que tiene la empresa no es suficiente",
                        DialogoInfo.NivelDeAdvertencia.ERROR);
                break;
            case 3:
                getFachadaAplicacion().muestraExcepcion("El numero de participacione que tiene la empresa no es suficiente",
                        DialogoInfo.NivelDeAdvertencia.ERROR);
                break;
            default:

        }
    }

    public void pagarBeneficios(Float importe, Integer participaciones, Empresa empresa, AnuncioBeneficios a) {
        daoUsuarios.pagarBeneficios(importe, participaciones, empresa, a, daoParticipaciones.getParticipacionesEmpresa(empresa, empresa.getIdUsuario()));
    }

    public java.util.List<AnuncioBeneficios> obtenerAnuncios(String empresa) {
        return daoUsuarios.obtenerAnuncios(empresa);
    }

    public boolean solicitarBajaAnuncio(String empresa, Timestamp fechaPago) {
        return daoUsuarios.solicitarBajaAnuncio(empresa, fechaPago);

    }

    public java.util.List<AnuncioBeneficios> obtenerAnunciosRegulador() {
        return daoUsuarios.obtenerAnunciosRegulador();
    }

    public void bajaAnuncio(String empresa, Timestamp fecha, Float importe, Integer numparticipaciones) {
        daoUsuarios.bajaAnuncio(empresa, fecha, importe, numparticipaciones);
    }

    public java.util.List<EntradaHistorial> obtenerHistorial() {
        return daoHistorial.obtenerHistorial();
    }

    public java.util.List<EntradaHistorial> obtenerHistorial(Usuario u) {
        return daoHistorial.obtenerHistorial(u);
    }

    public void insertarHistorial(EntradaHistorial h) {
        daoHistorial.insertaHistorial(h);
    }

    public boolean comprobarID(String id) {
        return daoUsuarios.comprobarID(id);
    }

    public boolean modificarInversor(Inversor i, String pass, String idviejo) {
        return daoUsuarios.modificarInversor(i, pass, idviejo);
    }

    public boolean modificarEmpresa(Empresa e, String pass, String idviejo) {
        return daoUsuarios.modificarEmpresa(e, pass, idviejo);
    }

    public FachadaAplicacion getFachadaAplicacion() {
        return fa;
    }

    public float obtenerComision(String r) {
        return daoUsuarios.obtenerComision(r);
    }

    public void modificarComision(Regulador r, float comision) {
        daoUsuarios.modificarComision(r, comision);
    }

    public float obtenerSaldoInversor(Usuario u) {
        return daoUsuarios.obtenerSaldoInversor(u);
    }

    public float obtenerSaldoEmpresa(Usuario u) {
        return daoUsuarios.obtenerSaldoEmpresa(u);
    }

    public void modificarSaldoInversor(String idUsuario, float saldo) {
        daoUsuarios.modificarSaldoInversor(idUsuario, saldo);
    }

    public void modificarSaldoEmpresa(String idUsuario, float saldo) {
        daoUsuarios.modificarSaldoEmpresa(idUsuario, saldo);
    }

    public int getNumeroParticipacionesInversor(String idUsuario) {
        return daoUsuarios.getNumeroParticipacionesInversor(idUsuario);
    }

    public int getNumeroParticipacionesEmpresa(String idUsuario) {
        return daoUsuarios.getNumeroParticipacionesEmpresa(idUsuario);
    }
}
