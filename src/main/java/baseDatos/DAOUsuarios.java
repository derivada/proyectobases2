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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author migue
 */
public class DAOUsuarios extends AbstractDAO {

    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    /**
     * Comprueba si el usuario con ese id y clave está en la base de datos y devuelve su objeto asocidado
     * El objeto Usuario devuelto ya tiene un tipo asociado y todos sus campos rellenados!
     */
    public Usuario validarUsuario(String idUsuario, String clave) {

        Usuario usuario = null;
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

            /*
             * Obtiene el tipo del usuario
             */
            while (rsUsuario.next()) {
                usuario = new Usuario(rsUsuario.getString("id_usuario"));
            }
            if (usuario != null) {
                // Busca el usuario en cada una de las tablas
                for (Regulador r : obtenerRegulador()) {
                    if (r.getIdUsuario().equals(usuario.getIdUsuario()))
                        return r;
                }
                for (Inversor i : obtenerInversores()) {
                    if (i.getIdUsuario().equals(usuario.getIdUsuario()))
                        return i;
                }
                for (Empresa p : obtenerEmpresas()) {
                    if (p.getIdUsuario().equals(usuario.getIdUsuario()))
                        return p;
                }
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return usuario;
    }

    /**
     * Obtiene una lista de usuarios en los que tan solo se conocerá su nombre de usuario
     * Para obtener una lista parametrizada con los atributos completos
     * de cada tipo de usuarios usar los métodos obtenerEmpresa/Inversor/Regulador()
     */
    public java.util.List<Usuario> obtenerUsuarios() {

        java.util.List<Usuario> resultado = new java.util.ArrayList<Usuario>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();
        String consulta = "select * "
                + "from usuario ";
        try {
            stmCatalogo = con.prepareStatement(consulta);
            rsUsuarios = stmCatalogo.executeQuery();
            while (rsUsuarios.next()) {
                usuarioActual = new Usuario(rsUsuarios.getString("id_usuario"));
                resultado.add(usuarioActual);
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                stmCatalogo.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    /**
     * Obtiene una lista de todaos los inversores con todos los atributos de ellos
     */
    public java.util.List<Inversor> obtenerInversores() {
        java.util.List<Inversor> resultado = new java.util.ArrayList<Inversor>();
        Inversor inversorActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsInversores;

        con = this.getConexion();

        String consulta = "select * from inversor ";
        try {
            stmCatalogo = con.prepareStatement(consulta);

            rsInversores = stmCatalogo.executeQuery();
            while (rsInversores.next()) {
                // Empresas con todos sus atributos
                inversorActual = new Inversor(rsInversores.getString("id_usuario"),
                        rsInversores.getString("nombre"),
                        rsInversores.getString("dni"),
                        rsInversores.getString("direccion"),
                        rsInversores.getString("telefono"),
                        rsInversores.getBoolean("autorizado"));
                resultado.add(inversorActual);
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                stmCatalogo.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    /**
     * Obtiene una lista de todas las empresas con todos los atributos de ellas
     */
    public java.util.List<Empresa> obtenerEmpresas() {
        java.util.List<Empresa> resultado = new java.util.ArrayList<Empresa>();
        Empresa empresaActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsEmpresas;

        con = this.getConexion();

        String consulta = "select * from empresa ";
        try {
            stmCatalogo = con.prepareStatement(consulta);

            rsEmpresas = stmCatalogo.executeQuery();
            while (rsEmpresas.next()) {
                // Empresas con todos sus atributos
                empresaActual = new Empresa(rsEmpresas.getString("id_usuario"),
                        rsEmpresas.getString("nombrecomercial"),
                        rsEmpresas.getString("cif"),
                        rsEmpresas.getString("direccion"),
                        rsEmpresas.getString("telefono"),
                        rsEmpresas.getBoolean("autorizado"));
                resultado.add(empresaActual);
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                stmCatalogo.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    /**
     * Obtiene una lista (de un solo elemento) con el regulador del mercado
     * Abajo se explica porque mantuve la lista aunque solo sea 1 elemento
     */
    public java.util.List<Regulador> obtenerRegulador() {
        java.util.List<Regulador> resultado = new java.util.ArrayList<Regulador>();
        Regulador reguladorActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();

        String consulta = "select * from regulador ";

        try {
            stmCatalogo = con.prepareStatement(consulta);
            rsUsuarios = stmCatalogo.executeQuery();
            // Solo debería haber un regulador, de todas formas mantengo la Lista
            // porque devolver null podría causar problemas y además después podremos juntas las listas
            // fácilmente si es necesario - PabloD
            while (rsUsuarios.next()) {
                reguladorActual = new Regulador(rsUsuarios.getString("id_usuario"));
                resultado.add(reguladorActual);
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
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
    public boolean registroUsuario(Usuario u) {
        boolean insertado = false;//boolean para saber si se ha podido insertar o no
        PreparedStatement stmCheck = null;
        PreparedStatement stmIns = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from usuario where id_usuario=?"; //pillo todo donde el ID sea el mismo (solo quiero el RST para saber si es vacio)

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, u.getIdUsuario());
            rst = stmCheck.executeQuery();

            if (!rst.isBeforeFirst()) {//si el rst esta vacio no hay nadie con su id, podemos insertar
                insertado = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCheck.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        if (insertado) {
            try {
                consulta = "insert into usuario(id_usuario, clave, cuenta) values (?,?,?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, u.getIdUsuario());
                stmIns.setString(2, u.getClave());
                stmIns.setFloat(3, u.getCuenta());
                stmIns.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            } finally {
                try {
                    stmIns.close();
                } catch (SQLException e) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
        }

        return insertado;//para que la funcion sepa si se ha insertado o no
    }

    //funcion para insertar un nuevo inversor, totalmente analoga a la de usuario
    public boolean registroInversor(Inversor i) {
        boolean insertado = false;
        PreparedStatement stmCheck = null;
        PreparedStatement stmIns = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from inversor where id_usuario=?";

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, i.getIdUsuario());
            rst = stmCheck.executeQuery();
            if (!rst.isBeforeFirst()) {//si el rst esta vacio no hay nadie con su id, podemos insertar
                insertado = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCheck.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        if (insertado) {
            try {
                consulta = "insert into inversor(id_usuario, nombre, dni, direccion, telefono, autorizado) values (?,?,?,?,?,?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, i.getIdUsuario());
                stmIns.setString(2, i.getNombre());
                stmIns.setString(3, i.getDni());
                if (i.getDireccion().isEmpty()) { //si el campo estaba vacio, coloco null (puede ser interesante para coalesces y funciones que buscan null)
                    stmIns.setString(4, null);
                } else {
                    stmIns.setString(4, i.getDireccion());
                }
                if (i.getTelefono().isEmpty()) {
                    stmIns.setString(5, null);
                } else {
                    stmIns.setString(5, i.getTelefono());
                }
                stmIns.setBoolean(6, i.isAutorizado());
                stmIns.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            } finally {
                try {
                    stmIns.close();
                } catch (SQLException e) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
        }

        return insertado;//para que la funcion sepa si se ha insertado o no
    }

    //funcion para insertar una nueva empresa, totalmente analoga a la de usuario
    public boolean registroEmpresa(Empresa e) {
        boolean insertado = false;
        PreparedStatement stmCheck = null;
        PreparedStatement stmIns = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from empresa where id_usuario=?";

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, e.getIdUsuario());
            rst = stmCheck.executeQuery();
            if (!rst.isBeforeFirst()) {//si el rst esta vacio no hay nadie con su id, podemos insertar
                insertado = true;
            }
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            System.out.println(ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        } finally {
            try {
                stmCheck.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        if (insertado) {
            try {
                consulta = "insert into empresa(id_usuario, nombrecomercial, cif, direccion, telefono, autorizado) values (?,?,?,?,?,?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, e.getIdUsuario());
                stmIns.setString(2, e.getNombre());
                stmIns.setString(3, e.getCIF());
                if (e.getDireccion().isEmpty()) {//si el campo estaba vacio, coloco null (puede ser interesante para coalesces y funciones que buscan null)
                    stmIns.setString(4, null);
                } else {
                    stmIns.setString(4, e.getDireccion());
                }
                if (e.getTelefono().isEmpty()) {
                    stmIns.setString(5, null);
                } else {
                    stmIns.setString(5, e.getTelefono());
                }
                stmIns.setBoolean(6, e.isAutorizado());
                stmIns.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
            } finally {
                try {
                    stmIns.close();
                } catch (SQLException ex) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
        }

        return insertado;//para que la funcion sepa si se ha insertado o no
    }

}
