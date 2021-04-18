package baseDatos;

import aplicacion.Empresa;
import aplicacion.Inversor;
import aplicacion.Regulador;
import aplicacion.Usuario;
import vista.componentes.DialogoInfo;

import java.sql.*;

public class DAOParticipaciones extends AbstractDAO {

    public DAOParticipaciones(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    /**
     * Obtiene el número de participaciones totales del usuario
     *
     * @param u Inversor o Empresa que tiene las participaciones
     * @return El número total de participaciones de u
     */
    public int getParticipacionesTotales(Usuario u) {
        if (u == null || u instanceof Regulador) {
            return 0;
        }

        int result = 0;
        PreparedStatement stmCheck = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select SUM(numparticipaciones) as result "
                + "from @ "
                + "where usuario = ? "
                + "group by usuario";

        // Meter la tabla en la que se mirará
        if (u instanceof Inversor) {
            consulta = consulta.replace("@", "participacionesInversor");
        }
        if (u instanceof Empresa) {
            consulta = consulta.replace("@", "participacionesEmpresa");
        }

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, u.getIdUsuario());
            rst = stmCheck.executeQuery();
            while (rst.next()) {
                result = rst.getInt("result");
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

    /**
     * Obtiene el número de participaciones del usuario de una empresa
     *
     * @param u Inversor o Empresa que tiene las participaciones
     * @param e Empresa a las que están asociadas esas participaciones
     * @return El número de participaciones de u en e
     */
    public int getParticipacionesEmpresa(Usuario u, Empresa e) {
        if (u == null || u instanceof Regulador) {
            return 0;
        }

        int result = 0;
        PreparedStatement stmCheck = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select numparticipaciones as result "
                + "from @ "
                + "where usuario = ? AND empresa = ? ";

        // Meter la tabla en la que se mirará
        if (u instanceof Inversor) {
            consulta = consulta.replace("@", "participacionesInversor");
        }
        if (u instanceof Empresa) {
            consulta = consulta.replace("@", "participacionesEmpresa");
        }

        try {
            stmCheck = con.prepareStatement(consulta);
            stmCheck.setString(1, u.getIdUsuario());
            stmCheck.setString(2, e.getIdUsuario());
            rst = stmCheck.executeQuery();
            while (rst.next()) {
                result = rst.getInt("result");
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

    public int getPartPropEmpresa(Empresa e) {
        return getParticipacionesTotales(e);
    }

    public void bajaParticipaciones(Empresa e, int baja) {
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

    public void crearOfertaVenta(Usuario u, Empresa e, int numero, float precioVenta) {
        if (u == null || u instanceof Regulador) {
            manejarExcepcion(new Exception("El usuario no puede crear ofertas de venta!"));
            return;
        }

        int result = 0;
        PreparedStatement stmOferta = null, stmSustracion = null, stmEliminacion = null;
        Connection con;

        con = this.getConexion();
        String consultaOferta = "INSERT INTO public.ofertaventa(\n"
                + "\tusuario, empresa, fecha, numparticipaciones, precio)\n"
                + "\tVALUES (?, ?, ?, ?, ?);";

        String consultaSustracion = "UPDATE public.@\n"
                + "\tSET numparticipaciones=?\n"
                + "\tWHERE usuario=? AND empresa=?;";

        String consultaEliminacion = "DELETE FROM public.@\n"
                + "\tWHERE usuario=? AND empresa=?;";

        if (u instanceof Inversor) {
            consultaSustracion = consultaSustracion.replace("@", "participacionesInversor");
            consultaEliminacion = consultaEliminacion.replace("@", "participacionesInversor");
        }
        if (u instanceof Empresa) {
            consultaSustracion = consultaSustracion.replace("@", "participacionesEmpresa");
            consultaEliminacion = consultaEliminacion.replace("@", "participacionesEmpresa");
        }

        try {
            con.setAutoCommit(false);

            // 1. CREAR OFERTA DE VENTA
            stmOferta = con.prepareStatement(consultaOferta);
            stmOferta.setString(1, u.getIdUsuario());
            stmOferta.setString(2, e.getIdUsuario());
            stmOferta.setDate(3, new Date(System.currentTimeMillis()));
            stmOferta.setInt(4, numero);
            stmOferta.setFloat(5, precioVenta);
            stmOferta.executeUpdate();

            // 2. SUSTRAER PARTICIPACIONES
            int nuevaCantidad = this.getParticipacionesEmpresa(u, e) - numero;
            stmSustracion = con.prepareStatement(consultaSustracion);
            stmSustracion.setInt(1, nuevaCantidad);
            stmSustracion.setString(2, u.getIdUsuario());
            stmSustracion.setString(3, e.getIdUsuario());
            stmSustracion.executeUpdate();

            if (nuevaCantidad == 0) {
                // 3. ELIMINAR LA TUPLA YA QUE NO QUEDAN PARTICIPACIONES
                stmEliminacion = con.prepareStatement(consultaEliminacion);
                stmEliminacion.setString(1, u.getIdUsuario());
                stmEliminacion.setString(2, e.getIdUsuario());
                stmEliminacion.executeUpdate();
            }
            String part = numero == 1 ? "1 participación " : (numero + " participaciones ");
            muestraExcepcion("Se ha creado la oferta de venta:\n\n" + u.getIdUsuario() + " vende "
                    + part + "de " + e.getIdUsuario() + " a " + precioVenta + "$", DialogoInfo.NivelDeAdvertencia.INFORMACION);
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                con.setAutoCommit(true);
                stmOferta.close();
                stmSustracion.close();
                if (stmEliminacion != null) {
                    stmEliminacion.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
    private int tipoUsuario(String id_usuario){
        int result = 0;
        
        PreparedStatement stmEmpresa = null, stmInversor = null;
        
        Connection con;
        
        con = this.getConexion();
        
        ResultSet rst1 = null;
        ResultSet rst2 = null;
        
        String empresas = "select id_usuario "
                + "from empresa "
                + "where id_usuario = ? ";
        
        String inversor = "select id_usuario "
                + "from inversor "
                + "where id_usuario = ? ";
        
        try{
            con.setAutoCommit(false);
            
            stmEmpresa = con.prepareStatement(empresas);
            stmEmpresa.setString(1, id_usuario);
            
            rst1 = stmEmpresa.executeQuery();
            
            while(rst1.next()){
                result = 1; //es una empresa
            }
            
            stmInversor = con.prepareStatement(inversor);
            stmInversor.setString(1, id_usuario);
            
            rst2 = stmInversor.executeQuery();
            
            while(rst2.next()){
                result = 2; //es un inversor
            }
            
            con.commit();
            
        }catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                con.setAutoCommit(true);
                stmEmpresa.close();
                if (stmInversor != null) {
                    stmInversor.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
        
        
        return result;
    }

    public void comprarParticipaciones(Usuario comprador, Empresa empresa, int cantidad, float precioMax) {
        if (comprador == null || comprador instanceof Regulador) {
            manejarExcepcion(new Exception("El usuario no puede comprar participaciones!"));
            return;
        }
        boolean salir = false;
        int participacionesIteracion = 0, participacionesTotales = 0;
        float precioAcumulado = 0, precioIteracion = 0, saldoCompra = 0;
        PreparedStatement stmParticipaciones = null, stmUpdate = null, stmEliminacion = null;
        ResultSet rst = null;
        Connection con;

        con = this.getConexion();

        String consultaOfertas = "select * \n"
                + "from ofertaVenta\n"
                + "where empresa = ? \n"
                + "AND fecha = \n"
                + "\t(select MIN(fecha) \n"
                + "\t from ofertaVenta \n"
                + "\t where empresa = ? AND precio = \n"
                + "\t \t(select MIN(precio) \n"
                + "\t\t from ofertaVenta \n"
                + "\t\t where empresa = ?))\n"
                + "AND precio = \n"
                + "\t (select MIN(precio) \n"
                + "\t  from ofertaVenta \n"
                + "\t  where empresa = ?)";

        String eliminacionOferta = "delete from ofertaVenta where usuario = ? AND fecha = ?";

        String updateOferta = "update ofertaVenta set numparticipaciones = ? where usuario = ? AND fecha = ?";

        String updateCarterasComprador = "update public.@ set numparticipaciones = ? where usuario=? AND empresa=? ";
        
        String updateCarterasVendedor = "update public.@ set numparticipaciones = ? where usuario=? AND empresa=? ";

        if (comprador instanceof Inversor) {
            updateCarterasComprador = updateCarterasComprador.replace("@", "participacionesInversor");
            saldoCompra = ((Inversor) comprador).getSaldo();
        }
        if (comprador instanceof Empresa) {
            updateCarterasComprador = updateCarterasComprador.replace("@", "participacionesEmpresa");
            saldoCompra = ((Empresa) comprador).getSaldo();
        }

        StringBuilder logOperacion = new StringBuilder();
        //lo primero es escoger las participaciones y ver cuanto cuestan
        try {
            con.setAutoCommit(false);
            while (participacionesTotales < cantidad && salir != true) { //mientras no se hayan escogido todas las pedidas seguimos cogiendo
                stmParticipaciones = con.prepareStatement(consultaOfertas);
                stmParticipaciones.setString(1, empresa.getIdUsuario());
                stmParticipaciones.setString(2, empresa.getIdUsuario());
                stmParticipaciones.setString(3, empresa.getIdUsuario());
                stmParticipaciones.setString(4, empresa.getIdUsuario());
                rst = stmParticipaciones.executeQuery();
                while (rst.next()) {

                    //variables de la iteracion
                    participacionesIteracion = rst.getInt("numparticipaciones");
                    precioIteracion = rst.getFloat("precio");
                    int participacionesCompradas;

                    //sustrer participaciones
                    if (participacionesIteracion + participacionesTotales > cantidad) {
                        //como nos pasamos, tenemos que actualizar la oferta de venta y no eliminarla, y vamos a sumar
                        // unicamente las participaciones necesarias a la compra, siendo estas tmb las que se
                        // eliminan de la oferta de venta
                        participacionesCompradas = cantidad - participacionesTotales;
                        if (precioAcumulado + (float) participacionesCompradas * (float) precioIteracion > saldoCompra) {
                            //reducimos numero participaciones compradas

                            float saldoRonda = saldoCompra - precioAcumulado;

                            participacionesCompradas = (int) (saldoRonda / precioIteracion);

                            salir = true;
                        }
                        precioAcumulado += (float) participacionesCompradas * (float) precioIteracion;

                    } else {
                        //como la oferta de venta no cubre entera la cantidad la eliminamos y pasamos a la siguiente
                        //las sumamos a las enteras
                        participacionesCompradas = participacionesIteracion;

                        if (precioAcumulado + (float) participacionesCompradas * (float) precioIteracion > saldoCompra) {
                            //reducimos numero participaciones compradas
                            float saldoRonda = saldoCompra - precioAcumulado;

                            participacionesCompradas = (int) (saldoRonda / precioIteracion);

                            salir = true;
                        }
                        precioAcumulado += (float) participacionesCompradas * (float) precioIteracion;
                    }

                    if (participacionesCompradas == participacionesIteracion) {
                        stmEliminacion = con.prepareStatement(eliminacionOferta);
                        stmEliminacion.setString(1, rst.getString("usuario"));
                        stmEliminacion.setDate(2, rst.getDate("fecha"));

                        stmEliminacion.executeUpdate();
                    } else {
                        stmUpdate = con.prepareStatement(updateOferta);
                        stmUpdate.setInt(1, participacionesIteracion - participacionesCompradas);
                        stmUpdate.setString(2, rst.getString("usuario"));
                        stmUpdate.setDate(3, rst.getDate("fecha"));

                        stmUpdate.executeUpdate();
                    }
                    
                    String vendedor = rst.getString("id_usuario");
                    
                    if(this.tipoUsuario(vendedor) == 1){
                        //es empresa
                        updateCarterasVendedor = updateCarterasVendedor.replace("@", "participacionesEmpresa");
                    }else{
                        //es usuario
                        updateCarterasVendedor = updateCarterasVendedor.replace("@", "participacionesInversor");
                    }
                    
                    
                    //quiero quitar las participaciones compradas de la cartera del vendedor, y ademas meterlas en la del comprador
                    
                    //por ultimo tendremos que actualizar los saldos, añadir el saldo al vendedor substrayendo del comprador
                    
                    participacionesTotales += participacionesCompradas;
                    logOperacion.append("Se han comprado " + participacionesCompradas
                            + " al usuario " + rst.getString("usuario")
                            + " que las puso a la venta el " + rst.getDate("fecha")
                            + " a " + precioIteracion + "$\n");
                    logOperacion.append("Se han comprado " + participacionesTotales + "/" + cantidad + " por " + precioAcumulado + "$\n");
                }
            }
            System.out.println(logOperacion);


            /*
            // Estaría bien preguntar al usuario si desea realizar esa operación con el coste asociado
            // que acabamos de calcular, por ejemplo si la operación gastaría más del 10% de su saldo

            //habria que hacer un switch para cada tipo de user, es decir segun sea empresa o inversor hara una cosa o otra
            // (solo usando instanceof se puede hacer)
            //float saldoUsuario = ; aqui necesitamos saber el saldo
            if(precioAcumulado >= saldo){
                //error pero no se si puedo lanzar una excepcion y tal para que haga catch y cierre los cursores
                // Creo que se aborta así???
                stmParticipaciones.cancel();
                stmEliminacion.cancel();
                stmUpdate.cancel();

            }else{
                //se ejecutará todo si se cumple la condicion, de manera que quedaran eliminadas las ofertas de venta
            
                //como cumple la condicion, cambiaremos las carteras de participaciones de cada uno, del empresa eliminaremos las que tenia y al comprador se las ponemos
                con.commit();
            }

            
                
            tras todo eso, el precio acumulado tiene que ser <= al saldo, el saldo no esta en user, asi que necesito una clase para conseguir su saldo
                
            hago un filtro o cambio lo de pasarle un usuario por el tipo de usuario que es?*/
            //con.commit();
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                con.setAutoCommit(true);
                stmParticipaciones.close();
                if (stmUpdate != null) {
                    stmUpdate.close();
                }
                if (stmEliminacion != null) {
                    stmEliminacion.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

    }

}
