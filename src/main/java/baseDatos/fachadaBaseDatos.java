/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import aplicacion.Empresa;
import aplicacion.Inversor;
import aplicacion.Regulador;
import aplicacion.Usuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author migue
 */
public class fachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private static final String nombreArchivo = "baseDatos.properties";

    public fachadaBaseDatos(aplicacion.FachadaAplicacion fa) {
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

    public java.util.List<Usuario> obtenerUsuarios() { return daoUsuarios.obtenerUsuarios(); }

    public java.util.List<Inversor> obtenerInversores() { return daoUsuarios.obtenerInversores(); }

    public java.util.List<Empresa> obtenerEmpresas() {
        return daoUsuarios.obtenerEmpresas();
    }

    public java.util.List<Regulador> obtenerRegulador() {
        return daoUsuarios.obtenerRegulador();
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
    
    public int getPartPropEmpresa(Empresa e){
        return daoUsuarios.getPartPropEmpresa(e);
    }


}


