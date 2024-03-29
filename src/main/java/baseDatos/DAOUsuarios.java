package baseDatos;

import aplicacion.AnuncioBeneficios;
import aplicacion.Empresa;
import aplicacion.EntradaHistorial;
import aplicacion.Inversor;
import aplicacion.OfertaVenta;
import aplicacion.Regulador;
import aplicacion.Usuario;
import vista.componentes.DialogoInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;

import vista.FachadaGUI;
import vista.VentanaConfirmacion;

public class DAOUsuarios extends AbstractDAO {

    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    /**
     * Comprueba si el usuario con ese id y clave está en la base de datos y
     * devuelve su objeto asocidado El objeto Usuario devuelto ya tiene un tipo
     * asociado y todos sus campos rellenados!
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
                    + "where id_usuario = ? and clave = crypt(?, clave)");
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
            if (result == null) {
                result = obtenerDatosInversor(usuarioValidado);
            }
            if (result == null) {
                result = obtenerDatosRegulador(usuarioValidado);
            }

        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                if (stmUsuario != null) {
                    stmUsuario.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return result;
    }

    /**
     * Obtiene una lista de usuarios en los que tan solo se conocerá su nombre
     * de usuario y si están autorizados o con solicitud de baja Para obtener
     * una lista parametrizada con los atributos completos de cada tipo de
     * usuarios usar los métodos obtenerEmpresa/Inversor/Regulador()
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
        java.util.List<Usuario> resultado = new java.util.ArrayList<>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();
        // esto es obviamente muy poco seguro pero al ser un método privado no hace falta complicarse mucho
        String consulta = "select id_usuario, autorizado, solicitadobaja "
                + "from usuario";

        if (!tipo.equals("usuario")) {
            consulta = "select id_usuario, autorizado, solicitadobaja "
                    + "from usuario natural join " + tipo;
        }

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
                if (stmCatalogo != null) {
                    stmCatalogo.close();
                }
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
                if (stmCatalogo != null) {
                    stmCatalogo.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    /**
     * Obtiene los datos concretos del usuario si este es una empresa
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
                        rsEmpresas.getInt("participacionesbloqueadas"),
                        rsEmpresas.getString("direccion"),
                        rsEmpresas.getString("telefono"),
                        user.isSolicitadobaja(),
                        user.isAutorizado());
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                if (stmCatalogo != null) {
                    stmCatalogo.close();
                }
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

                resultado = new Regulador(rsUsuarios.getString("id_usuario"), user.isSolicitadobaja(), user.isAutorizado(),
                        rsUsuarios.getFloat("saldo"), rsUsuarios.getFloat("comision"));

            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                if (stmCatalogo != null) {
                    stmCatalogo.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public boolean registroInversor(Inversor i) {
        PreparedStatement stmCheck = null;
        PreparedStatement stmIns = null;
        ResultSet rst;
        Connection con;
        boolean done = false, posibleInsertar = false;

        con = this.getConexion();

        String consulta = "select * from usuario where id_usuario=?"; //pillo t0do donde el ID sea el mismo (solo quiero el RST para saber si es vacio)

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, i.getIdUsuario());
            rst = stmCheck.executeQuery();

            if (!rst.isBeforeFirst()) {//si el rst esta vacio no hay nadie con su id, podemos insertar
                posibleInsertar = true;
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                if (stmCheck != null) {
                    stmCheck.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        if (posibleInsertar) {
            try {
                con.setAutoCommit(false);

                consulta = "insert into usuario(id_usuario, clave, autorizado, solicitadobaja) values (?, crypt(?, gen_salt('bf', 4)),?,?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, i.getIdUsuario());
                stmIns.setString(2, i.getClave());
                stmIns.setBoolean(3, false);
                stmIns.setBoolean(4, false);
                stmIns.executeUpdate();

                consulta = "insert into inversor(id_usuario, nombre, saldo, dni, direccion, telefono) values (?,?,?,?,?,?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, i.getIdUsuario());
                stmIns.setString(2, i.getNombre());
                stmIns.setDouble(3, i.getSaldo());
                stmIns.setString(4, i.getDni());
                if (i.getDireccion().isEmpty()) { //si el campo estaba vacio, coloco null (puede ser interesante para coalesces y funciones que buscan null)
                    stmIns.setString(5, null);
                } else {
                    stmIns.setString(5, i.getDireccion());
                }
                if (i.getTelefono().isEmpty()) {
                    stmIns.setString(6, null);
                } else {
                    stmIns.setString(6, i.getTelefono());
                }
                stmIns.executeUpdate();

                done = true;
            } catch (SQLException e) {
                manejarExcepcionSQL(e);
            } finally {
                try {
                    if (done) {
                        con.commit();
                    } else {
                        con.rollback();
                    }
                    if (stmIns != null) {
                        stmIns.close();
                    }
                    con.setAutoCommit(true);
                } catch (SQLException e) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
        }

        return posibleInsertar;//para que la funcion sepa si se ha insertado o no
    }

    //funcion para insertar un nuevo inversor, totalmente analoga a la de usuario
    public boolean registroEmpresa(Empresa e) {
        PreparedStatement stmCheck = null;
        PreparedStatement stmIns = null;
        ResultSet rst;
        Connection con;
        boolean done = false, posibleInsertar = false;
        con = this.getConexion();

        String consulta = "select * from usuario where id_usuario=?"; //pillo t0do donde el ID sea el mismo (solo quiero el RST para saber si es vacio)

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, e.getIdUsuario());
            rst = stmCheck.executeQuery();

            if (!rst.isBeforeFirst()) {//si el rst esta vacio no hay nadie con su id, podemos insertar
                posibleInsertar = true;
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmCheck != null) {
                    stmCheck.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        if (posibleInsertar) {
            try {
                con.setAutoCommit(false);

                consulta = "insert into usuario(id_usuario, clave, autorizado, solicitadobaja) values (?, crypt(?, gen_salt('bf', 4)), ?, ?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, e.getIdUsuario());
                stmIns.setString(2, e.getClave());
                stmIns.setBoolean(3, false);
                stmIns.setBoolean(4, false);
                stmIns.executeUpdate();

                consulta = "insert into empresa(id_usuario, nombrecomercial, cif, saldo, saldobloqueado, direccion, telefono) values (?,?,?,?,?,?,?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, e.getIdUsuario());
                stmIns.setString(2, e.getNombre());
                stmIns.setString(3, e.getCIF());
                stmIns.setDouble(4, e.getSaldo());
                stmIns.setDouble(5, e.getSaldobloqueado());
                if (e.getDireccion().isEmpty()) { //si el campo estaba vacio, coloco null (puede ser interesante para coalesces y funciones que buscan null)
                    stmIns.setString(6, null);
                } else {
                    stmIns.setString(6, e.getDireccion());
                }
                if (e.getTelefono().isEmpty()) {
                    stmIns.setString(7, null);
                } else {
                    stmIns.setString(7, e.getTelefono());
                }
                stmIns.executeUpdate();

                done = true;
            } catch (SQLException ex) {
                manejarExcepcionSQL(ex);
            } finally {
                try {
                    if (done) {
                        con.commit();
                    } else {
                        con.rollback();
                    }
                    if (stmIns != null) {
                        stmIns.close();
                    }
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
        }
        return done; //para que la funcion sepa si se ha insertado o no
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
                if (stmInversores != null) {
                    stmInversores.close();
                }
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
                Empresa e = new Empresa(rst.getString("id_usuario"), rst.getString("nombrecomercial"),
                        rst.getString("cif"), rst.getFloat("saldo"),
                        rst.getFloat("saldobloqueado"), rst.getInt("participacionesbloqueadas"),
                        rst.getString("direccion"), rst.getString("telefono"), false, false);
                resultado.add(e);
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmEmpresas != null) {
                    stmEmpresas.close();
                }
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
                Empresa e = new Empresa(rst.getString("id_usuario"), rst.getString("nombrecomercial"),
                        rst.getString("cif"), rst.getFloat("saldo"), rst.getFloat("saldobloqueado"),
                        rst.getInt("participacionesbloqueadas"),
                        rst.getString("direccion"), rst.getString("telefono"), true, true);
                resultado.add(e);
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmEmpresas != null) {
                    stmEmpresas.close();
                }
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
                if (stmEmpresas != null) {
                    stmEmpresas.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    //Funcion para autorizar el registro de un usuario en la aplicacion
    public void autorizarUsuario(String idUsuario) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "update usuario "
                + "set autorizado = true"
                + " where id_usuario = ?";
        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void modificarDatosEmpresa(String idUsuario, Empresa e) {
        PreparedStatement stmEmpresas = null;
        ResultSet rst;
        Connection con;
        boolean done = false;
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
                + " clave = crypt(?, gen_salt('bf', 4)),"
                + " where id_usuario = ?";

        try {
            con.setAutoCommit(false);
            stmEmpresas = con.prepareStatement(consulta1);
            stmEmpresas.setString(1, e.getIdUsuario());
            stmEmpresas.setString(2, e.getNombre());
            stmEmpresas.setString(3, e.getCIF());
            stmEmpresas.setString(4, e.getDireccion());
            stmEmpresas.setString(5, e.getTelefono());
            stmEmpresas.setBoolean(6, e.isAutorizado());
            stmEmpresas.setString(7, idUsuario);

            stmEmpresas.executeUpdate();

            stmEmpresas = con.prepareStatement(consulta2);
            stmEmpresas.setString(1, ((Usuario) e).getIdUsuario());
            stmEmpresas.setString(2, ((Usuario) e).getClave());
            stmEmpresas.setString(3, idUsuario);

            stmEmpresas.executeUpdate();

            done = true;
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }
                if (stmEmpresas != null) {
                    stmEmpresas.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

    }

    public void modificarDatosInversor(String idUsuario, Inversor e) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;
        boolean done = false;
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
                + " clave = crypt(?, gen_salt('bf', 4)),"
                + " where id_usuario = ?";

        try {
            con.setAutoCommit(false);
            stm = con.prepareStatement(consulta1);
            stm.setString(1, e.getIdUsuario());
            stm.setString(2, e.getNombre());
            stm.setString(3, e.getDni());
            stm.setString(4, e.getDireccion());
            stm.setString(5, e.getTelefono());
            stm.setBoolean(6, e.isAutorizado());
            stm.setString(7, idUsuario);

            stm.executeUpdate();

            stm = con.prepareStatement(consulta2);
            stm.setString(1, e.getIdUsuario());
            stm.setString(2, e.getClave());
            stm.setString(3, idUsuario);

            stm.executeUpdate();

            done = true;
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }
                if (stm != null) {
                    stm.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public java.util.List<OfertaVenta> getOfertasVenta(String empresa, float precioMaximoPart) {
        java.util.List<OfertaVenta> resultado = new java.util.ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;
        float comision;
        try {
            comision = fa.obtenerComision(fa.obtenerListaReguladores().get(0).getIdUsuario());
        } catch (Exception e) {
            comision = 0.0f;
        }
        con = this.getConexion();

        String consulta = "select * "
                + "from ofertaVenta "
                + "where empresa like ? AND "
                + "precio * ? <= ?"; // cambiado >= por <= (queremos las que cuesten menos o igual que el precio maximo dado

        //Para obtener la comisión y guardarlo en las ofertas de venta
        //Problema, podría colarse el dato y aparecer en la VEmpresa. 
        try {
            stm = con.prepareStatement(consulta);
            empresa = "%" + empresa + "%";
            stm.setString(1, empresa);
            stm.setFloat(2, comision + 1.0f);
            stm.setFloat(3, precioMaximoPart);
            rst = stm.executeQuery();
            while (rst.next()) {
                //OfertaVenta(String usuario, String empresa, Date fecha, Integer numParticipaciones, Double precio)
                OfertaVenta v = new OfertaVenta(rst.getString("usuario"), rst.getString("empresa"), rst.getTimestamp("fecha"), rst.getInt("numParticipaciones"), rst.getFloat("precio"), comision);
                resultado.add(v);
            }

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }

            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public java.util.List<OfertaVenta> getOfertasVentaPropias(String idUsuario) {
        java.util.List<OfertaVenta> resultado = new java.util.ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * "
                + "from ofertaVenta "
                + "where usuario like ? ";

        try {
            stm = con.prepareStatement(consulta);
            idUsuario = "%" + idUsuario + "%";
            stm.setString(1, idUsuario);

            rst = stm.executeQuery();

            float comision = this.obtenerComision(this.obtenerListaReguladores().get(0).getIdUsuario());
            while (rst.next()) {
                //OfertaVenta(String usuario, String empresa, Date fecha, Integer numParticipaciones, Double precio)
                OfertaVenta v = new OfertaVenta(rst.getString("usuario"), rst.getString("empresa"), rst.getTimestamp("fecha"), rst.getInt("numParticipaciones"), rst.getFloat("precio"), comision);
                resultado.add(v);

            }

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }

            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public void eliminarInversor(String idUsuario, float saldo) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;
        boolean done = false;
        con = this.getConexion();

        String consulta1 = "delete from inversor "
                + "where id_usuario = ?";
        String consulta2 = "delete from usuario "
                + "where id_usuario = ?";
        try {
            con.setAutoCommit(false);
            stm = con.prepareStatement(consulta1);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

            stm = con.prepareStatement(consulta2);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

            done = true;

            if (saldo != 0.0f) {
                new VentanaConfirmacion(FachadaGUI.getInstance().getVentanaActiva(), con, "Esta baja supondrá que se pierda el dinero del usuario ¿Está seguro?", "La baja se ha completado correctamente!",
                        "La baja se ha cancelado correctamente...");
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }

                if (stm != null) {
                    stm.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void eliminarEmpresa(String idUsuario, float saldo) {
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;
        boolean done = false;
        con = this.getConexion();

        String consulta1 = "delete from empresa "
                + "where id_usuario = ?";
        String consulta2 = "delete from usuario "
                + "where id_usuario = ?";
        try {
            con.setAutoCommit(false);
            stm = con.prepareStatement(consulta1);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

            stm = con.prepareStatement(consulta2);
            stm.setString(1, idUsuario);
            stm.executeUpdate();

            done = true;

            if (saldo != 0.0f) {
                new VentanaConfirmacion(FachadaGUI.getInstance().getVentanaActiva(), con, "Esta baja supondrá que se pierda el dinero del usuario ¿Está seguro?", "La baja se ha completado correctamente!",
                        "La baja se ha cancelado correctamente...");
            }


        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }

                if (stm != null) {
                    stm.close();
                }
                con.setAutoCommit(true);
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
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public java.util.List<AnuncioBeneficios> obtenerAnuncios(String empresa) {
        java.util.List<AnuncioBeneficios> resultado = new java.util.ArrayList<>();
        Connection con;
        PreparedStatement stmConsulta = null;
        ResultSet rstConsulta;
        AnuncioBeneficios aux;

        String consulta = "select * from anunciobeneficios where empresa= ? ";

        con = this.getConexion();
        try {
            stmConsulta = con.prepareStatement(consulta);
            stmConsulta.setString(1, empresa);
            rstConsulta = stmConsulta.executeQuery();
            while (rstConsulta.next()) {
                if (rstConsulta.getString("solicitadobaja") == null) {
                    //AnuncioBeneficios(String empresa, Timestamp fechaPago, Timestamp fechaAnuncio, Float importeparticipacion,boolean solicitadobaja, int numeroParticipaciones)
                    aux = new AnuncioBeneficios(rstConsulta.getString("empresa"),
                            rstConsulta.getTimestamp("fechapago"),
                            rstConsulta.getTimestamp("fechaanuncio"),
                            rstConsulta.getFloat("importeparticipacion"),
                            false,
                            rstConsulta.getInt("numeroparticipaciones"));
                } else {
                    aux = new AnuncioBeneficios(rstConsulta.getString("empresa"),
                            rstConsulta.getTimestamp("fechapago"),
                            rstConsulta.getTimestamp("fechaanuncio"),
                            rstConsulta.getFloat("importeparticipacion"),
                            rstConsulta.getBoolean("solicitadobaja"),
                            rstConsulta.getInt("numeroparticipaciones"));
                }
                resultado.add(aux);
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmConsulta != null) {
                    stmConsulta.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    //Función para obtener los anuncios con una solicitud de baja
    public java.util.List<AnuncioBeneficios> obtenerAnunciosRegulador() {
        java.util.List<AnuncioBeneficios> resultado = new java.util.ArrayList<>();
        Connection con;
        PreparedStatement stmConsulta = null;
        ResultSet rstConsulta;
        AnuncioBeneficios aux;

        String consulta = "select * from anunciobeneficios where solicitadobaja=true ";

        con = this.getConexion();
        try {
            stmConsulta = con.prepareStatement(consulta);
            rstConsulta = stmConsulta.executeQuery();
            while (rstConsulta.next()) {
                aux = new AnuncioBeneficios(rstConsulta.getString("empresa"),
                        rstConsulta.getTimestamp("fechapago"),
                        rstConsulta.getTimestamp("fechaanuncio"),
                        rstConsulta.getFloat("importeparticipacion"),
                        rstConsulta.getBoolean("solicitadobaja"),
                        rstConsulta.getInt("numeroparticipaciones"));
                resultado.add(aux);
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmConsulta != null) {
                    stmConsulta.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    //Función para crear un anuncio de beneficios
    //Devuelve 1 si se ha creado correcetamente, 2 si el importe es insuficiente, y 3 si
    //las participaciones no son suficientes para afrontar el pago.
    public int crearAnuncio(Float importe, Empresa e, Timestamp fechaPago, Integer numeroParticipaciones) {
        Connection con;
        PreparedStatement stmAnunciar1 = null;
        PreparedStatement stmAnunciar2 = null;
        PreparedStatement stmAnunciar3 = null;
        PreparedStatement stmBloquear = null;
        PreparedStatement stmBloquearParticipaciones1 = null;
        PreparedStatement stmBloquearParticipaciones2 = null;

        int resultado = 0;
        boolean done = false;


        String consulta3 = "Insert into anunciobeneficios  (empresa,fechapago,fechaanuncio,importeparticipacion,numeroparticipaciones,solicitadobaja) "
                + "values (?,?,?,?,?,false)";

        String consulta4 = "update empresa set saldobloqueado=saldobloqueado + ? ,saldo=saldo - ? where id_usuario= ? ";

        String consulta5 = "update empresa set participacionesbloqueadas=participacionesbloqueadas+ ?  where id_usuario= ? ";

        String consulta6 = "update participacionesempresa set numparticipaciones=numparticipaciones- ?  "
                + "where usuario= ? and empresa= ? ";


        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        //Diferenciamos 3 casos
        //El primer es pagar únicamente con participaciones
        if (numeroParticipaciones != 0 && importe == 0.0f) {
            if (fa.getPartPropEmpresa(e) < (numeroParticipaciones * this.participacionesVendidas(e.getIdUsuario()))) {
                resultado = 3;
            } else {
                con = this.getConexion();
                try {
                    con.setAutoCommit(false);
                    stmAnunciar1 = con.prepareStatement(consulta3);
                    stmAnunciar1.setString(1, e.getIdUsuario());
                    stmAnunciar1.setTimestamp(2, fechaPago);
                    stmAnunciar1.setTimestamp(3, fechaActual);
                    stmAnunciar1.setInt(4, 0);
                    stmAnunciar1.setInt(5, numeroParticipaciones);
                    stmAnunciar1.executeUpdate();

                    stmBloquearParticipaciones1 = con.prepareStatement(consulta5);
                    stmBloquearParticipaciones1.setInt(1, numeroParticipaciones * this.participacionesVendidas(e.getIdUsuario()));
                    stmBloquearParticipaciones1.setString(2, e.getIdUsuario());
                    stmBloquearParticipaciones1.executeUpdate();

                    stmBloquearParticipaciones2 = con.prepareStatement(consulta6);
                    stmBloquearParticipaciones2.setInt(1, numeroParticipaciones * this.participacionesVendidas(e.getIdUsuario()));
                    stmBloquearParticipaciones2.setString(2, e.getIdUsuario());
                    stmBloquearParticipaciones2.setString(3, e.getIdUsuario());
                    stmBloquearParticipaciones2.executeUpdate();
                    fa.insertarHistorial(new EntradaHistorial(e.getIdUsuario(), e.getIdUsuario(),
                            new Timestamp(System.currentTimeMillis()), numeroParticipaciones, null, EntradaHistorial.TipoEntradaHistorial.BENEFICIOS));

                    resultado = 1;
                    done = true;
                } catch (SQLException ex) {
                    manejarExcepcionSQL(ex);
                } finally {
                    try {
                        if (done) {
                            con.commit();
                        } else {
                            con.rollback();
                        }
                        if (stmAnunciar1 != null) {
                            stmAnunciar1.close();
                        }
                        if (stmBloquearParticipaciones1 != null) {
                            stmBloquearParticipaciones1.close();
                        }
                        if (stmBloquearParticipaciones2 != null) {
                            stmBloquearParticipaciones2.close();
                        }
                        con.setAutoCommit(true);
                    } catch (SQLException ex) {
                        System.out.println("Imposible cerrar cursores");
                    }
                }
            }
        } //Segundo caso, solo importe
        else if (numeroParticipaciones == 0 && importe >= 0) {
            if (importe * this.participacionesVendidas(e.getIdUsuario()) > this.comprobarSaldoEmpresa(e.getIdUsuario())) {
                resultado = 2;
            } else {
                con = this.getConexion();
                try {
                    con.setAutoCommit(false);
                    stmAnunciar2 = con.prepareStatement(consulta3);
                    stmAnunciar2.setString(1, e.getIdUsuario());
                    stmAnunciar2.setTimestamp(2, fechaPago);
                    stmAnunciar2.setTimestamp(3, fechaActual);
                    stmAnunciar2.setFloat(4, importe);
                    stmAnunciar2.setInt(5, 0);
                    stmAnunciar2.executeUpdate();

                    stmBloquear = con.prepareStatement(consulta4);
                    stmBloquear.setFloat(1, importe * this.participacionesVendidas(e.getIdUsuario()));
                    stmBloquear.setFloat(2, importe * this.participacionesVendidas(e.getIdUsuario()));
                    stmBloquear.setString(3, e.getIdUsuario());
                    stmBloquear.executeUpdate();

                    fa.insertarHistorial(new EntradaHistorial(e.getIdUsuario(), e.getIdUsuario(),
                            new Timestamp(System.currentTimeMillis()), null, importe, EntradaHistorial.TipoEntradaHistorial.BENEFICIOS));

                    resultado = 1;
                    done = true;
                } catch (SQLException ex) {
                    manejarExcepcionSQL(ex);
                } finally {

                    try {
                        if (done) {
                            con.commit();
                        } else {
                            con.rollback();
                        }
                        if (stmAnunciar2 != null) {
                            stmAnunciar2.close();
                        }
                        if (stmBloquear != null) {
                            stmBloquear.close();
                        }
                        con.setAutoCommit(true);
                    } catch (SQLException ex) {
                        System.out.println("Imposible cerrar cursores");
                    }
                }
            }
        } //Tercer caso, con importe y participaciones.
        //Se comprueban tanto que el importe sea suficiente como que las participaciones sean suficientes.
        else {
            if ((importe * participacionesVendidas(e.getIdUsuario())) > this.comprobarSaldoEmpresa(e.getIdUsuario())) {
                resultado = 2;
            } else if (fa.getParticipacionesEmpresa(e, e) < (numeroParticipaciones * this.participacionesVendidas(e.getIdUsuario()))) {
                resultado = 3;
            } else {
                con = this.getConexion();
                try {
                    con.setAutoCommit(false);
                    stmAnunciar3 = con.prepareStatement(consulta3);
                    stmAnunciar3.setString(1, e.getIdUsuario());
                    stmAnunciar3.setTimestamp(2, fechaPago);
                    stmAnunciar3.setTimestamp(3, fechaActual);
                    stmAnunciar3.setFloat(4, importe);
                    stmAnunciar3.setInt(5, numeroParticipaciones);
                    stmAnunciar3.executeUpdate();

                    stmBloquear = con.prepareStatement(consulta4);
                    stmBloquear.setFloat(1, importe * this.participacionesVendidas(e.getIdUsuario()));
                    stmBloquear.setFloat(2, importe * this.participacionesVendidas(e.getIdUsuario()));
                    stmBloquear.setString(3, e.getIdUsuario());
                    stmBloquear.executeUpdate();

                    stmBloquearParticipaciones1 = con.prepareStatement(consulta5);
                    stmBloquearParticipaciones1.setInt(1, numeroParticipaciones * this.participacionesVendidas(e.getIdUsuario()));
                    stmBloquearParticipaciones1.setString(2, e.getIdUsuario());
                    stmBloquearParticipaciones1.executeUpdate();

                    stmBloquearParticipaciones2 = con.prepareStatement(consulta6);
                    stmBloquearParticipaciones2.setInt(1, numeroParticipaciones * this.participacionesVendidas(e.getIdUsuario()));
                    stmBloquearParticipaciones2.setString(2, e.getIdUsuario());
                    stmBloquearParticipaciones2.setString(3, e.getIdUsuario());
                    stmBloquearParticipaciones2.executeUpdate();
                    fa.insertarHistorial(new EntradaHistorial(e.getIdUsuario(), e.getIdUsuario(),
                            new Timestamp(System.currentTimeMillis()), numeroParticipaciones, importe, EntradaHistorial.TipoEntradaHistorial.BENEFICIOS));

                    resultado = 1;
                    done = true;
                } catch (SQLException ex) {
                    manejarExcepcionSQL(ex);
                } finally {
                    try {
                        if (done) {
                            con.commit();
                        } else {
                            con.rollback();
                        }

                        if (stmAnunciar3 != null) {
                            stmAnunciar3.close();
                        }
                        if (stmBloquear != null) {
                            stmBloquear.close();
                        }
                        if (stmBloquearParticipaciones1 != null) {
                            stmBloquearParticipaciones1.close();
                        }
                        if (stmBloquearParticipaciones2 != null) {
                            stmBloquearParticipaciones2.close();
                        }
                        //con.setAutoCommit(true);
                    } catch (SQLException ex) {
                        System.out.println("Imposible cerrar cursores");
                    }
                }
            }
        }
        return resultado;
    }


    //Función para dar de baja un anuncio de la base de datos
    public void bajaAnuncio(String empresa, Timestamp fecha, Float importe, Integer numparticipaciones) {
        Connection con;
        PreparedStatement stmResta = null;
        PreparedStatement stmSuma = null;
        PreparedStatement stmBaja = null;
        PreparedStatement stmSumaParticipaciones = null;

        //Statements para cuando se retiren todos los anuncios de una empresa 
        PreparedStatement stmBloqueado = null;
        ResultSet rstBloqueado;
        int participaciones = 0;
        float imp = 0.f;

        PreparedStatement stmDevolver = null;
        PreparedStatement stmDevolver2 = null;


        boolean done = false;
        //Consulta para quitar el importe del saldo bloqueado de la empresa y las participaciones bloqueadas 
        String consulta1 = "update empresa set saldobloqueado=saldobloqueado- ?"
                + ",participacionesbloqueadas=participacionesbloqueadas- ?  where id_usuario= ? ";

        //Consulta para sumar el importe al salod
        String consulta2 = "update empresa set saldo=saldo+ ? where id_usuario= ? ";
        //Consulta para borrar el anuncio
        String consulta3 = "delete from anunciobeneficios where empresa= ? and fechapago= ? ";

        //Consulta para sumar las participaciones a la cartera de empredsa 

        String consulta4 = "update participacionesempresa set numparticipaciones=numparticipaciones + ? "
                + " where usuario = ? and empresa = ?";

        //Consulta para desbloquear importe y participaciones en caso de que sea necesario 

        String consulta5 = "select participacionesbloqueadas, saldobloqueado from empresa where id_usuario = ? ";

        //Consultas para actualizar datos

        String consulta6 = "update empresa set saldo = saldo + ?, saldobloqueado = saldobloqueado - ?,"
                + " participacionesbloqueadas=participacionesbloqueadas - ? where id_usuario = ? ";

        String consulta7 = "update participacionesempresa set numparticipaciones = numparticipaciones + ? where usuario = ? and empresa = ? ";


        con = this.getConexion();

        try {
            con.setAutoCommit(false);
            //Se resta de saldobloqueado
            stmResta = con.prepareStatement(consulta1);
            stmResta.setFloat(1, importe * this.participacionesVendidas(empresa));
            stmResta.setInt(2, numparticipaciones * this.participacionesVendidas(empresa));
            stmResta.setString(3, empresa);
            stmResta.executeUpdate();
            //Se suma a saldo
            stmSuma = con.prepareStatement(consulta2);
            stmSuma.setFloat(1, importe * this.participacionesVendidas(empresa));
            stmSuma.setString(2, empresa);
            stmSuma.executeUpdate();

            //Se suma a las participaciones de la cartera de empresa 

            stmSumaParticipaciones = con.prepareStatement(consulta4);
            stmSumaParticipaciones.setInt(1, numparticipaciones * this.participacionesVendidas(empresa));
            stmSumaParticipaciones.setString(2, empresa);
            stmSumaParticipaciones.setString(3, empresa);
            stmSumaParticipaciones.executeUpdate();

            //Se elimina el anuncio
            stmBaja = con.prepareStatement(consulta3);
            stmBaja.setString(1, empresa);
            stmBaja.setTimestamp(2, fecha);
            stmBaja.executeUpdate();


            //Si es el último anuncio y queda dinero o participacines bloqueadas, 
            //se pasan a saldo y cartera de participaciones 

            java.util.List<AnuncioBeneficios> aux = obtenerAnuncios(empresa);
            if (aux.size() == 0) {
                stmBloqueado = con.prepareStatement(consulta5);
                stmBloqueado.setString(1, empresa);
                rstBloqueado = stmBloqueado.executeQuery();
                while (rstBloqueado.next()) {
                    imp = rstBloqueado.getFloat("saldobloqueado");
                    participaciones = rstBloqueado.getInt("participacionesbloqueadas");
                }

                stmDevolver = con.prepareStatement(consulta6);
                stmDevolver.setFloat(1, imp);
                stmDevolver.setFloat(2, imp);
                stmDevolver.setFloat(3, participaciones);
                stmDevolver.setString(4, empresa);
                stmDevolver.executeUpdate();


                stmDevolver2 = con.prepareStatement(consulta7);
                stmDevolver2.setInt(1, participaciones);
                stmDevolver2.setString(2, empresa);
                stmDevolver2.setString(3, empresa);
                stmDevolver2.executeLargeUpdate();
            }


            fa.insertarHistorial(new EntradaHistorial(empresa, empresa,
                    new Timestamp(System.currentTimeMillis()), null, null, EntradaHistorial.TipoEntradaHistorial.BENEFICIOS));
            done = true;
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }
                if (stmBaja != null) {
                    stmBaja.close();
                }
                if (stmSuma != null) {
                    stmSuma.close();
                }
                if (stmResta != null) {
                    stmResta.close();
                }
                if (stmSumaParticipaciones != null) {
                    stmSumaParticipaciones.close();
                }
                if (stmBloqueado != null) {
                    stmBloqueado.close();
                }
                if (stmDevolver != null) {
                    stmDevolver.close();
                }
                if (stmDevolver2 != null) {
                    stmDevolver2.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

    }

    public boolean solicitarBajaAnuncio(String empresa, Timestamp fechaPago) {
        Connection con;
        PreparedStatement stmAnuncio = null;
        String consulta = "update anunciobeneficios set solicitadobaja=true where empresa= ?  and fechapago= ? ";
        con = this.getConexion();
        try {
            stmAnuncio = con.prepareStatement(consulta);
            stmAnuncio.setString(1, empresa);
            stmAnuncio.setTimestamp(2, fechaPago);
            stmAnuncio.executeUpdate();

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
            return false;
        } finally {
            try {
                if (stmAnuncio != null) {
                    stmAnuncio.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return true;

    }

    //Función para obtener el saldo de una empresa
    public float comprobarSaldoEmpresa(String empresa) {
        float resultado = 0.0f;

        Connection con;
        PreparedStatement stmConsulta = null;
        ResultSet rstResultado;

        String consulta = "select saldo from empresa where id_usuario= ?  ";

        con = this.getConexion();
        try {
            stmConsulta = con.prepareStatement(consulta);
            stmConsulta.setString(1, empresa);
            rstResultado = stmConsulta.executeQuery();
            while (rstResultado.next()) {
                resultado = rstResultado.getFloat("saldo");
            }

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);

        } finally {
            try {
                if (stmConsulta != null) {
                    stmConsulta.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    //Función para obtener el numer de participaciones que tiene vendidas la empresa
    public int participacionesVendidas(String empresa) {

        PreparedStatement stmConsulta = null;
        ResultSet rstResultado;
        Connection con;
        int resultado = 0;
        String consulta = "select sum(numparticipaciones)"
                + "from ( "
                + "    select pi.* "
                + "    from participacionesinversor as pi "
                + "    where  pi.empresa= ? "
                + "    union "
                + "    select pe.* "
                + "    from participacionesempresa as pe "
                + "    where  pe.empresa= ?) as resultado "
                + "where resultado.usuario!= ? ";
        con = this.getConexion();
        try {
            stmConsulta = con.prepareStatement(consulta);
            stmConsulta.setString(1, empresa);
            stmConsulta.setString(2, empresa);
            stmConsulta.setString(3, empresa);
            rstResultado = stmConsulta.executeQuery();
            while (rstResultado.next()) {
                resultado = rstResultado.getInt("sum");
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmConsulta != null) {
                    stmConsulta.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    //Función que paga los beneficios, con o sin anuncio previo.
    //Si el pago es sin anuncio, se llama a esta función con un null en anuncio
    public void pagarBeneficios(Float importe, Integer participacionesAPagarSinAnuncio, Empresa e, AnuncioBeneficios anuncio, Integer participacionesPropias) {
        //Para restar a empresa
        PreparedStatement stmImporte = null;
        PreparedStatement stmBloqueado = null;
        PreparedStatement stmParticipaciones = null;
        Connection con;
        boolean done = false;
        int participacionesVendidas = 0; //Variable que se va a usar para obtener el número de participaciones en varias consultas
        //ResultSet y statements para modificar los datos de inversores
        ResultSet rstMI;
        PreparedStatement stmMI = null;
        PreparedStatement stmSumaI = null;
        PreparedStatement stmSuma2I = null;

        //ResultSet y statements para modificar los datos de empresa
        ResultSet rstME;
        PreparedStatement stmME = null;
        PreparedStatement stmSumaE = null;
        PreparedStatement stmSuma2E = null;

        //Statement de eliminación de anuncio, con su consulta
        PreparedStatement stmEliminacion = null;


        String borrarAnuncio = "delete from anunciobeneficios where empresa= ?  and fechapago= ? ";

        // Consulta para actualizar el saldo de la empresa
        String actualizarSaldoEmpresa = "update empresa set saldo=saldo-? where id_usuario= ? ";

        // Consulta para actualizar las participaciones disponibles de la empresa
        String actualizarPartEmpresa = "update participacionesempresa set numparticipaciones=numparticipaciones- ?  "
                + "where empresa= ?  and usuario= ? ";

        // Consulta para actualizar el saldobloqueado y participacionesbloqueadas de la empresa si el pago es con un anuncio
        String actualizarValoresBloqueados = "update empresa set saldobloqueado=saldobloqueado- ?,"
                + " participacionesbloqueadas=participacionesbloqueadas - ?  where id_usuario= ? ";

        // Consulta para seleccionar a los inversores que tienen las participaciones de la empresa
        String seleccionarInvPartEmpresa = "select i.id_usuario,pi.numparticipaciones,pi.empresa "
                + "from participacionesinversor as pi,inversor as i, empresa as e "
                + "where pi.usuario=i.id_usuario and pi.empresa= ? "
                + "group by i.id_usuario,pi.numparticipaciones,pi.empresa";
        //Consulta para aumentar el saldo de inversor
        String aumentarSaldoInversor = "update inversor set saldo=saldo+? where id_usuario= ? ";
        //Consulta para aumentar las participaciones del inversor
        String aumentarPartInversor = "update participacionesinversor set numparticipaciones=numparticipaciones+ ? where empresa= ? ";

        // Consulta para seleccionar a las empresas que tienen las participaciones de la empresa
        String seleccionarEmpPartEmpresa = "select e.id_usuario,pe.numparticipaciones,pe.empresa "
                + "from participacionesempresa as pe,empresa as e "
                + "where pe.usuario=e.id_usuario and pe.empresa= ? and pe.usuario!= ? "
                + "group by e.id_usuario,pe.numparticipaciones,pe.empresa";
        //Consulta para aumentar el saldo de la empresa
        String aumentarSaldoEmpresa = "update empresa set saldo=saldo+? where id_usuario= ? ";
        //Consulta para aumentar las participaciones de la empresa
        String aumentarPartEmpresa = "update participacionesempresa set numparticipaciones=numparticipaciones+ ?  "
                + "where empresa= ? and usuario!= ? ";

        con = this.getConexion();

        /*
         * 2 pasos:
         * 1. Restar participaciones y dinero a la empresa, desbloquear el saldo/part si había anuncio
         * 2. Dar saldo/part a todos los inversores, empresas que tienen participaciones de la empresa
         */

