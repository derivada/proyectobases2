package baseDatos;

import vista.componentes.DialogoInfo;

import java.sql.SQLException;

public abstract class AbstractDAO {
    private aplicacion.FachadaAplicacion fa;
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
        this.getFachadaAplicacion().muestraExcepcion("Excepción al acceder a la base de datos", e.getMessage(),
                DialogoInfo.NivelDeAdvertencia.ERROR_BASEDATOS);
    }

}
