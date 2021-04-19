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

    public void emitirParticipaciones(Empresa e, int emision, float precio) {
        int antiguasPart = 0;
        PreparedStatement stmAntiguas = null;
        PreparedStatement stmUpdate = null;
        PreparedStatement stmNueva = null;
        PreparedStatement stmCartera = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String participacionesAnteriores = "select numparticipaciones "
                + "from participacionesempresa "
                + "where usuario=? AND empresa=?";

        String updateCartera = "update participacionesempresa "
                + "set numparticipaciones=? "
                + "where usuario=? AND empresa=?";
        
        //añadir una participacionesAnteriores que cree la cartera si es que no esta creada, ya que el update si no esta creada no funciona
        String nuevaCartera = "insert into participacionesempresa(usuario, empresa, numparticipaciones) values(?,?,?);";

        String nuevaEmision = "insert into emitirparticipaciones(empresa, fechaemision, numeroparticipaciones, precio) values(?,now(),?, ?);";

        try {
            con.setAutoCommit(false);
            stmAntiguas = con.prepareStatement(participacionesAnteriores);
            stmAntiguas.setString(1, e.getIdUsuario());
            stmAntiguas.setString(2, e.getIdUsuario());
            rst = stmAntiguas.executeQuery();
            while (rst.next()) {
                antiguasPart = rst.getInt("numparticipaciones");
            }
            
            stmNueva = con.prepareStatement(nuevaEmision);
            stmNueva.setString(1, e.getIdUsuario());
            stmNueva.setInt(2, emision);
            stmNueva.setFloat(3, precio);
            
            stmNueva.executeUpdate();
            
            if(antiguasPart == 0){
                stmCartera = con.prepareStatement(nuevaCartera);
                stmCartera.setString(1, e.getIdUsuario());
                stmCartera.setString(2, e.getIdUsuario());
                stmCartera.setInt(3, 0);
                
                stmCartera.executeUpdate();
            }
            
            stmUpdate = con.prepareStatement(updateCartera);
            stmUpdate.setInt(1, emision + antiguasPart);
            stmUpdate.setString(2, e.getIdUsuario());
            stmUpdate.setString(3, e.getIdUsuario());
            
            stmUpdate.executeUpdate();
            
            con.commit();
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                con.setAutoCommit(true);
                if(stmAntiguas != null){
                    stmAntiguas.close();
                }
                
                if(stmCartera != null){
                    stmCartera.close();
                }
                
                if(stmUpdate != null){
                    stmUpdate.close();
                }
                
                if(stmNueva != null){
                    stmNueva.close();
                }
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

    public void comprarParticipaciones(Usuario comprador, Empresa empresa, int cantidad, float precioMax) {
        if (comprador == null || comprador instanceof Regulador) {
            manejarExcepcion(new Exception("El usuario no puede comprar participaciones!"));
            return;
        }
        boolean dineroAgotado = false;
        int participacionesIteracion = 0, participacionesCompradas = 0;
        float precioAcumulado = 0, precioIteracion = 0, saldoCompra = 0;
        PreparedStatement stmParticipaciones = null, stmUpdate = null, stmEliminacion = null;
        ResultSet rst = null;
        Connection con;

        con = this.getConexion();

        String encontrarMejorOferta = "select * \n"
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

        StringBuilder logOperacion = new StringBuilder();

        if (comprador instanceof Inversor)
            saldoCompra = ((Inversor) comprador).getSaldo();
        if (comprador instanceof Empresa)
            saldoCompra = ((Empresa) comprador).getSaldo();

        try {
            con.setAutoCommit(false);

            while (participacionesCompradas < cantidad && !dineroAgotado) { //mientras no se hayan escogido todas las pedidas seguimos cogiendo y si no hay dinero salimos del bucle    
                stmParticipaciones = con.prepareStatement(encontrarMejorOferta);
                stmParticipaciones.setString(1, empresa.getIdUsuario());
                stmParticipaciones.setString(2, empresa.getIdUsuario());
                stmParticipaciones.setString(3, empresa.getIdUsuario());
                stmParticipaciones.setString(4, empresa.getIdUsuario());
                rst = stmParticipaciones.executeQuery();
                while (rst.next()) {

                    //variables de la iteracion
                    participacionesIteracion = rst.getInt("numparticipaciones");
                    precioIteracion = rst.getFloat("precio");

                    // El mínimo de las que hay y las que faltan por comprar
                    int partCompradasIteraccion = Math.min(participacionesIteracion, cantidad - participacionesCompradas);

                    // No hay suficiente dinero -> reducimos el número al que podemos comprar
                    if (precioAcumulado + (float) partCompradasIteraccion * precioIteracion > saldoCompra) {
                        float saldoRonda = saldoCompra - precioAcumulado;
                        partCompradasIteraccion = (int) (saldoRonda / precioIteracion);

                        // Hace falta otro booleano para esto por si la última en comprar vale 5 pero solo
                        // te quedan 3, no basta comprobar precioAcumulado == saldoCompra
                        dineroAgotado = true;
                    }

                    precioAcumulado += (float) partCompradasIteraccion * precioIteracion;

                    // Ahora que ya sabemos cuantas vamos a comprar las quitamos de la oferta
                    if (partCompradasIteraccion == participacionesIteracion) {
                        stmEliminacion = con.prepareStatement(eliminacionOferta);
                        stmEliminacion.setString(1, rst.getString("usuario"));
                        stmEliminacion.setDate(2, rst.getDate("fecha"));

                        stmEliminacion.executeUpdate();
                    } else {
                        stmUpdate = con.prepareStatement(updateOferta);
                        stmUpdate.setInt(1, participacionesIteracion - partCompradasIteraccion);
                        stmUpdate.setString(2, rst.getString("usuario"));
                        stmUpdate.setDate(3, rst.getDate("fecha"));

                        stmUpdate.executeUpdate();
                    }

                    String nombreVendedor = rst.getString("usuario");
                    Usuario vendedor = fa.obtenerDatosEmpresa(new Usuario(nombreVendedor, false, true));
                    if (vendedor == null)
                        vendedor = fa.obtenerDatosRegulador(new Usuario(nombreVendedor, false, true));

                    // DEBUG: HASTA AQUÍ VA BIEN
                    participacionesCompradas += partCompradasIteraccion;

                    moverParticipaciones(comprador, vendedor, participacionesCompradas, empresa);


                    // Le damos su dinero al vendedor, el del comprador lo podemos restar al final fuera del bucle
                    modificarSaldo(vendedor, partCompradasIteraccion * precioIteracion);

                    logOperacion.append("Se han comprado " + partCompradasIteraccion
                            + " al usuario " + rst.getString("usuario")
                            + " que las puso a la venta el " + rst.getDate("fecha")
                            + " a " + precioIteracion + "$\n");
                    logOperacion.append("Se han comprado " + participacionesCompradas + "/" + cantidad + " por " + precioAcumulado + "$\n");
                }
            }
            modificarSaldo(comprador, (int) -precioAcumulado);
            System.out.println(logOperacion);
            // TODO: Preguntar al comprador por confirmación si la modificación excede el x% de su saldo actual

        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
            try {
                con.rollback();
            } catch (SQLException ignored) {
                System.out.println("Imposible cerrar cursores");
            }
        } finally {
            try {
                con.commit();
                con.setAutoCommit(true);
                if (stmParticipaciones != null)
                    stmParticipaciones.close();
                if (stmUpdate != null)
                    stmUpdate.close();
                if (stmEliminacion != null)
                    stmEliminacion.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    private void moverParticipaciones(Usuario comprador, Usuario vendedor, int cantidad, Empresa empresa) throws SQLException {
        // Mover las participaciones de la carte de vendedor a la de comprador
        PreparedStatement stmCreacion = null, stmUpdate = null;
        ResultSet rst = null;
        Connection con;
        con = this.getConexion();

        // Aquí el autocommit ya debería estar en false

        String crearComprador = "INSERT INTO public.@(\n" +
                "\tusuario, empresa, numparticipaciones)\n" +
                "\tVALUES (?, ?, ?);";
        String sumarComprador = "update public.@ set numparticipaciones = ? where usuario=? AND empresa=? ";

        String restarVendedor = "update public.@ set numparticipaciones = ? where usuario=? AND empresa=? ";

        // Encontramos la tabla para el update en las cartera del comprador
        if (comprador instanceof Inversor) {
            crearComprador = crearComprador.replace("@", "participacionesInversor");
            sumarComprador = sumarComprador.replace("@", "participacionesInversor");
        } else {
            crearComprador = crearComprador.replace("@", "participacionesEmpresa");
            sumarComprador = sumarComprador.replace("@", "participacionesEmpresa");
        }

        if (vendedor instanceof Inversor) {
            restarVendedor = restarVendedor.replace("@", "participacionesInversor");
        } else {
            restarVendedor = restarVendedor.replace("@", "participacionesEmpresa");
        }

        try {
            int participacionesPreviasComprador = this.getParticipacionesEmpresa(comprador, empresa);
            int participacionesPreviasVendedor = this.getParticipacionesEmpresa(vendedor, empresa);

            if (participacionesPreviasComprador == 0) {
                // Crear tabla de participaciones en la cartera del comprador
                stmCreacion = con.prepareStatement(crearComprador);
                stmCreacion.setString(1, comprador.getIdUsuario());
                stmCreacion.setString(2, empresa.getIdUsuario());
                stmCreacion.setInt(3, 0);
                stmCreacion.executeUpdate();
            }
            // Sumar en la cartera del comprador
            stmUpdate = con.prepareStatement(sumarComprador);
            stmUpdate.setInt(1, participacionesPreviasComprador + cantidad);
            stmUpdate.setString(2, comprador.getIdUsuario());
            stmUpdate.setString(3, empresa.getIdUsuario());
            stmUpdate.executeUpdate();

            // Restar en la cartera del vendedor
            stmUpdate = con.prepareStatement(restarVendedor);
            stmUpdate.setInt(1, participacionesPreviasVendedor - cantidad);
            stmUpdate.setString(2, comprador.getIdUsuario());
            stmUpdate.setString(3, empresa.getIdUsuario());
            stmUpdate.executeUpdate();
            
            
        } finally {
            try {
                if (stmCreacion != null)
                    stmCreacion.close();
                if (stmUpdate != null)
                    stmUpdate.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    private void modificarSaldo(Usuario u, float cantidad) throws SQLException {
        // Mover las participaciones de la carte de vendedor a la de comprador
        PreparedStatement stmUpdate = null;
        ResultSet rst = null;
        Connection con;
        con = this.getConexion();

        // Aquí el autocommit ya debería estar en false

        String modificarSaldo = "update public.@ set saldo = ? where id_usuario=?";
        if (u instanceof Inversor) {
            modificarSaldo = modificarSaldo.replace("@", "inversor");
        } else {
            modificarSaldo = modificarSaldo.replace("@", "empresa");
        }
        // Encontramos la tabla para el update en las cartera del comprador
        try {
            stmUpdate = con.prepareStatement(modificarSaldo);
            if (u instanceof Inversor) {
                stmUpdate.setFloat(1, ((Inversor) u).getSaldo() + cantidad);
            } else {
                stmUpdate.setFloat(1, ((Empresa) u).getSaldo() + cantidad);
            }
            stmUpdate.setString(2, u.getIdUsuario());
            stmUpdate.executeUpdate();
        } finally {
            try {
                if (stmUpdate != null)
                    stmUpdate.close();
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
}