        try {
            con.setAutoCommit(false);

            // PASO 1
            //Se comprueba el número de participaciones que tiene la empresa vendidas
            participacionesVendidas = this.participacionesVendidas(e.getIdUsuario());

            if (anuncio == null) { // No hay anuncio

                //Comprobación de que tiene el número de participaciones e importe suficiente para pagar
                e = this.obtenerDatosEmpresa(e);
                if (e.getSaldo() < importe * (float) participacionesVendidas) {
                    muestraExcepcion("El importe de la empresa no es suficiente para afrontarel pago\n\n", DialogoInfo.NivelDeAdvertencia.ERROR);
                    return;
                }
                if (participacionesPropias < participacionesAPagarSinAnuncio * participacionesVendidas) {
                    muestraExcepcion("El numero de participacione de la empresa no es suficiente para afrontarel pago\n\n", DialogoInfo.NivelDeAdvertencia.ERROR);
                    return;
                }

                //Ahora se resta el dinero en la empresa y participaciones de la empresa

                // Dinero
                float dinero = importe * (float) participacionesVendidas;
                stmImporte = con.prepareStatement(actualizarSaldoEmpresa);
                stmImporte.setFloat(1, dinero);
                stmImporte.setString(2, e.getIdUsuario());
                stmImporte.executeUpdate();

                // Participaciones
                int p = participacionesVendidas * participacionesAPagarSinAnuncio;
                stmParticipaciones = con.prepareStatement(actualizarPartEmpresa);
                stmParticipaciones.setInt(1, p);
                stmParticipaciones.setString(2, e.getIdUsuario());
                stmParticipaciones.setString(3, e.getIdUsuario());
                stmParticipaciones.executeUpdate();
            } else {
                // Desbloquear dinero y participaciones
                float dinero = anuncio.getImporteparticipacion() * (float) participacionesVendidas;
                int p = participacionesVendidas * anuncio.getNumeroparticipaciones();
                stmBloqueado = con.prepareStatement(actualizarValoresBloqueados);
                stmBloqueado.setFloat(1, dinero);
                stmBloqueado.setInt(2, p);
                stmBloqueado.setString(3, e.getIdUsuario());
                stmBloqueado.executeUpdate();
            }

            // PASO 2
            // Y ahora se suma a los usuarios que tenian las participaciones, tanto inversores como empresas
            // Hay que hacer dos consultas distintas, primero la de inversores
            if (anuncio == null) {
                // Dar dinero
                stmMI = con.prepareStatement(seleccionarInvPartEmpresa);
                stmSumaI = con.prepareStatement(aumentarSaldoInversor);
                stmMI.setString(1, e.getIdUsuario());
                rstMI = stmMI.executeQuery();
                float suma = 0;
                while (rstMI.next()) {
                    participacionesVendidas = rstMI.getInt("numparticipaciones");
                    suma = importe * participacionesVendidas;
                    stmSumaI.setFloat(1, suma);
                    stmSumaI.setString(2, rstMI.getString("id_usuario"));
                    stmSumaI.executeUpdate();
                }

                // Dar participaciones
                stmSuma2I = con.prepareStatement(aumentarPartInversor);
                stmSuma2I.setInt(1, participacionesAPagarSinAnuncio);
                stmSuma2I.setString(2, e.getIdUsuario());
                stmSuma2I.executeUpdate();
            } else {
                // Dar dinero
                stmMI = con.prepareStatement(seleccionarInvPartEmpresa);
                stmSumaI = con.prepareStatement(aumentarSaldoInversor);
                stmMI.setString(1, e.getIdUsuario());
                rstMI = stmMI.executeQuery();
                float suma = 0;
                while (rstMI.next()) {
                    participacionesVendidas = rstMI.getInt("numparticipaciones");
                    suma = anuncio.getImporteparticipacion() * participacionesVendidas;
                    stmSumaI.setFloat(1, suma);
                    stmSumaI.setString(2, rstMI.getString("id_usuario"));
                    stmSumaI.executeUpdate();

                }

                //Participaciones
                stmSuma2I = con.prepareStatement(aumentarPartInversor);
                stmSuma2I.setInt(1, anuncio.getNumeroparticipaciones());
                stmSuma2I.setString(2, e.getIdUsuario());
                stmSuma2I.executeUpdate();
            }

            //Y ahora la de empresas
            if (anuncio == null) {
                //Importe
                stmME = con.prepareStatement(seleccionarEmpPartEmpresa);
                stmSumaE = con.prepareStatement(aumentarSaldoEmpresa);
                stmME.setString(1, e.getIdUsuario());
                stmME.setString(2, e.getIdUsuario());
                rstME = stmME.executeQuery();
                float sum = 0;
                while (rstME.next()) {
                    participacionesVendidas = rstME.getInt("numparticipaciones");
                    sum = importe * participacionesVendidas;
                    stmSumaE.setFloat(1, sum);
                    stmSumaE.setString(2, rstME.getString("id_usuario"));
                    stmSumaE.executeUpdate();
                }
                //Participaciones
                stmSuma2E = con.prepareStatement(aumentarPartEmpresa);
                stmSuma2E.setInt(1, participacionesAPagarSinAnuncio);
                stmSuma2E.setString(2, e.getIdUsuario());
                stmSuma2E.setString(3, e.getIdUsuario());
                stmSuma2E.executeUpdate();

                fa.insertarHistorial(new EntradaHistorial(e.getIdUsuario(), e.getIdUsuario(),
                        new Timestamp(System.currentTimeMillis()), participacionesAPagarSinAnuncio * this.participacionesVendidas(e.getIdUsuario()),
                        importe * this.participacionesVendidas(e.getIdUsuario()), EntradaHistorial.TipoEntradaHistorial.PAGO));

            } else {
                //Importe
                stmME = con.prepareStatement(seleccionarEmpPartEmpresa);
                stmSumaE = con.prepareStatement(aumentarSaldoEmpresa);
                stmME.setString(1, e.getIdUsuario());
                stmME.setString(2, e.getIdUsuario());
                rstME = stmME.executeQuery();
                float sum = 0;
                while (rstME.next()) {
                    participacionesVendidas = rstME.getInt("numparticipaciones");
                    sum = anuncio.getImporteparticipacion() * participacionesVendidas;
                    stmSumaE.setFloat(1, sum);
                    stmSumaE.setString(2, rstME.getString("id_usuario"));
                    stmSumaE.executeUpdate();
                }
                //Participaciones
                stmSuma2E = con.prepareStatement(aumentarPartEmpresa);
                stmSuma2E.setInt(1, anuncio.getNumeroparticipaciones());
                stmSuma2E.setString(2, e.getIdUsuario());
                stmSuma2E.setString(3, e.getIdUsuario());
                stmSuma2E.executeUpdate();
                fa.insertarHistorial(new EntradaHistorial(e.getIdUsuario(), e.getIdUsuario(),
                        new Timestamp(System.currentTimeMillis()),
                        anuncio.getNumeroparticipaciones() * this.participacionesVendidas(e.getIdUsuario()),
                        anuncio.getImporteparticipacion() * this.participacionesVendidas(e.getIdUsuario()), EntradaHistorial.TipoEntradaHistorial.PAGO));
            }

            //Por último, si hay un anuncio, se elimina
            if (anuncio != null) {
                try {
                    stmEliminacion = con.prepareStatement(borrarAnuncio);
                    stmEliminacion.setString(1, e.getIdUsuario());
                    stmEliminacion.setTimestamp(2, anuncio.getFechaPago());
                    stmEliminacion.executeUpdate();

                } catch (SQLException ex) {
                    manejarExcepcionSQL(ex);

                } finally {
                    try {
                        if (stmEliminacion != null) {
                            stmEliminacion.close();
                        }
                    } catch (SQLException ex) {
                        System.out.println("Imposible cerrar cursores");
                    }
                }
            }
            muestraExcepcion("Pago realizado correctamente\n\n", DialogoInfo.NivelDeAdvertencia.INFORMACION);
            done = true;
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }
                if (stmImporte != null) {
                    stmImporte.close();
                }
                if (stmParticipaciones != null) {
                    stmParticipaciones.close();
                }
                if (stmMI != null) {
                    stmMI.close();
                }
                if (stmSuma2I != null) {
                    stmSuma2I.close();
                }
                if (stmME != null) {
                    stmME.close();
                }
                if (stmSumaE != null) {
                    stmSumaE.close();
                }
                if (stmSuma2E != null) {
                    stmSuma2E.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public boolean comprobarID(String id) {
        boolean libre = false;
        PreparedStatement stmCheck = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from usuario where id_usuario=?"; //pillo t0do donde el ID sea el mismo (solo quiero el RST para saber si es vacio)

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, id);
            rst = stmCheck.executeQuery();
            if (!rst.isBeforeFirst()) {//si el rst esta vacio no hay nadie con su id, podemos insertar
                libre = true;
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        } finally {
            try {
                if (stmCheck != null) {
                    stmCheck.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return libre;
    }

    public boolean modificarInversor(Inversor inversor, String pass, String idviejo) {
        PreparedStatement stmUpd = null;
        Connection con;
        boolean done = false;
        con = this.getConexion();
        String consultaPass;
        if (pass == null)
            consultaPass = "update usuario set id_usuario=? where id_usuario=?";
        else
            consultaPass = "update usuario set id_usuario=?, clave=crypt(?, gen_salt('bf', 4)) where id_usuario=?";
        String consulta2 = "update inversor set nombre=?, dni=?, direccion=?, telefono=? where id_usuario=?";

        try {
            con.setAutoCommit(false);
            stmUpd = con.prepareStatement(consultaPass);
            stmUpd.setString(1, inversor.getIdUsuario());
            if (pass == null) {
                stmUpd.setString(2, idviejo);
            } else {
                stmUpd.setString(2, pass);
                stmUpd.setString(3, idviejo);
            }

            stmUpd.executeUpdate();

            stmUpd = con.prepareStatement(consulta2);
            stmUpd.setString(1, inversor.getNombre());
            stmUpd.setString(2, inversor.getDni());
            stmUpd.setString(3, inversor.getDireccion());
            stmUpd.setString(4, inversor.getTelefono());
            stmUpd.setString(5, inversor.getIdUsuario());
            stmUpd.executeUpdate();

            done = true;
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
            try {
                con.rollback();
            } catch (SQLException rollEx) {
                manejarExcepcionSQL(rollEx);
            }
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }

                if (stmUpd != null) {
                    stmUpd.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return done;
    }

    public boolean modificarEmpresa(Empresa empresa, String pass, String idviejo) {
        PreparedStatement stmUpd = null;
        Connection con;
        boolean done = false;
        con = this.getConexion();
        // Si pasas null en pass no cambiar contraseña
        String consultaPass;
        if (pass == null)
            consultaPass = "update usuario set id_usuario=? where id_usuario=?";
        else
            consultaPass = "update usuario set id_usuario=?, clave=crypt(?, gen_salt('bf', 4)) where id_usuario=?";

        String consulta2 = "update empresa set nombrecomercial=?, cif=?, direccion=?, telefono=? where id_usuario=?";

        try {
            con.setAutoCommit(false);

            stmUpd = con.prepareStatement(consultaPass);
            stmUpd.setString(1, empresa.getIdUsuario());

            if (pass == null) {
                stmUpd.setString(2, idviejo);
            } else {
                stmUpd.setString(2, pass);
                stmUpd.setString(3, idviejo);
            }

            stmUpd.executeUpdate();

            stmUpd = con.prepareStatement(consulta2);
            stmUpd.setString(1, empresa.getNombre());
            stmUpd.setString(2, empresa.getCIF());
            stmUpd.setString(3, empresa.getDireccion());
            stmUpd.setString(4, empresa.getTelefono());
            stmUpd.setString(5, empresa.getIdUsuario());

            stmUpd.executeUpdate();

            done = true;
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
            try {
                con.rollback();
            } catch (SQLException rollEx) {
                manejarExcepcionSQL(rollEx);
            }
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }
                if (stmUpd != null) {
                    stmUpd.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return done;
    }

    public float obtenerComision(String regulador) {
        PreparedStatement stm = null;
        Connection con;
        ResultSet rst;
        float resultado = 0.0f;

        con = this.getConexion();

        String consultaComision = "select comision from regulador where id_usuario = ?";

        try {
            stm = con.prepareStatement(consultaComision);
            stm.setString(1, regulador);
            rst = stm.executeQuery();
            while (rst.next()) {
                resultado = rst.getFloat("comision");
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public void modificarComision(Regulador regulador, float comision) {
        PreparedStatement stmUpd = null;
        Connection con;
        con = this.getConexion();
        String consultaUpdate = "update regulador set comision=? where id_usuario=?";
        try {
            stmUpd = con.prepareStatement(consultaUpdate);
            stmUpd.setFloat(1, comision);
            stmUpd.setString(2, regulador.getIdUsuario());
            stmUpd.executeUpdate();
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmUpd != null) {
                    stmUpd.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public float obtenerSaldoInversor(Usuario usuario) {
        PreparedStatement stm = null;
        Connection con;
        ResultSet rst;
        float resultado = 0.0f;

        con = this.getConexion();

        String consulta = "select saldo from inversor where id_usuario = ?";

        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, usuario.getIdUsuario());
            rst = stm.executeQuery();
            if (rst.next()) {
                resultado = rst.getFloat("saldo");
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public float obtenerSaldoEmpresa(Usuario usuario) {
        PreparedStatement stm = null;
        Connection con;
        ResultSet rst;
        float resultado = 0.0f;

        con = this.getConexion();

        String consulta = "select saldo from empresa where id_usuario = ?";

        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, usuario.getIdUsuario());
            rst = stm.executeQuery();
            if (rst.next()) {
                resultado = rst.getFloat("saldo");
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public void modificarSaldoInversor(String idUsuario, float saldo) {
        PreparedStatement stmUpd = null;
        Connection con;
        con = this.getConexion();
        String consultaUpdate = "update inversor set saldo=? where id_usuario=?";
        try {
            stmUpd = con.prepareStatement(consultaUpdate);
            stmUpd.setFloat(1, saldo);
            stmUpd.setString(2, idUsuario);
            stmUpd.executeUpdate();
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmUpd != null) {
                    stmUpd.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void modificarSaldoEmpresa(String idUsuario, float saldo) {
        PreparedStatement stmUpd = null;
        Connection con;
        con = this.getConexion();
        String consultaUpdate = "update empresa set saldo=? where id_usuario=?";
        try {
            stmUpd = con.prepareStatement(consultaUpdate);
            stmUpd.setFloat(1, saldo);
            stmUpd.setString(2, idUsuario);
            stmUpd.executeUpdate();
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmUpd != null) {
                    stmUpd.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public int getNumeroParticipacionesInversor(String idUsuario) {
        PreparedStatement stm = null;
        Connection con;
        ResultSet rst;
        int resultado = 0;

        con = this.getConexion();

        String consultaComision = "select sum(numparticipaciones) as num\n" +
                "from participacionesinversor\n" +
                "where usuario = ?";

        try {
            stm = con.prepareStatement(consultaComision);
            stm.setString(1, idUsuario);
            rst = stm.executeQuery();
            if (rst.next()) {
                resultado = rst.getInt("num");
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    int getNumeroParticipacionesEmpresa(String idUsuario) {
        PreparedStatement stm = null;
        Connection con;
        ResultSet rst;
        int resultado = 0;

        con = this.getConexion();

        String consultaComision = "select sum(numparticipaciones) as num\n" +
                "from participacionesempresa\n" +
                "where usuario = ?";

        try {
            stm = con.prepareStatement(consultaComision);
            stm.setString(1, idUsuario);
            rst = stm.executeQuery();
            if (rst.next()) {
                resultado = rst.getInt("num");
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public ArrayList<String> getParticipacionesCompradas(Usuario usuario) {

        PreparedStatement stm = null;
        Connection con;
        ResultSet rst;
        ArrayList<String> resultado = new ArrayList<>();

        con = this.getConexion();

        String consulta = "select empresa "
                + "from @ "
                + "where usuario = ?  usuario!=empresa ";

        // Meter la tabla en la que se mirará
        if (usuario instanceof Inversor) {
            consulta = consulta.replace("@", "participacionesInversor");
        }
        if (usuario instanceof Empresa) {
            consulta = consulta.replace("@", "participacionesEmpresa");
        }

        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, usuario.getIdUsuario());
            rst = stm.executeQuery();
            while (rst.next()) {
                resultado.add(rst.getString("empresa"));
            }

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public void bloquearSaldo(Empresa empresa, int cantidad) {
        PreparedStatement stmAnuncios = null, stmBloqueo = null, stmBloqueoPartEmpresa = null;
        ResultSet rstAnuncios = null, rst = null;
        Connection con;
        con = this.getConexion();

        String consultaBloqueo = "update empresa set saldo = saldo - ?, saldobloqueado = saldobloqueado + ?," +
                "participacionesbloqueadas = participacionesbloqueadas + ? where id_usuario = ?";
        String consultaBloqueoPartEmpresa = "update participacionesempresa set numparticipaciones = numparticipaciones - ? " +
                "where usuario = ? and empresa = ?";
        String obtenerAnuncios = "select distinct(saldo) as s,sum(numeroparticipaciones) as p,sum(importeparticipacion) as i "
                + "from empresa as e inner join anunciobeneficios as a "
                + "	on ( e.id_usuario=a.empresa and e.id_usuario= ? ) "
                + "group by saldo";

        try {
            // Obtenemos anuncios
            stmAnuncios = con.prepareStatement(obtenerAnuncios);
            stmAnuncios.setString(1, empresa.getIdUsuario());
            rstAnuncios = stmAnuncios.executeQuery();

            if (rstAnuncios.isBeforeFirst()) {
                // Calculamos lo que hay que bloquear por participación
                int participacionesADarPorVendida = 0;
                float dineroADarPorVendida = 0.0f;

                while (rstAnuncios.next()) {
                    participacionesADarPorVendida += rstAnuncios.getInt("p");
                    dineroADarPorVendida += rstAnuncios.getFloat("i");
                }

                // Empezamos a bloquear
                stmBloqueo = con.prepareStatement(consultaBloqueo);
                stmBloqueoPartEmpresa = con.prepareStatement(consultaBloqueoPartEmpresa);

                stmBloqueo.setFloat(1, cantidad * dineroADarPorVendida);
                stmBloqueo.setFloat(2, cantidad * dineroADarPorVendida);
                stmBloqueo.setInt(3, cantidad * participacionesADarPorVendida);
                stmBloqueo.setString(4, empresa.getIdUsuario());

                stmBloqueoPartEmpresa.setInt(1, cantidad * participacionesADarPorVendida);
                stmBloqueoPartEmpresa.setString(2, empresa.getIdUsuario());
                stmBloqueoPartEmpresa.setString(3, empresa.getIdUsuario());

                stmBloqueo.executeUpdate();
                stmBloqueoPartEmpresa.executeUpdate();
            }
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            // Cerrar stms y confirmar la transación si no se pidió confirmación manual
            try {
                if (stmAnuncios != null) {
                    stmAnuncios.close();
                }
                if (stmBloqueo != null) {
                    stmBloqueo.close();
                }
                if (stmBloqueoPartEmpresa != null) {
                    stmBloqueoPartEmpresa.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void liberarSaldo(Empresa empresa, int cantidad) {
        bloquearSaldo(empresa, -cantidad);
    }
}
