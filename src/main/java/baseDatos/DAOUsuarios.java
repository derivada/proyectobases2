/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import aplicacion.Empresa;
import aplicacion.Inversor;
import aplicacion.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author migue
 */
public class DAOUsuarios extends AbstractDAO {

    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public Usuario validarUsuario(String idUsuario, String clave) {
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select id_usuario, clave, cuenta "
                    + "from usuario "
                    + "where id_usuario = ? and clave = ?");
            stmUsuario.setString(1, idUsuario);
            stmUsuario.setString(2, clave);
            rsUsuario = stmUsuario.executeQuery();
            while (rsUsuario.next()) {
                resultado = new Usuario(rsUsuario.getString("id_usuario"), rsUsuario.getString("clave"),
                       rsUsuario.getFloat("cuenta"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }
    
    
    public  java.util.List<Usuario> obtenerInversores(){
        java.util.List<Usuario> resultado = new java.util.ArrayList<Usuario>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();

        String consulta = "select * "
                + "from inversor ";

        try {
            stmCatalogo = con.prepareStatement(consulta);

            rsUsuarios = stmCatalogo.executeQuery();
            while (rsUsuarios.next()) {
                usuarioActual = new Usuario(rsUsuarios.getString("id_usuario"));
                resultado.add(usuarioActual);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCatalogo.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }
    
    public  java.util.List<Usuario> obtenerEmpresas(){
        java.util.List<Usuario> resultado = new java.util.ArrayList<Usuario>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();

        String consulta = "select * "
                + "from empresa ";

        try {
            stmCatalogo = con.prepareStatement(consulta);

            rsUsuarios = stmCatalogo.executeQuery();
            while (rsUsuarios.next()) {
                usuarioActual = new Usuario(rsUsuarios.getString("id_usuario"));
                resultado.add(usuarioActual);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCatalogo.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }
    
    public  java.util.List<Usuario> obtenerRegulador(){
        java.util.List<Usuario> resultado = new java.util.ArrayList<Usuario>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();

        String consulta = "select * "
                + "from regulador ";

        try {
            stmCatalogo = con.prepareStatement(consulta);

            rsUsuarios = stmCatalogo.executeQuery();
            while (rsUsuarios.next()) {
                usuarioActual = new Usuario(rsUsuarios.getString("id_usuario"));
                resultado.add(usuarioActual);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCatalogo.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }
    
    
    //funcion para el registro de un nuevo Usuario
    public boolean registroUsuario(Usuario u){
        boolean insertado=false;//boolean para saber si se ha podido insertar o no
        PreparedStatement stmCheck=null;
        PreparedStatement stmIns=null;
        ResultSet rst;
        Connection con;
        
        con=this.getConexion();
        
        String consulta="select * from usuario where id_usuario=?"; //pillo todo donde el ID sea el mismo (solo quiero el RST para saber si es vacio)
        
        try{
            stmCheck=con.prepareStatement(consulta);
            stmCheck.setString(1, u.getIdUsuario());
            rst=stmCheck.executeQuery();
            
            if(!rst.isBeforeFirst()){//si el rst esta vacio no hay nadie con su id, podemos insertar
                insertado=true;
            }
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCheck.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        if(insertado){
            try{
                consulta="insert into usuario(id_usuario, clave, cuenta) values (?,?,?)";
                stmIns=con.prepareStatement(consulta);
                stmIns.setString(1, u.getIdUsuario());
                stmIns.setString(2, u.getClave());
                stmIns.setFloat(3, u.getCuenta());
                stmIns.executeUpdate();
            }catch (SQLException e){
                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
                try {stmIns.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        }
        
        return insertado;//para que la funcion sepa si se ha insertado o no
    }
    
    //funcion para insertar un nuevo inversor, totalmente analoga a la de usuario
    public boolean registroInversor(Inversor i){
        boolean insertado=false;
        PreparedStatement stmCheck=null;
        PreparedStatement stmIns=null;
        ResultSet rst;
        Connection con;
        
        con=this.getConexion();
        
        String consulta="select * from inversor where id_usuario=?";
        
        try{
            stmCheck=con.prepareStatement(consulta);
            stmCheck.setString(1, i.getIdUsuario());
            rst=stmCheck.executeQuery();           
            if(!rst.isBeforeFirst()){//si el rst esta vacio no hay nadie con su id, podemos insertar
                insertado=true;
            }
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCheck.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        if(insertado){
            try{
                consulta="insert into inversor(id_usuario, nombre, dni, direccion, telefono, autorizado) values (?,?,?,?,?,?)";
                stmIns=con.prepareStatement(consulta);
                stmIns.setString(1, i.getIdUsuario());
                stmIns.setString(2, i.getNombre());
                stmIns.setString(3, i.getDni());
                if(i.getDireccion().isEmpty()){ //si el campo estaba vacio, coloco null (puede ser interesante para coalesces y funciones que buscan null)
                    stmIns.setString(4, null);
                }
                else{
                    stmIns.setString(4, i.getDireccion());
                }
                if(i.getTelefono().isEmpty()){
                    stmIns.setString(5, null);
                }
                else{
                    stmIns.setString(5, i.getTelefono());
                } 
                stmIns.setBoolean(6, i.isAutorizado());
                stmIns.executeUpdate();
            }catch (SQLException e){
                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
                try {stmIns.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        }
        
        return insertado;//para que la funcion sepa si se ha insertado o no
    }
    
    //funcion para insertar una nueva empresa, totalmente analoga a la de usuario
    public boolean registroEmpresa(Empresa e){
        boolean insertado=false;
        PreparedStatement stmCheck=null;
        PreparedStatement stmIns=null;
        ResultSet rst;
        Connection con;
        
        con=this.getConexion();
        
        String consulta="select * from empresa where id_usuario=?";
        
        try{
            stmCheck=con.prepareStatement(consulta);
            stmCheck.setString(1, e.getIdUsuario());
            rst=stmCheck.executeQuery();           
            if(!rst.isBeforeFirst()){//si el rst esta vacio no hay nadie con su id, podemos insertar
                insertado=true;
            }
        }catch (SQLException ex){//hay que cambiar la exception de e a ex, lo hago abajo tambien
          System.out.println(ex.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        }finally{
          try {stmCheck.close();} catch (SQLException ex){System.out.println("Imposible cerrar cursores");}
        }
        
        if(insertado){
            try{
                consulta="insert into empresa(id_usuario, nombrecomercial, cif, direccion, telefono, autorizado) values (?,?,?,?,?,?)";
                stmIns=con.prepareStatement(consulta);
                stmIns.setString(1, e.getIdUsuario());
                stmIns.setString(2, e.getNombre());
                stmIns.setString(3, e.getCIF());
                if(e.getDireccion().isEmpty()){//si el campo estaba vacio, coloco null (puede ser interesante para coalesces y funciones que buscan null)
                    stmIns.setString(4, null);
                }
                else{
                    stmIns.setString(4, e.getDireccion());
                }
                if(e.getTelefono().isEmpty()){
                    stmIns.setString(5, null);
                }
                else{
                    stmIns.setString(5, e.getTelefono());
                }
                stmIns.setBoolean(6, e.isAutorizado());
                stmIns.executeUpdate();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
            }finally{
                try {stmIns.close();} catch (SQLException ex){System.out.println("Imposible cerrar cursores");}
            }
        }
        
        return insertado;//para que la funcion sepa si se ha insertado o no
    }
    
    
    //Función para obtener un inversor con todos sus datos 
    
    public Inversor selecionarInversor(String id_usuario){
        Inversor resultado=null; 
        Connection con; 
        PreparedStatement stmInversor = null;
        ResultSet rsInversor;
         con = this.getConexion();

        try {
            stmInversor = con.prepareStatement("select i.*,u.cuenta,u.clave " +
                "from usuario as u, inversor as i " +
                "where u.id_usuario=i.id_usuario and u.id_usuario= ? " + 
                    "group by i.id_usuario,u.cuenta,u.clave ");
            stmInversor.setString(1, id_usuario);
            rsInversor = stmInversor.executeQuery();
            while (rsInversor.next()) {
                resultado = new Inversor(rsInversor.getString("id_usuario"),rsInversor.getString("nombre"),rsInversor.getString("dni"),
                    rsInversor.getString("direccion"),rsInversor.getString("telefono"),rsInversor.getBoolean("autorizado"));
                resultado.setCuenta(rsInversor.getFloat("cuenta"));
                resultado.setClave(rsInversor.getString("clave"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmInversor.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
        return resultado; 
    }
}
