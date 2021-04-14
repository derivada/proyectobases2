package baseDatos;

import aplicacion.Empresa;
import aplicacion.Inversor;
import aplicacion.Regulador;
import aplicacion.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
             * Obtiene el tipo del usuario que está intentando acceder
             */
            while (rsUsuario.next()) {
                usuario = new Usuario(rsUsuario.getString("id_usuario"));
            }
            if (usuario != null) {
                // Busca el usuario en cada una de las tablas
                for (Regulador r : obtenerDatosRegulador(usuario.getIdUsuario())) {
                    if (r.getIdUsuario().equals(usuario.getIdUsuario()))
                        return r;
                }
                for (Inversor i : obtenerDatosInversor(usuario.getIdUsuario())) {
                    if (i.getIdUsuario().equals(usuario.getIdUsuario()))
                        return i;
                }
                for (Empresa p : obtenerDatosEmpresa(usuario.getIdUsuario())) {
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
    public java.util.List<String> obtenerListaNombresUsuarios() {

        java.util.List<String> resultado = new java.util.ArrayList<String>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();
        String consulta = "select id_usuario "
                + "from usuario ";
        try {
            stmCatalogo = con.prepareStatement(consulta);
            rsUsuarios = stmCatalogo.executeQuery();
            while (rsUsuarios.next()) {
                resultado.add(rsUsuarios.getString("id_usuario").replaceAll("\\s+$", ""));
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
    public java.util.List<Inversor> obtenerDatosInversor(String id_inversor) {
        java.util.List<Inversor> resultado = new java.util.ArrayList<Inversor>();
        Inversor inversorActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsInversores;

        con = this.getConexion();

        String consulta = "select * from inversor where id_usuario = ?";
        try {
            stmCatalogo = con.prepareStatement(consulta);
            stmCatalogo = con.prepareStatement(consulta);
            stmCatalogo.setString(1, id_inversor);
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
    public java.util.List<Empresa> obtenerDatosEmpresa(String id_empresa) {
        java.util.List<Empresa> resultado = new java.util.ArrayList<Empresa>();
        Empresa empresaActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsEmpresas;

        con = this.getConexion();

        String consulta = "select * from empresa where id_usuario = ?";
        try {
            stmCatalogo = con.prepareStatement(consulta);
            stmCatalogo.setString(1, id_empresa);
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
    public java.util.List<Regulador> obtenerDatosRegulador(String id_regulador) {
        java.util.List<Regulador> resultado = new java.util.ArrayList<Regulador>();
        Regulador reguladorActual;
        Connection con;
        PreparedStatement stmCatalogo = null;
        ResultSet rsUsuarios;

        con = this.getConexion();

        String consulta = "select * from regulador where id_usuario = ?";

        try {
            stmCatalogo = con.prepareStatement(consulta);
            stmCatalogo.setString(1, id_regulador);
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
                consulta = "insert into usuario(id_usuario, clave, cuenta) values (?,?,?)";
                stmIns = con.prepareStatement(consulta);
                stmIns.setString(1, u.getIdUsuario());
                stmIns.setString(2, u.getClave());
                stmIns.setFloat(3, u.getCuenta());
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

    public void emitirParticipaciones(Empresa e, int emision, int precio){
        int antiguasPart=0;
        PreparedStatement stmAntiguas = null;
        PreparedStatement stmUpdate = null;
        PreparedStatement stmNueva = null;
        ResultSet rst;
        ResultSet rstUp;
        ResultSet rstNu;
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
                System.out.println(ex.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
            } finally {
                try {
                    stmNueva.close();
                } catch (SQLException ex) {
                    System.out.println("Imposible cerrar cursores");
                }
            }
            try {
                stmUpdate = con.prepareStatement(consulta2);
                stmUpdate.setInt(1, emision+antiguasPart);
                stmUpdate.setString(2, e.getIdUsuario());
                stmUpdate.setString(3, e.getIdUsuario());
                stmUpdate.executeUpdate();
            } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
                System.out.println(ex.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
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
            System.out.println(ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        } finally {
            try {
                stmAntiguas.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
    }
    
    public void bajaParticipaciones(Empresa e, int baja){
        int antiguasPart=0;
        PreparedStatement stmAntiguas = null;
        PreparedStatement stmUpdate = null;
        PreparedStatement stmNueva = null;
        ResultSet rst;
        ResultSet rstUp;
        ResultSet rstNu;
        Connection con;

        con = this.getConexion();

        String consulta = "select numparticipaciones "
                + "from participacionesempresa "
                + "where usuario=? AND empresa=?";
        
        
        String consulta2 = "update participacionesempresa "
                + "set numparticipaciones=? "
                + "where usuario=? AND empresa=?";
        
       

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
                stmUpdate = con.prepareStatement(consulta2);
                stmUpdate.setInt(1, antiguasPart - baja);
                stmUpdate.setString(2, e.getIdUsuario());
                stmUpdate.setString(3, e.getIdUsuario());
                stmUpdate.executeUpdate();
            } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
                System.out.println(ex.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
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
            System.out.println(ex.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(ex.getMessage());
        } finally {
            try {
                stmAntiguas.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }


    public ArrayList<Inversor> obtenerInversoresPorAutorizacion(Boolean autorizado) {
        ArrayList<Inversor> resultado = new ArrayList<>();
        PreparedStatement stmInversores = null;
        ResultSet rst;
        ResultSet rst2;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from inversor where autorizado = ?";

        try {
            stmInversores = con.prepareStatement(consulta);
            stmInversores.setBoolean(1, autorizado);
            rst = stmInversores.executeQuery();
            while (rst.next()) {
                Inversor i = new Inversor(rst.getString("id_usuario"), rst.getString("nombre"), rst.getString("dni"), rst.getString("direccion"), rst.getString("telefono"), autorizado);
                stmInversores = con.prepareStatement("select * from usuario where id_usuario = ?");
                stmInversores.setString(1, rst.getString("id_usuario"));

                rst2 = stmInversores.executeQuery();
                while (rst2.next()) {
                    Usuario u = (Usuario) i;
                    u.setClave(rst2.getString("clave"));
                    u.setIdUsuario(rst2.getString("id_usuario"));
                    u.setCuenta(rst2.getFloat("cuenta"));

                }
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

    public ArrayList<Empresa> obtenerEmpresaPorAutorizacion(Boolean autorizado) {
        ArrayList<Empresa> resultado = new ArrayList<>();
        PreparedStatement stmEmpresas = null;
        ResultSet rst, rst2;
        Connection con;

        con = this.getConexion();

        String consulta = "select * from empresa where autorizado = ?";

        try {
            stmEmpresas = con.prepareStatement(consulta);
            stmEmpresas.setBoolean(1, autorizado);
            rst = stmEmpresas.executeQuery();
            while (rst.next()) {
                Empresa e = new Empresa(rst.getString("id_usuario"), rst.getString("nombrecomercial"), rst.getString("cif"), rst.getString("direccion"), rst.getString("telefono"), autorizado);
                stmEmpresas = con.prepareStatement("select * from usuario where id_usuario = ?");
                stmEmpresas.setString(1, rst.getString("id_usuario"));

                rst2 = stmEmpresas.executeQuery();
                while (rst2.next()) {
                    Usuario u = (Usuario) e;
                    u.setClave(rst2.getString("clave"));
                    u.setIdUsuario(rst2.getString("id_usuario"));
                    u.setCuenta(rst2.getFloat("cuenta"));

                }

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
}
