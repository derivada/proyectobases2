package baseDatos;

import aplicacion.EntradaHistorial;
import aplicacion.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOHistorial extends AbstractDAO {

    public DAOHistorial(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    // Sin ningún filtro
    public java.util.List<EntradaHistorial> obtenerHistorial() {

        java.util.List<EntradaHistorial> resultado = new java.util.ArrayList<>();
        EntradaHistorial historialActual;

        PreparedStatement stmHistorial = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consultaHistorial = "select * "
                + "from historial ";
        
        try {
            stmHistorial = con.prepareStatement(consultaHistorial);
            rst = stmHistorial.executeQuery();
            while (rst.next()) {
                //Historial(String empresa, String comprador, Date fecha, Integer cantidad, Float precio, String tipo)
                historialActual = new EntradaHistorial(rst.getString("empresa"),
                        rst.getString("usuario"), rst.getTimestamp("fecha"),
                        rst.getInt("cantidad"), rst.getFloat("precio"),
                        rst.getString("tipo"));
                resultado.add(historialActual);
            }
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmHistorial != null) {
                    stmHistorial.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }
    
    // Con filtro de usuario, para más filtros hacerlo fuera del DAO, no se violarán
    // las restricciones de negocio
    public java.util.List<EntradaHistorial> obtenerHistorial(Usuario u) {

        java.util.List<EntradaHistorial> resultado = new java.util.ArrayList<>();
        EntradaHistorial historialActual;

        PreparedStatement stmHistorial = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consultaHistorial = "select * "
                + "from historial "
                + "where usuario = ?";
        try {
            stmHistorial = con.prepareStatement(consultaHistorial);
            stmHistorial.setString(1, u.getIdUsuario());
            rst = stmHistorial.executeQuery();
            while (rst.next()) {
                //Historial(String empresa, String comprador, Date fecha, Integer cantidad, Float precio, String tipo)
                historialActual = new EntradaHistorial(rst.getString("empresa"),
                        rst.getString("usuario"), rst.getTimestamp("fecha"),
                        rst.getInt("cantidad"), rst.getFloat("precio"),
                        rst.getString("tipo"));
                resultado.add(historialActual);
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                if (stmHistorial != null) {
                    stmHistorial.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

  
    //para insertar una nueva entrada al historial debemos pasarle un objetoHistorial creado, con los usuarios, la fecha, la cantidad el precio y el tipo, este ultimo de manera manual
    //por ejemplo cuando se hace la emision, hacer new historial(lenovo, lenovo, currentMilis, cantidad, precio, emision);
    public void insertaHistorial(EntradaHistorial h) {
        PreparedStatement stmHistorial = null;
        Connection con;

        con = this.getConexion();

        String consultaHistorial = "Insert into historial(empresa, usuario, fecha, cantidad, precio, tipo) values(?, ?, ?, ?, ?, ?)";

        try {

            stmHistorial = con.prepareStatement(consultaHistorial);

            stmHistorial.setString(1, h.getEmpresa());
            stmHistorial.setString(2, h.getComprador()); //cambiar a getUsuario
            stmHistorial.setTimestamp(3, h.getFecha());
            stmHistorial.setInt(4, h.getCantidad() !=null ? h.getCantidad() : 0);
            stmHistorial.setFloat(5, h.getPrecio() != null ? h.getPrecio() : 0);
            stmHistorial.setString(6, h.getTipo().toString());

            stmHistorial.executeUpdate();

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmHistorial != null) {
                    stmHistorial.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
}
