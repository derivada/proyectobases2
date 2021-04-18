package baseDatos;

import aplicacion.Empresa;
import aplicacion.Inversor;
import aplicacion.Regulador;
import aplicacion.Usuario;
import aplicacion.AnuncioBeneficios; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date; 
import java.util.HashMap; 

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
            if (usuarioValidado != null && usuarioValidado.isAutorizado()) {
                // Busca el usuario en cada una de las tablas
                result = obtenerDatosEmpresa(usuarioValidado);
                if (result == null)
                    result = obtenerDatosInversor(usuarioValidado);
                if (result == null)
                    result = obtenerDatosRegulador(usuarioValidado);
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
        return result;
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
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                con.setAutoCommit(true);
                stmAntiguas.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

    }
/*
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
    */

    /*
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
                    Empresa e = new Empresa(rst.getString("id_usuario"),
                            rst.getString("nombrecomercial"),
                            rst.getString("cif"),
                            rst.getFloat("saldo"),
                            rst.getFloat("saldobloqueado"),
                            rst.getString("direccion"),
                            rst.getString("telefono"));
                    stmEmpresas = con.prepareStatement("select * from usuario where id_usuario = ?");
                    stmEmpresas.setString(1, rst.getString("id_usuario"));

                    rst2 = stmEmpresas.executeQuery();
                    while (rst2.next()) {
                        Usuario u = (Usuario) e;
                        u.setClave(rst2.getString("clave"));
                        u.setIdUsuario(rst2.getString("id_usuario"));
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
    */
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
    
    public java.util.List<AnuncioBeneficios> obtenerAnuncios(String empresa){
        java.util.List<AnuncioBeneficios> resultado=new java.util.ArrayList<AnuncioBeneficios>(); 
        Connection con; 
        PreparedStatement stmConsulta=null; 
        ResultSet rstConsulta; 
        AnuncioBeneficios aux=null; 
        
        String consulta="select * from anunciobeneficios where empresa= ? "; 
        
        con=this.getConexion(); 
        try {
            stmConsulta=con.prepareStatement(consulta); 
            stmConsulta.setString(1, empresa);
            rstConsulta=stmConsulta.executeQuery(); 
            while(rstConsulta.next()){
                if(rstConsulta.getString("solicitadobaja").isEmpty()){
                    aux=new AnuncioBeneficios(rstConsulta.getString("empresa"),rstConsulta.getDate("fechapago"),
                        rstConsulta.getDate("fechaanuncio"),rstConsulta.getFloat("importeparticipacion"),rstConsulta.getInt("numeroparticipaciones"),false); 
                }
                else{
                    aux=new AnuncioBeneficios(rstConsulta.getString("empresa"),rstConsulta.getDate("fechapago"),
                        rstConsulta.getDate("fechaanuncio"),rstConsulta.getFloat("importeparticipacion"),rstConsulta.getInt("numeroparticipaciones"),rstConsulta.getBoolean("solicitadobaja")); 
                }
                
                resultado.add(aux); 
            }
            
            
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmConsulta.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
        return resultado; 
    }
    //Función para obtener los anuncios con una solicitud de baja 
    public java.util.List<AnuncioBeneficios> obtenerAnunciosRegulador(){
         java.util.List<AnuncioBeneficios> resultado=new java.util.ArrayList<AnuncioBeneficios>(); 
        Connection con; 
        PreparedStatement stmConsulta=null; 
        ResultSet rstConsulta; 
        AnuncioBeneficios aux=null; 
        
        String consulta="select * from anunciobeneficios where solicitadobaja=true "; 
        
        con=this.getConexion(); 
        try {
            stmConsulta=con.prepareStatement(consulta);
            rstConsulta=stmConsulta.executeQuery(); 
            while(rstConsulta.next()){
                aux=new AnuncioBeneficios(rstConsulta.getString("empresa"),rstConsulta.getDate("fechapago"),
                    rstConsulta.getDate("fechaanuncio"),rstConsulta.getFloat("importeparticipacion"),
                        rstConsulta.getInt("numeroparticipaciones"),rstConsulta.getBoolean("solicitadobaja")); 
                
                resultado.add(aux); 
            }
            
            
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmConsulta.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
        return resultado; 
    }
    
    //Función para crear un anuncio de beneficios 
    //Devuelve 1 si se ha creado correcetamente, 2 si el importe es insuficiente, y 3 si 
    //las participaciones no son suficientes para afrontar el pago. 
    public int  crearAnuncio(Float importe, Empresa e,Date fecha,Integer numeroParticipaciones){
        Connection con; 
        PreparedStatement stmAnunciar1=null; 
        PreparedStatement stmAnunciar2=null; 
        PreparedStatement stmAnunciar3=null; 
        PreparedStatement stmBloquear=null; 
        PreparedStatement stmPrecioParticipaciones=null; //Statement para consultar el precio por participación de la empresa 
        int resultado=1; 
        String consulta1="Insert into anunciobeneficios  (empresa,fechapago,numeroparticipaciones,solicitadobaja) values (?,?,?,false)"; 
        
        String consulta2="Insert into anunciobeneficios  (empresa,fechapago,importeparticipacion,solicitadobaja) values (?,?,?,false)"; 
        
        String consulta3="Insert into anunciobeneficios  (empresa,fechapago,importeparticipacion,numeroparticipaciones,solicitadobaja) "
                + "values (?,?,?,?,false)"; 
        
        String consulta4="update empresa set saldobloqueado=saldobloqueado + ? ,saldo=saldo - ? where id_usuario= ? "; 
               
         
            //Diferenciamos 3 casos

            //El primer es pagar únicamente con participaciones 
            if(numeroParticipaciones!=0 && importe==0.0f){
                 if(this.getPartPropEmpresa(e)<(numeroParticipaciones*this.participacionesVendidas(e.getIdUsuario()))){
                    resultado=3; 
                }
                 else{
                      con=this.getConexion(); 
                      try {
                           stmAnunciar1=con.prepareStatement(consulta1); 
                            stmAnunciar1.setString(1, e.getIdUsuario());
                            stmAnunciar1.setDate(2,fecha); 
                            stmAnunciar1.setInt(3, numeroParticipaciones);
                            stmAnunciar1.executeUpdate(); 
                       } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
                           manejarExcepcionSQL(ex);
                       } finally {
                           try {
                               stmAnunciar1.close(); 

                           } catch (SQLException ex) {
                               System.out.println("Imposible cerrar cursores");
                           }
                       }
                 }


            }
            //Segundo caso, solo importe 
            else if(numeroParticipaciones==0 && importe>=0){
                if(importe>this.comprobarSaldoEmpresa(e.getIdUsuario())){
                    resultado=2; 
                }
                else{
                     con=this.getConexion(); 
                     try {
                           stmAnunciar2=con.prepareStatement(consulta2); 
                           stmAnunciar2.setString(1,e.getIdUsuario() );
                           stmAnunciar2.setDate(2, fecha);
                           stmAnunciar2.setFloat(3, importe);
                           stmAnunciar2.executeUpdate(); 

                           stmBloquear=con.prepareStatement(consulta4); 
                           stmBloquear.setFloat(1, importe);
                           stmBloquear.setFloat(2, importe);
                           stmBloquear.setString(3,e.getIdUsuario());
                           stmBloquear.executeUpdate(); 
                       } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
                           manejarExcepcionSQL(ex);
                       } finally {
                           try {
                               stmAnunciar2.close(); 
                               stmBloquear.close();

                           } catch (SQLException ex) {
                               System.out.println("Imposible cerrar cursores");
                           }
                       }

                }
            }
            //Tercer caso, con importe y participaciones. 
            //Se comprueban tanto que el importe sea suficiente como que las participaciones sean suficientes. 

            else{
                if (importe>this.comprobarSaldoEmpresa(e.getIdUsuario())){
                    resultado=2; 
                }
                else if(this.getPartPropEmpresa(e)<(numeroParticipaciones*this.participacionesVendidas(e.getIdUsuario()))){
                    resultado=3; 
                }
                else{
                    con=this.getConexion(); 
                    try {
                           stmAnunciar3=con.prepareStatement(consulta3); 
                           stmAnunciar3.setString(1, e.getIdUsuario());
                           stmAnunciar3.setDate(2, fecha);
                           stmAnunciar3.setFloat(3, importe);
                           stmAnunciar3.setInt(4, numeroParticipaciones);
                           stmAnunciar3.executeUpdate();

                           stmBloquear=con.prepareStatement(consulta4); 
                           stmBloquear.setFloat(1, importe);
                           stmBloquear.setFloat(2, importe);
                           stmBloquear.setString(3,e.getIdUsuario());
                           stmBloquear.executeUpdate(); 
                       } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
                           manejarExcepcionSQL(ex);
                       } finally {
                           try {
                               stmAnunciar3.close(); 
                               stmBloquear.close();

                           } catch (SQLException ex) {
                               System.out.println("Imposible cerrar cursores");
                           }
                       }


                }
            }
            
       
        return resultado; 
        
    }
    
    //Función para dar de baja un anuncio de la base de datos 
    public void bajaAnuncio(String empresa,Date fecha,Float importe){
        Connection con; 
        PreparedStatement stmResta=null; 
         PreparedStatement stmSuma=null;
          PreparedStatement stmBaja=null; 
        //Consulta para quitar el importe del saldo bloqueado de la empresa 
        String consulta1="update empresa set saldobloqueado=saldobloqueado- ? where id_usuario= ? "; 
        
        //Consulta para sumar el importe al salod
        String consulta2="update empresa set saldo=saldo+ ? where id_usuario= ? ";
        //Consulta para borrar el anuncio 
        String consulta3="delete from anunciobeneficios where empresa= ? and fechapago= ? ";
        
        con=this.getConexion(); 
         
        try {
            //Se resta de saldobloqueado
            stmResta=con.prepareStatement(consulta1); 
            stmResta.setFloat(1, importe);
            stmResta.setString(2, empresa);
            stmResta.executeUpdate(); 
            //Se suma a saldo
            stmSuma=con.prepareStatement(consulta2); 
            stmSuma.setFloat(1, importe);
            stmSuma.setString(2, empresa);
            stmSuma.executeUpdate(); 
            
            //Se elimina el anuncio 
            
            stmBaja=con.prepareStatement(consulta3); 
            stmBaja.setString(1, empresa);
            stmBaja.setDate(2, fecha);
            stmBaja.executeUpdate(); 
            
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                stmBaja.close();
                stmSuma.close();
                stmResta.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
        
    }
    
    public boolean solicitarBajaAnuncio(String empresa,Date fechaPago){
        Connection con; 
        PreparedStatement stmAnuncio=null; 
        String consulta="update anunciobeneficios set solicitadobaja=true where empresa= ?  and fechapago= ? "; 
        con=this.getConexion(); 
        try {
           stmAnuncio=con.prepareStatement(consulta); 
           stmAnuncio.setString(1, empresa);
           stmAnuncio.setDate(2, fechaPago);
           stmAnuncio.executeUpdate(); 
            
            
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
            return false; 
        } finally {
            try {
                stmAnuncio.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return true; 
        
        
    }
    
    //Función para obtener el saldo de una empresa 
    public Float comprobarSaldoEmpresa(String empresa){
        Float resultado=0.0f; 
        
        Connection con; 
        PreparedStatement stmConsulta=null;  
        ResultSet rstResultado; 
        
        String consulta="select saldo from empresa where id_usuario= ?  "; 
        
        con=this.getConexion(); 
        try {
             stmConsulta=con.prepareStatement(consulta); 
             stmConsulta.setString(1, empresa);
             rstResultado=stmConsulta.executeQuery(); 
             while(rstResultado.next()){
                 resultado=rstResultado.getFloat("saldo"); 
             }
            
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
            
        } finally {
            try {
                stmConsulta.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado; 
    }
    
    //Función para obtener el numer de participaciones que tiene vendidas la empresa 
    
    public int participacionesVendidas(String empresa){

        PreparedStatement stmConsulta=null; 
        ResultSet rstResultado;
        Connection con;
        int resultado=0; 
        String consulta="select sum(numparticipaciones)" +
            "from ( " +
            "    select pi.* " +
            "    from participacionesinversor as pi " +
            "    where  pi.empresa= ? " +
            "    union " +
            "    select pe.* " +
            "    from participacionesempresa as pe " +
            "    where  pe.empresa= ?) as resultado " +
            "where resultado.usuario!= ? "; 
         con=this.getConexion(); 
        try {
             stmConsulta=con.prepareStatement(consulta); 
             stmConsulta.setString(1, empresa);
             stmConsulta.setString(2, empresa);
             stmConsulta.setString(3, empresa);
             rstResultado=stmConsulta.executeQuery(); 
             while(rstResultado.next()){
                 resultado=rstResultado.getInt("sum"); 
             }
            
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
            
        } finally {
            try {
                stmConsulta.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
        return resultado; 
    }
    
    //Función que paga los beneficios, con o sin anuncio previo. 
    //Si el pago es sin anuncio, se llama a esta función con un null en anuncio
    
    public void pagarBeneficios(Float importe,Integer participaciones,Empresa e,AnuncioBeneficios anuncio){
        //Para restar a empresa 
        PreparedStatement stmImporte = null;
        PreparedStatement stmImporteB = null;
        PreparedStatement stmParticipaciones=null; 
        Connection con;
        int num=0; //Variable que se va a usar para obtener el número de participaciones en varias consultas 
        //ResultSet y statements para modificar los datos de inversores 
        ResultSet rstMI; 
        PreparedStatement stmMI=null; 
        PreparedStatement stmSumaI=null; 
        PreparedStatement stmSuma2I=null; 
        
        //ResultSet y statements para modificar los datos de empresa 
        ResultSet rstME; 
        PreparedStatement stmME=null; 
        PreparedStatement stmSumaE=null; 
        PreparedStatement stmSuma2E=null; 
        
        //Statement de eliminación de anuncio, con su consulta 
        PreparedStatement stmEliminacion=null; 
        String consulta10="delete from anunciobeneficios where empresa= ?  and fechapago= ? "; 

        
        
        
        //Consulta 1 es para actualizar el saldo de la empresa 
        String consulta1="update empresa set saldo=saldo-? where id_usuario= ? ";
        
        //Consulta 1 es para actualizar el saldobloquead de la empresa si el pago es con un anuncio
        String consulta11="update empresa set saldobloqueado=saldobloqueado-? where id_usuario= ? ";
        
        //Consulta9 es para actualizar las participaciones disponibles de la empresa
        String consulta9="update participacionesempresa set numparticipaciones=numparticipaciones- ?  "
                + "where empresa= ?  and usuario= ? ";
       
        //Consulta 3 es para seleccionar de los inversores que tienen las participaciones de la empresa 
        String consulta3="select i.id_usuario,pi.numparticipaciones,pi.empresa " +
            "from participacionesinversor as pi,inversor as i, empresa as e " +
            "where pi.usuario=i.id_usuario and pi.empresa= ? " +
            "group by i.id_usuario,pi.numparticipaciones,pi.empresa"; 
        //Consulta 4 para aumentar el saldo de inversor
        String consulta4="update inversor set saldo=saldo+? where id_usuario= ? "; 
        //Consulta 7 para aumentar las participaciones del inversor 
        String consulta7="update participacionesinversor set numparticipaciones=numparticipaciones+ ? where empresa= ? "; 
        //Consulta 5, igual que consulta 3, pero para empresas
        String consulta5="select e.id_usuario,pe.numparticipaciones,pe.empresa " +
            "from participacionesempresa as pe,empresa as e " +
            "where pe.usuario=e.id_usuario and pe.empresa= ? and pe.usuario!= ? " +
            "group by e.id_usuario,pe.numparticipaciones,pe.empresa";
        //Consulta 6 para aumentar el saldo de la empresa 
        String consulta6="update empresa set saldo=saldo+? where id_usuario= ? "; 
         //Consulta 8 para aumentar las participaciones de la empresa 
        String consulta8="update participacionesempresa set numparticipaciones=numparticipaciones+ ?  "
                + "where empresa= ? and usuario!= ? "; 
        
        con = this.getConexion();
        
                
         try {
             
            con.setAutoCommit(false);
            //En todos los casos se va a diferenciar si hay un anuncio o no. 
            
            //Se comprueba el número de participaciones que tiene la empresa vendidas 
            num=this.participacionesVendidas(e.getIdUsuario()); 
            
            
            //Ahora se resta el dinero en la empresa y participaciones de la empresa 
 
            if(anuncio==null){
                //Dinero 
                float dinero=importe*(float)num; 
                stmImporte=con.prepareStatement(consulta1); 
                stmImporte.setFloat(1, dinero);
                stmImporte.setString(2, e.getIdUsuario());
                stmImporte.executeUpdate();
                
                //Participaciones 
                int p=num*participaciones;
                stmParticipaciones=con.prepareStatement(consulta9); 
                stmParticipaciones.setInt(1, p);
                stmParticipaciones.setString(2, e.getIdUsuario());
                stmParticipaciones.setString(3, e.getIdUsuario());
                stmParticipaciones.executeUpdate(); 
            }
            else{
                
               try {
                    //Dinero bloqueado
                    float dinero=anuncio.getImporteparticipacion()*(float)num; 
                    stmImporteB=con.prepareStatement(consulta11); 
                    stmImporteB=con.prepareStatement(consulta1); 
                    stmImporteB.setFloat(1, dinero);
                    stmImporteB.setString(2, e.getIdUsuario());
                    stmImporteB.executeUpdate();

               } catch (SQLException ex) {
                   manejarExcepcionSQL(ex);

               } finally {
                   try {
                       stmImporteB.close();
                   } catch (SQLException ex) {
                       System.out.println("Imposible cerrar cursores");
                   }
               }
                
                
                
                //Participaciones 
                int p=num*anuncio.getNumeroparticipaciones(); 
                stmParticipaciones=con.prepareStatement(consulta9); 
                stmParticipaciones.setInt(1, p);
                stmParticipaciones.setString(2, e.getIdUsuario());
                stmParticipaciones.setString(3, e.getIdUsuario());
                stmParticipaciones.executeUpdate(); 
            }
           
            
            //Y ahora se suma a los usuarios que tenian las participaciones, tanto inversores como empresas
            //Hay que hacer dos consultas distintas, primero la de inversores 
            
            if(anuncio==null){
                //Dinero
                stmMI=con.prepareStatement(consulta3); 
                stmSumaI=con.prepareStatement(consulta4); 
                stmMI.setString(1, e.getIdUsuario());
                rstMI=stmMI.executeQuery();
                float suma=0; 
                while(rstMI.next()){
                    num=rstMI.getInt("numparticipaciones"); 
                    suma=importe*num; 
                    stmSumaI.setFloat(1, suma);
                    stmSumaI.setString(2, rstMI.getString("id_usuario"));
                    stmSumaI.executeUpdate(); 

                }
                
                //Participaciones 
                stmSuma2I=con.prepareStatement(consulta7); 
                stmSuma2I.setInt(1, participaciones);
                stmSuma2I.setString(2, e.getIdUsuario());
                stmSuma2I.executeUpdate(); 
            }
            else{
                //Dinero
                stmMI=con.prepareStatement(consulta3); 
                stmSumaI=con.prepareStatement(consulta4); 
                stmMI.setString(1, e.getIdUsuario());
                rstMI=stmMI.executeQuery();
                float suma=0; 
                while(rstMI.next()){
                    num=rstMI.getInt("numparticipaciones"); 
                    suma=anuncio.getImporteparticipacion()*num; 
                    stmSumaI.setFloat(1, suma);
                    stmSumaI.setString(2, rstMI.getString("id_usuario"));
                    stmSumaI.executeUpdate(); 

                }
                
                //Participaciones 
                stmSuma2I=con.prepareStatement(consulta7); 
                stmSuma2I.setInt(1, anuncio.getNumeroparticipaciones());
                stmSuma2I.setString(2, e.getIdUsuario());
                stmSuma2I.executeUpdate(); 
            }
            
            //Y ahora la de empresas 
            
            if(anuncio==null){
                //Importe 
                stmME=con.prepareStatement(consulta5); 
                stmSumaE=con.prepareStatement(consulta6); 
                stmME.setString(1, e.getIdUsuario());
                stmME.setString(2, e.getIdUsuario());
                rstME=stmME.executeQuery(); 
                float sum=0; 
                while(rstME.next()){
                    num=rstME.getInt("numparticipaciones"); 
                    sum=importe*num; 
                    stmSumaE.setFloat(1, sum);
                    stmSumaE.setString(2, rstME.getString("id_usuario"));
                    stmSumaE.executeUpdate(); 
                }
                //Participaciones 
                stmSuma2E=con.prepareStatement(consulta8); 
                stmSuma2E.setInt(1, participaciones);
                stmSuma2E.setString(2, e.getIdUsuario());
                stmSuma2E.setString(3, e.getIdUsuario());
                stmSuma2E.executeUpdate(); 
                
            }
            else{
                 //Importe 
                stmME=con.prepareStatement(consulta5); 
                stmSumaE=con.prepareStatement(consulta6); 
                stmME.setString(1, e.getIdUsuario());
                stmME.setString(2, e.getIdUsuario());
                rstME=stmME.executeQuery(); 
                float sum=0; 
                while(rstME.next()){
                    num=rstME.getInt("numparticipaciones"); 
                    sum=anuncio.getImporteparticipacion()*num; 
                    stmSumaE.setFloat(1, sum);
                    stmSumaE.setString(2, rstME.getString("id_usuario"));
                    stmSumaE.executeUpdate(); 
                }
                //Participaciones 
                stmSuma2E=con.prepareStatement(consulta8); 
                stmSuma2E.setInt(1, anuncio.getNumeroparticipaciones());
                stmSuma2E.setString(2, e.getIdUsuario());
                stmSuma2E.setString(3, e.getIdUsuario());
                stmSuma2E.executeUpdate(); 
            }
            
            //Por último, si hay un anuncio, se elimina
            if(anuncio!=null){
                try {
                    stmEliminacion=con.prepareStatement(consulta10); 
                    stmEliminacion.setString(1, e.getIdUsuario());
                    stmEliminacion.setDate(2, anuncio.getFechaPago());
                    stmEliminacion.executeUpdate(); 

               } catch (SQLException ex) {
                   manejarExcepcionSQL(ex);

               } finally {
                   try {
                       stmEliminacion.close();
                   } catch (SQLException ex) {
                       System.out.println("Imposible cerrar cursores");
                   }
               }
            }
          
            
            
            
            
            con.commit();
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                con.setAutoCommit(true);
                stmImporte.close();
                stmParticipaciones.close();
                stmMI.close();
                stmSumaI.close();
                stmSuma2I.close();
                stmME.close();
                stmSumaE.close(); 
                stmSuma2E.close();
                
                
                
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

    }
    
    public void pagarBeneficiosAnuncio(AnuncioBeneficios anuncio){
        Connection con; 
        PreparedStatement stmRestarDinero=null; 
        PreparedStatement stmRestarParticipaciones=null; 
        PreparedStatement stmPagar1=null; 
        PreparedStatement stmPagar2=null; 
        String consulta1="update empresa set saldobloqueado=saldobloqueado - ? where id_usuario= ? "; 
        
        String consulta2="update participacionesempresa set numparticipaciones=numparticipaciones- ? "
                + "where usuario= ?  and empresa= ? "; 
        
        
        
        con=this.getConexion(); 
          try {
              /*Primero se restas las participaciones y el dinero de la emprea. 
              En caso de que un anuncio sea solo de importe, como el anuncio almacenaría 
              un 0 en participaciones, se va a restas 0 en participaciones de la 
              empresa, por lo que se ejecutan siempre las dos instrucciones de resta*/
             
             con.setAutoCommit(false);
             //Primero se resta el dinero del saldo bloqueado de la empresa 
             stmRestarDinero=con.prepareStatement(consulta1); 
             stmRestarDinero.setFloat(1, anuncio.getImporteparticipacion());
             stmRestarDinero.setString(2, anuncio.getEmpresa());
             //Ahora se restan las participaciones 
             stmRestarParticipaciones=con.prepareStatement(consulta2); 
             stmRestarParticipaciones.setInt(1, anuncio.getNumeroparticipaciones());
             stmRestarParticipaciones.setString(2, anuncio.getEmpresa());
             stmRestarParticipaciones.setString(3, anuncio.getEmpresa());
             
             //Ahora se suma a los inversores o empresas que tengan las participaciones
             
             
            
            
            
            
            con.commit();
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                con.setAutoCommit(true);
                stmPagar1.close();
                stmPagar2.close();
                
                
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
}
