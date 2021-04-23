/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import aplicacion.Historial;
import aplicacion.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author migue
 */
public class DAOHistorial extends AbstractDAO {

    public DAOHistorial(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public java.util.List<Historial> actualizarHistorial(Usuario u) {

        java.util.List<Historial> resultado = new java.util.ArrayList<>();
        Historial historialActual;

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
                historialActual = new Historial(rst.getString("empresa"), rst.getString("usuario"), rst.getTimestamp("fecha"), rst.getInt("cantidad"), rst.getFloat("precio"), rst.getString("tipo"));
                resultado.add(historialActual);
            }
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmHistorial != null)
                    stmHistorial.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }


    //para insertar una nueva entrada al historial debemos pasarle un objetoHistorial creado, con los usuarios, la fecha, la cantidad el precio y el tipo, este ultimo de manera manual

    //por ejemplo cuando se hace la emision, hacer new historial(lenovo, lenovo, currentMilis, cantidad, precio, emision);
    public void insertaHistorial(Historial h) {


        PreparedStatement stmHistorial = null;
        Connection con;

        con = this.getConexion();

        String consultaHistorial = "Insert into historial(empresa, usuario, fecha, cantidad, precio, tipo) values(?, ?, ?, ?, ?, ?)";

        try {

            stmHistorial = con.prepareStatement(consultaHistorial);

            stmHistorial.setString(1, h.getEmpresa());
            stmHistorial.setString(2, h.getComprador()); //cambiar a getUsuario
            stmHistorial.setTimestamp(3, h.getFecha());
            stmHistorial.setInt(4, h.getCantidad());
            stmHistorial.setFloat(5, h.getPrecio());
            stmHistorial.setString(6, h.getTipo());

            stmHistorial.executeUpdate();

        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmHistorial != null)
                    stmHistorial.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

}
