package baseDatos;

import aplicacion.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;
import vista.componentes.DialogoInfo;

public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private static final String nombreArchivo = "baseDatos.properties";

    public FachadaAplicacion getFachadaAplicacion(){
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

    public java.util.List<String> obtenerListaNombresUsuarios() {
        return daoUsuarios.obtenerListaNombresUsuarios();
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

    public int getPartPropEmpresa(Empresa e) {
        return daoUsuarios.getPartPropEmpresa(e);
    }

    public void emitirParticipaciones(Empresa e, int emision, int precio) {
        daoUsuarios.emitirParticipaciones(e, emision, precio);
    }
    
    public void bajaParticipaciones(Empresa e, int baja){
        daoUsuarios.bajaParticipaciones(e, baja);
    }

//    public ArrayList<Inversor> obtenerInversorPorAutorizacion(boolean autorizado) {
//        return daoUsuarios.obtenerInversoresPorAutorizacion(autorizado);
//    }
//
//    public ArrayList<Empresa> obtenerEmpresaPorAutorizacion(boolean autorizado) {
//        return daoUsuarios.obtenerEmpresaPorAutorizacion(autorizado);
//    }

    public void modificarEmpresa(String id_usuario, Empresa u) {
        daoUsuarios.modificarDatosEmpresa(id_usuario, u);
    }

    public void modificarInversor(String id_usuario, Inversor u) {
        daoUsuarios.modificarDatosInversor(id_usuario, u);
    }
    
    public int crearAnuncio(Float importe, Empresa e,Date fecha,Integer numeroParticipaciones){
        return daoUsuarios.crearAnuncio(importe, e, fecha,numeroParticipaciones);
    }
    
    public void pagarBeneficios(Float importe,Integer participaciones,Empresa empresa,AnuncioBeneficios a){
        daoUsuarios.pagarBeneficios(importe,participaciones, empresa,a);
    }
    
     public java.util.List<AnuncioBeneficios> obtenerAnuncios(String empresa){
        return daoUsuarios.obtenerAnuncios(empresa); 
    }
     
     public boolean solicitarBajaAnuncio(String empresa,Date fechaPago){
        return daoUsuarios.solicitarBajaAnuncio(empresa, fechaPago);
       
    }
     
     public java.util.List<AnuncioBeneficios> obtenerAnunciosRegulador(){
        return daoUsuarios.obtenerAnunciosRegulador(); 
    }
     
     public void bajaAnuncio(String empresa,Date fecha,Float importe){
         daoUsuarios.bajaAnuncio(empresa, fecha, importe);
     }

}


