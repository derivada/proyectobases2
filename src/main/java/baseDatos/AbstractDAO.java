package baseDatos;

import vista.FachadaGUI;
import vista.componentes.DialogoInfo;

import javax.swing.*;
import java.sql.SQLException;

public abstract class AbstractDAO {
    protected aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;

    protected java.sql.Connection getConexion() {
        return this.conexion;
    }

    protected void setConexion(java.sql.Connection conexion) {
        this.conexion = conexion;
    }

    protected void setFachadaAplicacion(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
    }

    protected aplicacion.FachadaAplicacion getFachadaAplicacion() {
        return fa;
    }

    protected void manejarExcepcionSQL(SQLException e) {
        System.err.println("Excepción al acceder a la base de datos...");
        e.printStackTrace();
        FachadaGUI.muestraExcepcion("Excepción al acceder a la base de datos", e.getMessage(),
                DialogoInfo.NivelDeAdvertencia.ERROR_BASEDATOS);
    }

    protected void manejarExcepcion(Exception e) {
        System.err.println("Excepción al acceder a la base de datos...");
        e.printStackTrace();
        FachadaGUI.muestraExcepcion("Excepción al acceder a la base de datos", e.getMessage(),
                DialogoInfo.NivelDeAdvertencia.ERROR);
    }

    protected void muestraExcepcion(JFrame padre, String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel, boolean bloqueaInput) {
        FachadaGUI.muestraExcepcion(padre, titulo, descripcion, nivel, bloqueaInput);
    }

    protected void muestraExcepcion(JFrame padre, String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        FachadaGUI.muestraExcepcion(padre, titulo, descripcion, nivel, false);
    }

    protected void muestraExcepcion(String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        FachadaGUI.muestraExcepcion(null, titulo, descripcion, nivel, false);
    }

    protected void muestraExcepcion(String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        FachadaGUI.muestraExcepcion(null, "Mercado de valores", descripcion, nivel, false);
    }

    protected void muestraExcepcion(String descripcion) {
        FachadaGUI.muestraExcepcion(null, "Mercado de valores", descripcion, DialogoInfo.NivelDeAdvertencia.ERROR, false);
    }


}
