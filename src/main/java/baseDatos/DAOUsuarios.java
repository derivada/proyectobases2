package baseDatos;

import aplicacion.Empresa;
import aplicacion.Inversor;
import aplicacion.OfertaVenta;
import aplicacion.Regulador;
import aplicacion.Usuario;
import vista.componentes.DialogoInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUsuarios extends AbstractDAO {

    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    /**
     * Comprueba si el usuario con ese id y clave está en la base de datos y devuelve su objeto asocidado
     * El objeto Usuario devuelto ya tiene un tipo asociado y todos sus campos rellenados!
     *
     * @param idUsuario
     * @param clave
     * @return
     */
    public Usuario validarUsuario(String idUsuario, String clave) {

        Usuario usuarioValidado = null;
        Usuario result = null;

        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select id_usuario, autorizado, solicitadobaja "
                    + "from usuario "
                    + "where id_usuario = ? and clave = ?");
            stmUsuario.setString(1, idUsuario);
            stmUsuario.setString(2, clave);
            rsUsuario = stmUsuario.executeQuery();

            /*
             * Obtiene el tipo del usuario que está intentando acceder
             */
            while (rsUsuario.next()) {
                usuarioValidado = new Usuario(rsUsuario.getString("id_usuario"), rsUsuario.getBoolean("solicitadobaja"), rsUsuario.getBoolean("autorizado"));
            }

            if (usuarioValidado == null) {
                muestraExcepcion("La contraseña introducida es incorrecta! \nNo se puede acceder al sistema...", DialogoInfo.NivelDeAdvertencia.ERROR);
                return null;
            }
            if (!usuarioValidado.isAutorizado()) {
                muestraExcepcion("El usuario no está validado! \nNo se puede acceder al sistema...", DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
                return null;
            }

            // Busca el usuario en cada una de las tablas
            result = obtenerDatosEmpresa(usuarioValidado);
            if (result == null)
                result = obtenerDatosInversor(usuarioValidado);
            if (result == null)
                result = obtenerDatosRegulador(usuarioValidado);

        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return result;
    }


    /**
     * Obtiene una lista de usuarios en los que tan solo se conocerá su nombre de usuario y si están
     * autorizados o con solicitud de baja
     * Para obtener una lista parametrizada con los atributos completos
     * de cada tipo de usuarios usar los métodos obtenerEmpresa/Inversor/Regulador()
     */
    public java.util.List<Usuario> obtenerListaUsuarios() {
        return _obtenerListaUsuarios("usuario");
    }

    public java.util.List<Usuario> obtenerListaEmpresas() {
        return _obtenerListaUsuarios("empresa");
    }

    public java.util.List<Usuario> obtenerListaInversores() {
        return _obtenerListaUsuarios("inversor");
    }

    public java.util.List<Usuario> obtenerListaReguladores() {
        return _obtenerListaUsuarios("regulador");
    }

    private java.util.List<Usuario> _obtenerListaUsuarios(String tipo) {
        // Método interno llamado desde obtenrListaUsuarios() obtenerListaEmpresas() obtenerListaInversores()
        // y obtenerListaReguladores() que maneja las consultas generales
        java.util.List<Usuario> resultado = new java.util.ArrayList<Usuario>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();
        // esto es obviamente muy poco seguro pero al ser un método privado no hace falta complicarse mucho
        String consulta = "select id_usuario, autorizado, solicitadobaja "
                + "from usuario";
        if (!tipo.equals("usuario"))
            consulta = "select id_usuario, autorizado, solicitadobaja "
                    + "from usuario natural join " + tipo;

        try {
            stmCatalogo = con.prepareStatement(consulta);
            rsUsuarios = stmCatalogo.executeQuery();
            while (rsUsuarios.next()) {
                resultado.add(
                        new Usuario(rsUsuarios.getString("id_usuario"),
                                rsUsuarios.getBoolean("autorizado"),
                                rsUsuarios.getBoolean("solicitadobaja"))
                );
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
     * Obtiene los datos concretos del usuario si este es un inversor
     */
    public Inversor obtenerDatosInversor(Usuario user) {
        Inversor resultado = null;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsInversores;

        con = this.getConexion();

        String consulta = "select * from inversor where id_usuario = ?";
        try {
            stmCatalogo = con.prepareStatement(consulta);
            stmCatalogo = con.prepareStatement(consulta);
            stmCatalogo.setString(1, user.getIdUsuario());
            rsInversores = stmCatalogo.executeQuery();
            while (rsInversores.next()) {
                // Empresas con todos sus atributos
                resultado = new Inversor(rsInversores.getString("id_usuario"),
                        rsInversores.getString("nombre"),
                        rsInversores.getString("dni"),
                        rsInversores.getString("direccion"),
                        rsInversores.getString("telefono"),
                        rsInversores.getFloat("saldo"),
                        user.isSolicitadobaja(),
                        user.isAutorizado());
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
     * Obtiene los datos concretos del usuario si este es una empresa
     * @param user
     * @return 
     */
    public Empresa obtenerDatosEmpresa(Usuario user) {
        Empresa resultado = null;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsEmpresas;

        con = this.getConexion();

        String consulta = "select * from empresa where id_usuario = ?";
        try {
            stmCatalogo = con.prepareStatement(consulta);
            stmCatalogo.setString(1, user.getIdUsuario());
            rsEmpresas = stmCatalogo.executeQuery();
            while (rsEmpresas.next()) {
                // Empresas con todos sus atributos
                resultado = new Empresa(rsEmpresas.getString("id_usuario"),
                        rsEmpresas.getString("nombrecomercial"),
                        rsEmpresas.getString("cif"),
                        rsEmpresas.getFloat("saldo"),
                        rsEmpresas.getFloat("saldobloqueado"),
                        rsEmpresas.getString("direccion"),
                        rsEmpresas.getString("telefono"),
                        user.isSolicitadobaja(),
                        user.isAutorizado());
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
     * Obtiene los datos concretos del usuario si este es el regulador
     */
    public Regulador obtenerDatosRegulador(Usuario user) {
        Regulador resultado = null;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();

        String consulta = "select * from regulador where id_usuario = ?";

        try {
            stmCatalogo = con.prepareStatement(consulta);
            stmCatalogo.setString(1, user.getIdUsuario());
            rsUsuarios = stmCatalogo.executeQuery();
            while (rsUsuarios.next()) {
                resultado = new Regulador(rsUsuarios.getString("id_usuario"), user.isSolicitadobaja(), user.isAutorizado());
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
            manejarExcepcionSQL(e);
        } finally {
            try {
                stmCheck.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        if (insertado) {
            try {
                consulta = "insert into usuario(id_usuario, clave, autorizado) values (?,?,?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, u.getIdUsuario());
                stmIns.setString(2, u.getClave());
                stmIns.setBoolean(3, false);
                stmIns.executeUpdate();
            } catch (SQLException e) {
                manejarExcepcionSQL(e);
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
            manejarExcepcionSQL(e);
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
                manejarExcepcionSQL(e);
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
            manejarExcepcionSQL(ex);
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
                manejarExcepcionSQL(ex);
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

    public int getPartPropEmpresa(Empresa e) {
        int result = 0;
        PreparedStatement stmCheck = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select numparticipaciones "
                + "from participacionesempresa "
                + "where usuario=? AND empresa=?";

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, e.getIdUsuario());
            stmCheck.setString(2, e.getIdUsuario());
            rst = stmCheck.executeQuery();
            while (rst.next()) {
                result = rst.getInt("numparticipaciones");
            }
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmCheck.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return result;
    }
    
    public void bajaParticipaciones(Empresa e, int baja){
        int antiguasPart = getPartPropEmpresa(e);
        PreparedStatement stmUpdate = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();
        String consulta = "update participacionesempresa "
                + "set numparticipaciones=? "
                + "where usuario=? AND empresa=?";
        
        try {
                stmUpdate = con.prepareStatement(consulta);
                stmUpdate.setInt(1, antiguasPart - baja);
                stmUpdate.setString(2, e.getIdUsuario());
                stmUpdate.setString(3, e.getIdUsuario());
                stmUpdate.executeUpdate();
            } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
                manejarExcepcionSQL(ex);
            } finally {
                try {
                    stmUpdate.close();
                } catch (SQLException ex) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
        
    }

    public void emitirParticipaciones(Empresa e, int emision, int precio) {
        int antiguasPart = 0;
        PreparedStatement stmAntiguas = null;
        PreparedStatement stmUpdate = null;
        PreparedStatement stmNueva = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select numparticipaciones "
                + "from participacionesempresa "
                + "where usuario=? AND empresa=?";


        String consulta2 = "update participacionesempresa "
                + "set numparticipaciones=? "
                + "where usuario=? AND empresa=?";

        String consulta3 = "insert into emitirparticipaciones(empresa, fechaemision, numeroparticipaciones, precio) values(?,now(),?, ?);";

        try {
            con.setAutoCommit(false);
            stmAntiguas = con.prepareStatement(consulta);
            stmAntiguas.setString(1, e.getIdUsuario());
            stmAntiguas.setString(2, e.getIdUsuario());
            rst = stmAntiguas.executeQuery();
            while (rst.next()) {
                antiguasPart = rst.getInt("numparticipaciones");
            }
            try {
                stmNueva = con.prepareStatement(consulta3);
                stmNueva.setString(1, e.getIdUsuario());
                stmNueva.setInt(2, emision);
                stmNueva.setInt(3, precio);
                stmNueva.executeUpdate();
            } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
                manejarExcepcionSQL(ex);
            } finally {
                try {
                    stmNueva.close();
                } catch (SQLException ex) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
            try {
                stmUpdate = con.prepareStatement(consulta2);
                stmUpdate.setInt(1, emision + antiguasPart);
                stmUpdate.setString(2, e.getIdUsuario());
                stmUpdate.setString(3, e.getIdUsuario());
                stmUpdate.executeUpdate();
            } catch (SQLException ex) { //hay que cambiar la exception de e a ex, lo hago abajo tambien
                manejarExcepcionSQL(ex);
            } finally {
                try {
                    stmUpdate.close();
                } catch (SQLException ex) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmAntiguas.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

    }

    //Obtiene una lista de inversores con la autorizacion a false
    public ArrayList<Inversor> obtenerInversoresPorAutorizacion() {
        ArrayList<Inversor> resultado = new ArrayList<>();
        PreparedStatement stmInversores = null;
        ResultSet rst;
        ResultSet rst2;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from inversor natural join usuario where autorizado = false";

        try {
            stmInversores = con.prepareStatement(consulta);
            rst = stmInversores.executeQuery();
            while (rst.next()) {
                Inversor i = new Inversor(rst.getString("id_usuario"), rst.getString("nombre"), rst.getString("dni"), rst.getString("direccion"), rst.getString("telefono"), rst.getFloat("saldo"), false, false);
                resultado.add(i);
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmInversores.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    //Obtiene una lista de empresas con la autorizacion a false
    public ArrayList<Empresa> obtenerEmpresaPorAutorizacion() {
        ArrayList<Empresa> resultado = new ArrayList<>();
        PreparedStatement stmEmpresas = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from empresa natural join usuario where autorizado = false";

        try {
            stmEmpresas = con.prepareStatement(consulta);

            rst = stmEmpresas.executeQuery();
            while (rst.next()) {
                Empresa e = new Empresa(rst.getString("id_usuario"), rst.getString("nombrecomercial"), rst.getString("cif"), rst.getFloat("saldo"), rst.getFloat("saldobloqueado"), rst.getString("direccion"), rst.getString("telefono"), false, false);
                resultado.add(e);
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmEmpresas.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    //Obtiene una lista de empresas que se quieren dar de baja
    public ArrayList<Empresa> obtenerEmpresaBaja() {
        ArrayList<Empresa> resultado = new ArrayList<>();
        PreparedStatement stmEmpresas = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from empresa natural join usuario where solicitadoBaja = true";

        try {
            stmEmpresas = con.prepareStatement(consulta);
            rst = stmEmpresas.executeQuery();
            while (rst.next()) {
                Empresa e = new Empresa(rst.getString("id_usuario"), rst.getString("nombrecomercial"), rst.getString("cif"), rst.getFloat("saldo"), rst.getFloat("saldobloqueado"), rst.getString("direccion"), rst.getString("telefono"), true, true);
                resultado.add(e);
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmEmpresas.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    //Obtiene una lista de inversores que se quieren dar de baja
    public ArrayList<Inversor> obtenerInversorBaja() {
        ArrayList<Inversor> resultado = new ArrayList<>();
        PreparedStatement stmEmpresas = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from inversor natural join usuario where solicitadoBaja = true";

        try {
            stmEmpresas = con.prepareStatement(consulta);
            rst = stmEmpresas.executeQuery();
            while (rst.next()) {
                Inversor e = new Inversor(rst.getString("id_usuario"), rst.getString("nombre"), rst.getString("dni"), rst.getString("direccion"), rst.getString("telefono"), rst.getFloat("saldo"), true, true);
                resultado.add(e);
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmEmpresas.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    //Funcion para autorizar el registro de un usuario en la aplicacion
    public void autorizarUsuario(String id_usuario) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "update usuario "
                + "set autorizado = true"
                + " where id_usuario = ?";
        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, id_usuario);
            stm.executeUpdate();
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    /*TODO: Funciones para autorizar la baja del usuario,
      si el usuario tiene participaciones se cancela la baja y si tiene el saldo en un valor distinto de 0
      entonces se pone a 0 y se tramita la baja, sql??? */


    public void modificarDatosEmpresa(String id_usuario, Empresa e) {
        PreparedStatement stmEmpresas = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta1 = "update empresa "
                + "set id_usuario = ?,"
                + " nombrecomercial = ?,"
                + " cif = ?,"
                + " direccion = ?,"
                + " telefono = ?,"
                + " autorizado = ? "
                + " where id_usuario = ?";

        String consulta2 = "update usuario "
                + "set id_usuario = ?,"
                + " clave = ?"
                + " where id_usuario = ?";

        try {
            stmEmpresas = con.prepareStatement(consulta1);
            stmEmpresas.setString(1, e.getIdUsuario());
            stmEmpresas.setString(2, e.getNombre());
            stmEmpresas.setString(3, e.getCIF());
            stmEmpresas.setString(4, e.getDireccion());
            stmEmpresas.setString(5, e.getTelefono());
            stmEmpresas.setBoolean(6, e.isAutorizado());
            stmEmpresas.setString(7, id_usuario);

            stmEmpresas.executeUpdate();

            stmEmpresas = con.prepareStatement(consulta2);
            Usuario u = (Usuario) e;
            stmEmpresas.setString(1, u.getIdUsuario());
            stmEmpresas.setString(2, u.getClave());
            stmEmpresas.setString(3, id_usuario);

            stmEmpresas.executeUpdate();
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmEmpresas.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

    }

    public void modificarDatosInversor(String id_usuario, Inversor e) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta1 = "update inversor "
                + "set id_usuario = ?,"
                + " nombre = ?,"
                + " dni = ?,"
                + " direccion = ?,"
                + " telefono = ?,"
                + " autorizado = ? "
                + " where id_usuario = ?";

        String consulta2 = "update usuario "
                + "set id_usuario = ?,"
                + " clave = ?"
                + " where id_usuario = ?";


        try {
            stm = con.prepareStatement(consulta1);
            stm.setString(1, e.getIdUsuario());
            stm.setString(2, e.getNombre());
            stm.setString(3, e.getDni());
            stm.setString(4, e.getDireccion());
            stm.setString(5, e.getTelefono());
            stm.setBoolean(6, e.isAutorizado());
            stm.setString(7, id_usuario);

            stm.executeUpdate();

            stm = con.prepareStatement(consulta2);
            stm.setString(1, e.getIdUsuario());
            stm.setString(2, e.getClave());
            stm.setString(3, id_usuario);

            stm.executeUpdate();

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
    
    public java.util.List<OfertaVenta> getOfertasVenta(String empresa, int precioMaximoPart){
        java.util.List<OfertaVenta> resultado = new java.util.ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();
        
        String consulta = "select * "
                + "from ofertaVenta "
                + "where empresa like ? AND "
                + "precio <= ?"; // cambiado >= por <= (queremos las que cuesten menos o igual que el precio maximo dado
        
        try {
            stm = con.prepareStatement(consulta);
            empresa = "%" + empresa + "%";
            stm.setString(1, empresa);
            stm.setInt(2, precioMaximoPart);
            rst = stm.executeQuery();
            
            while (rst.next()) {
                //OfertaVenta(String usuario, String empresa, Date fecha, Integer numParticipaciones, Double precio)
                OfertaVenta v = new OfertaVenta(rst.getString("usuario"), rst.getString("empresa"), rst.getDate("fecha"), rst.getInt("numParticipaciones"), rst.getDouble("precio"));
                
                resultado.add(v);
                
            }

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
        return resultado;
        
        
    }


    //TODO como se hace para que si falla la primera consulta se deshaga la primera??
    public void eliminarInversor(String idUsuario) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta1 = "delete from inversor "
                + "where id_usuario = ?";
        String consulta2 = "delete from usuario "
                + "where id_usuario = ?";
        try {
            stm = con.prepareStatement(consulta1);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

            stm = con.prepareStatement(consulta2);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void eliminarEmpresa(String idUsuario) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta1 = "delete from empresa "
                + "where id_usuario = ?";
        String consulta2 = "delete from usuario "
                + "where id_usuario = ?";
        try {
            stm = con.prepareStatement(consulta1);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

            stm = con.prepareStatement(consulta2);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void solicitarBaja(String idUsuario) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "update usuario "
                + "set solicitadobaja = true"
                + " where id_usuario = ?";
        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, idUsuario);
            stm.executeUpdate();
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
}
