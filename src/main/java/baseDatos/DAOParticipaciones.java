package baseDatos;

import aplicacion.*;
import vista.componentes.DialogoInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vista.FachadaGUI;
import vista.VentanaConfirmacion;
import vista.componentes.Utils;

public class DAOParticipaciones extends AbstractDAO {

    private static final float CONFIRMACION_LIMITE = 10.0f; // A partir de este x%, se pedirá confirmación para una compra

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
                if (stmCheck != null) {
                    stmCheck.close();
                }
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
    public int getParticipacionesEmpresa(Usuario u, String e) {
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
            stmCheck.setString(2, e);
            rst = stmCheck.executeQuery();
            while (rst.next()) {
                result = rst.getInt("result");
            }
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
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
        return result;
    }

    /**
     * Obtiene el número de participaciones vendidas de la empresa
     *
     * @param empresa Nombre de la empresa
     * @return El número de participaciones vendidas
     */
    private int participacionesVendidas(String empresa) {

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

    /**
     * Obtiene el número de participaciones del usuario de una empresa Tiene en
     * cuenta los anuncios para calcular el máximo de participaciones que puede
     * vender
     *
     * @param u Inversor o Empresa que tiene las participaciones
     * @param e Empresa a las que están asociadas esas participaciones
     * @return El número de participaciones de u en e
     */
    public int getParticipacionesVendibles(Usuario u, Empresa e) {
        if (u == null || u instanceof Regulador) {
            return 0;
        }

        int result = 0;
        PreparedStatement stmCheck = null;
        ResultSet rst;

        PreparedStatement stmComprobacion = null;
        ResultSet rstComprobacion;

        PreparedStatement stmAnuncios = null, stmPartOferta = null;
        ResultSet rstAnuncios, rstPartOferta;

        Connection con;

        con = this.getConexion();

        String consulta = "select numparticipaciones as result "
                + "from @ "
                + "where usuario = ? AND empresa = ? ";
        String obtenerAnuncios = "select * from anunciobeneficios where empresa = ?";

        String obtenerPartEnOferta = "select sum(numparticipaciones) as result from ofertaventa where usuario = ? " +
                "and empresa = ? group by usuario, empresa";
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
            // El resultado normal, si es empresa o no hay anuncios es simplemente la cantidad de participaciones
            // de esa empresa
            while (rst.next()) {
                result = rst.getInt("result");
            }

            if (u instanceof Empresa && u.getIdUsuario().equals(e.getIdUsuario())) {
                try {
                    int partsEnOferta = 0;
                    stmPartOferta = con.prepareStatement(obtenerPartEnOferta);
                    stmPartOferta.setString(1, e.getIdUsuario());
                    stmPartOferta.setString(2, e.getIdUsuario());
                    rstPartOferta = stmPartOferta.executeQuery();
                    while (rstPartOferta.next()) {
                        partsEnOferta = rstPartOferta.getInt("result");
                    }
                    stmCheck = con.prepareStatement(consulta);
                    stmCheck.setString(1, u.getIdUsuario());
                    stmCheck.setString(2, e.getIdUsuario());
                    rst = stmCheck.executeQuery();
                    stmAnuncios = con.prepareStatement(obtenerAnuncios);
                    stmAnuncios.setString(1, e.getIdUsuario());
                    rstAnuncios = stmAnuncios.executeQuery();
                    if (rstAnuncios.isBeforeFirst()) {
                        /*
                         * En esta sección, se calcula cuantas participaciones se podrán vender de forma que después
                         * se pueda afrontar el pago de efectivo y participaciones total para cada uno de los anuncios
                         */
                        int participacionesADarPorVendida = 0;
                        float dineroADarPorVendida = 0.0f;
                        int participacionesPropias = getParticipacionesEmpresa(u, u.getIdUsuario());
                        float dineroPropio = ((Empresa) u).getSaldo();

                        while (rstAnuncios.next()) {
                            participacionesADarPorVendida += rstAnuncios.getInt("numeroparticipaciones");
                            dineroADarPorVendida += rstAnuncios.getFloat("importeparticipacion");
                        }

                        /*
                         * El 1.0f + es porque se venderán esas participaciones, hay que tenerlas en cuenta
                         * Por ejemplo: Si E tiene 500 part y 1000$ y tiene que pagar 4 part por vendida y 2$ por vendida
                         * no podrá vender 125 realmente, porque entonces tendría que bloquear 500 pero no las tendría
                         * porque vendió 125
                         */
                        result = (int) Math.min(participacionesPropias / (participacionesADarPorVendida + 1.0f) -
                                        (float) partsEnOferta,
                                dineroPropio / dineroADarPorVendida);
                        result = Math.max(result, 0);
                    }
                } catch (SQLException ex) { //hay que cambiar la exception de e a ex, lo hago abajo tambien
                    manejarExcepcionSQL(ex);
                } finally {
                    try {
                        if (stmAnuncios != null)
                            stmAnuncios.close();
                    } catch (SQLException ex) {
                        System.out.println("Imposible cerrar cursores");
                    }
                }
            }
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (stmCheck != null) {
                    stmCheck.close();
                }
                if (stmAnuncios != null) {
                    stmAnuncios.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return result;
    }

    public int getPartPropEmpresa(Empresa e) {
        return getParticipacionesTotales(e);
    }

    public void bajaParticipaciones(Empresa e, int cantidad) {
        int antiguasPart = getParticipacionesEmpresa(e, e.getIdUsuario());
        PreparedStatement stmUpdate = null;
        ResultSet rst;
        Connection con;
        boolean done = false;
        con = this.getConexion();
        String consulta = "update participacionesempresa "
                + "set numparticipaciones=? "
                + "where usuario=? AND empresa=?";

        try {
            con.setAutoCommit(false);
            stmUpdate = con.prepareStatement(consulta);
            stmUpdate.setInt(1, antiguasPart - cantidad);
            stmUpdate.setString(2, e.getIdUsuario());
            stmUpdate.setString(3, e.getIdUsuario());
            stmUpdate.executeUpdate();
            fa.insertarHistorial(new EntradaHistorial(e.getIdUsuario(), e.getIdUsuario(),
                    new Timestamp(System.currentTimeMillis()), cantidad, null, EntradaHistorial.TipoEntradaHistorial.BAJA));
            done = true;
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }
                if (stmUpdate != null) {
                    stmUpdate.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void emitirParticipaciones(Empresa e, int cantidad) {
        int antiguasPart = 0;
        PreparedStatement stmAntiguas = null;
        PreparedStatement stmUpdate = null;
        PreparedStatement stmNueva = null;
        PreparedStatement stmCartera = null;
        ResultSet rst;
        Connection con;
        boolean done = false;
        con = this.getConexion();

        String participacionesAnteriores = "select numparticipaciones "
                + "from participacionesempresa "
                + "where usuario=? AND empresa=?";

        String updateCartera = "update participacionesempresa "
                + "set numparticipaciones=? "
                + "where usuario=? AND empresa=?";

        //añadir una participacionesAnteriores que cree la cartera si es que no esta creada, ya que el update si no esta creada no funciona
        String nuevaCartera = "insert into participacionesempresa(usuario, empresa, numparticipaciones) values(?,?,?);";

        String nuevaEmision = "insert into emitirparticipaciones(empresa, fechaemision, numeroparticipaciones) values(?,?,?);";

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
            stmNueva.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmNueva.setInt(3, cantidad);

            stmNueva.executeUpdate();

            if (antiguasPart == 0) {
                stmCartera = con.prepareStatement(nuevaCartera);
                stmCartera.setString(1, e.getIdUsuario());
                stmCartera.setString(2, e.getIdUsuario());
                stmCartera.setInt(3, 0);

                stmCartera.executeUpdate();
            }

            stmUpdate = con.prepareStatement(updateCartera);
            stmUpdate.setInt(1, cantidad + antiguasPart);
            stmUpdate.setString(2, e.getIdUsuario());
            stmUpdate.setString(3, e.getIdUsuario());

            stmUpdate.executeUpdate();

            // e emite acciones de e
            fa.insertarHistorial(new EntradaHistorial(e.getIdUsuario(), e.getIdUsuario(), new Timestamp(System.currentTimeMillis()),
                    cantidad, null, EntradaHistorial.TipoEntradaHistorial.EMISION));

            done = true;
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }

                if (stmAntiguas != null) {
                    stmAntiguas.close();
                }

                if (stmCartera != null) {
                    stmCartera.close();
                }

                if (stmUpdate != null) {
                    stmUpdate.close();
                }

                if (stmNueva != null) {
                    stmNueva.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }

    }

    public void crearOfertaVenta(Usuario u, String e, int numero, float precioVenta) {
        if (u == null || u instanceof Regulador) {
            manejarExcepcion(new Exception("El usuario no puede crear ofertas de venta!"));
            return;
        }

        int result = 0;
        PreparedStatement stmOferta = null, stmSustracion = null, stmEliminacion = null;
        Connection con;
        boolean done = false;
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
            stmOferta.setString(2, e);
            stmOferta.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmOferta.setInt(4, numero);
            stmOferta.setFloat(5, precioVenta);
            stmOferta.executeUpdate();

            // 2. SUSTRAER PARTICIPACIONES
            int nuevaCantidad = this.getParticipacionesEmpresa(u, e) - numero;
            stmSustracion = con.prepareStatement(consultaSustracion);
            stmSustracion.setInt(1, nuevaCantidad);
            stmSustracion.setString(2, u.getIdUsuario());
            stmSustracion.setString(3, e);
            stmSustracion.executeUpdate();

            if (nuevaCantidad == 0) {
                // 3. ELIMINAR LA TUPLA YA QUE NO QUEDAN PARTICIPACIONES
                stmEliminacion = con.prepareStatement(consultaEliminacion);
                stmEliminacion.setString(1, u.getIdUsuario());
                stmEliminacion.setString(2, e);
                stmEliminacion.executeUpdate();
            }
            String part = numero == 1 ? "1 participación " : (numero + " participaciones ");
            muestraExcepcion("Se ha creado la oferta de venta:\n\n" + u.getIdUsuario() + " vende "
                    + part + "de " + e + " a " + precioVenta + "$", DialogoInfo.NivelDeAdvertencia.INFORMACION);

            fa.insertarHistorial(new EntradaHistorial(e, u.getIdUsuario(), new Timestamp(System.currentTimeMillis()),
                    numero, precioVenta, EntradaHistorial.TipoEntradaHistorial.VENTA));

            done = true;
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done) {
                    con.commit();
                } else {
                    con.rollback();
                }
                if (stmOferta != null) {
                    stmOferta.close();
                }
                if (stmSustracion != null) {
                    stmSustracion.close();
                }
                if (stmEliminacion != null) {
                    stmEliminacion.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void bajaOfertaVenta(Usuario usuario, Timestamp fecha) {
        java.util.List<OfertaVenta> resultado = new java.util.ArrayList<>();
        PreparedStatement stmSelecion = null, stmBorrado = null;
        ResultSet rst;
        Connection con;
        boolean done = false;
        con = this.getConexion();
        int cantidad = 0;
        String empresa = "";
        String seleccion = " select * from ofertaVenta where usuario = ? and fecha = ?";
        String borrado = " delete from ofertaVenta "
                + "where usuario = ? "
                + "and fecha = ? ";

        try {
            con.setAutoCommit(false);
            stmSelecion = con.prepareStatement(seleccion);
            stmSelecion.setString(1, usuario.getIdUsuario());
            stmSelecion.setTimestamp(2, fecha);
            rst = stmSelecion.executeQuery();

            while (rst.next()) {
                cantidad = rst.getInt("numparticipaciones");
                empresa = rst.getString("empresa");
            }

            stmBorrado = con.prepareStatement(borrado);
            stmBorrado.setString(1, usuario.getIdUsuario());
            stmBorrado.setTimestamp(2, fecha);
            stmBorrado.executeUpdate();

            darParticipaciones(usuario, cantidad, empresa);
            muestraExcepcion("Se ha dado de baja la oferta de venta:\n\n", DialogoInfo.NivelDeAdvertencia.INFORMACION);
            done = true;

        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            try {
                if (done)
                    con.commit();
                else
                    con.rollback();
                if (stmBorrado != null) {
                    stmBorrado.close();
                }
                if (stmBorrado != null) {
                    stmBorrado.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void comprarParticipaciones(Usuario comprador, String empresa, int cantidad, float precioMax,
                                       float comision, Usuario regulador, java.util.List<AnuncioBeneficios> anuncios) {

        // Comprobar que el comprador es un usuario válido
        if (comprador == null || comprador instanceof Regulador || !comprador.isAutorizado()) {
            FachadaGUI.muestraExcepcion("El usuario no puede comprar participaciones!",
                    DialogoInfo.NivelDeAdvertencia.ERROR);
            return;
        }

        if (comprador.isSolicitadobaja()) {
            FachadaGUI.muestraExcepcion("El usuario no puede comprar participaciones ya que solicitó su baja!",
                    DialogoInfo.NivelDeAdvertencia.ADVERTENCIA);
            return;
        }

        // Actualizar datos por si son incorrectos al llamar al método
        if (comprador instanceof Inversor) {
            comprador = fa.obtenerDatosInversor(comprador);
        }
        if (comprador instanceof Empresa) {
            comprador = fa.obtenerDatosEmpresa(comprador);
        }

        boolean dineroAgotado = false;
        int participacionesIteracion = 0, participacionesCompradas = 0;
        float precioAcumulado = 0, precioIteracion = 0, saldoCompra = 0;
        PreparedStatement stmParticipaciones = null, stmUpdate = null, stmEliminacion = null;
        ResultSet rst;
        Connection con;
        boolean done = false;

        // Lista de empresas que tienen anuncios y de las cuales vamos a bloquear saldo si se compran
        // sus participaciones
        HashMap<Empresa, Integer> participacionesBloquear = new HashMap<>();

        con = this.getConexion();

        // Lista ordenada por precio de las mejores ofertas que no son del usuario
        String listaMejoresOfertas = "select * from ofertaventa \n"
                + "where empresa = ? AND usuario != ?\n"
                + "order by precio asc, fecha asc";

        String eliminacionOferta = "delete from ofertaVenta where usuario = ? AND fecha = ?";

        String updateOferta = "update ofertaVenta set numparticipaciones = ? where usuario = ? AND fecha = ?";

        if (comprador instanceof Inversor) {
            saldoCompra = ((Inversor) comprador).getSaldo();
        }
        if (comprador instanceof Empresa) {
            saldoCompra = ((Empresa) comprador).getSaldo();
        }

        boolean sePidioConfirmacion = false;

        try {
            con.setAutoCommit(false);
            stmParticipaciones = con.prepareStatement(listaMejoresOfertas);
            stmParticipaciones.setString(1, empresa);
            stmParticipaciones.setString(2, comprador.getIdUsuario());
            rst = stmParticipaciones.executeQuery();

            // La compra se hará iterativamente sobre las mejores ofertas
            // Seguiremos iterando mientras queden suficientes ofertas,
            // no hallamos comprado todas las que necesitamos y haya suficiente dinero
            while (rst.next() && participacionesCompradas < cantidad && !dineroAgotado) {
                // Numero de participaciones disponibles en la oferta actual y precio en la oferta actual
                participacionesIteracion = rst.getInt("numparticipaciones");
                precioIteracion = rst.getFloat("precio") + (comision * rst.getFloat("precio"));

                // El mínimo de las que hay y las que faltan por comprar
                int partCompradasIteraccion = Math.min(participacionesIteracion, cantidad - participacionesCompradas);

                // No hay suficiente dinero
                if (precioAcumulado + partCompradasIteraccion * precioIteracion > saldoCompra) {
                    float saldoRonda = saldoCompra - precioAcumulado;

                    // Reducimos el número de participaciones compradas
                    partCompradasIteraccion = (int) (saldoRonda / precioIteracion);

                    // Hace falta otro booleano para esto, por ejemplo, si la última participación a comprar vale 5$
                    // pero solo quedan  3$, no basta comprobar precioAcumulado == saldoCompra
                    dineroAgotado = true;
                }

                // Comprobamos que la empresa pueda bloquear suficiente saldo en el caso de que quereamos
                // comprar participaciones de una empresa con anuncios (este es un caso bastante inusual)
                if (anuncios.size() > 0 && rst.getString("usuario").equals(empresa)) {
                    Empresa e = fa.obtenerDatosEmpresa(new Usuario(empresa, false, true));

                    int participacionesADarPorVendida = 0;
                    float dineroADarPorVendida = 0.0f;
                    int participacionesPropias = getParticipacionesEmpresa(e, e.getIdUsuario());
                    float dineroPropio = e.getSaldo();

                    for (AnuncioBeneficios a : anuncios) {
                        participacionesADarPorVendida += a.getNumeroparticipaciones();
                        dineroADarPorVendida += a.getImporteparticipacion();
                    }


                    int result = (int) Math.min((float) participacionesPropias / (float) participacionesADarPorVendida,
                            dineroPropio / dineroADarPorVendida);

                    // Se actualiza el número de participaciones a comprar, a lo mejor no se puede bloquear
                    // suficiente saldo/part para efectuar el pago de sus anuncios posteriormente
                    partCompradasIteraccion = Math.min(result, partCompradasIteraccion);

                    // Bloqueamos el salddo después del bucle para evitar problemas con el autocommit
                    // al poder comprar de diferentes ofertas de venta de la empresa
                    int partActuales = participacionesBloquear.getOrDefault(e, 0);
                    participacionesBloquear.put(e, partActuales + partCompradasIteraccion);
                }

                // Actualizamos el precio total gastado y el número total de participaciones compradas
                precioAcumulado += (float) partCompradasIteraccion * precioIteracion;
                participacionesCompradas += partCompradasIteraccion;

                // Ahora que ya sabemos cuantas vamos a comprar las quitamos de la oferta
                if (partCompradasIteraccion == participacionesIteracion) {
                    stmEliminacion = con.prepareStatement(eliminacionOferta);
                    stmEliminacion.setString(1, rst.getString("usuario"));
                    stmEliminacion.setTimestamp(2, rst.getTimestamp("fecha"));

                    stmEliminacion.executeUpdate();
                } else {
                    stmUpdate = con.prepareStatement(updateOferta);
                    stmUpdate.setInt(1, participacionesIteracion - partCompradasIteraccion);
                    stmUpdate.setString(2, rst.getString("usuario"));
                    stmUpdate.setTimestamp(3, rst.getTimestamp("fecha"));

                    stmUpdate.executeUpdate();
                }

                // Obtenemos los datos del vendedor
                String nombreVendedor = rst.getString("usuario");
                Usuario vendedor = fa.obtenerDatosInversor(new Usuario(nombreVendedor, false, true));
                if (vendedor == null) {
                    vendedor = fa.obtenerDatosEmpresa(new Usuario(nombreVendedor, false, true));
                }

                // Añadimos las participaciones a la cartera del comprador
                darParticipaciones(comprador, partCompradasIteraccion, empresa);

                // Le damos su dinero al vendedor y su comision al regulador,
                // el pago del comprador lo podemos restar al final fuera del bucle
                modificarSaldo(vendedor, partCompradasIteraccion * (precioIteracion - rst.getFloat("precio") * comision));
                modificarSaldo(regulador, partCompradasIteraccion * (rst.getFloat("precio") * comision));
            }

            // Bloqueamos el saldo a las empresas con anuncios de las que se han comprado participaciones
            for (Map.Entry<Empresa, Integer> entradas : participacionesBloquear.entrySet()) {
                if (entradas.getValue() > 0) {
                    fa.bloquearSaldo(entradas.getKey(), entradas.getValue());
                }
            }

            // Cobramos al inversor
            modificarSaldo(comprador, -precioAcumulado);

            // Pedir confirmación de compra si el dinero gastado es grande (más del CONFIRMACION_LIMITE %)
            float porcentajeGastado = (precioAcumulado / saldoCompra) * 100;
            if (porcentajeGastado > CONFIRMACION_LIMITE) {
                if (participacionesCompradas == cantidad) {
                    // Se pudieron comprar todas, pero se gasto mucho dinero
                    new VentanaConfirmacion(FachadaGUI.getInstance().getVentanaActiva(), con, "Esta compra le costará "
                            + Utils.displayCurrency(precioAcumulado) + ", el " + String.format("%.2f", (porcentajeGastado)) + "% de su saldo \n"
                            + "Desea continuar?", "La compra de las participaciones se ha completado correctamente!",
                            "La compra de las participaciones se ha cancelado correctamente...");
                } else {
                    // No se pudieron comprar todas, se gastó tod0 el dinero además
                    new VentanaConfirmacion(FachadaGUI.getInstance().getVentanaActiva(), con, "Esta compra le costará "
                            + Utils.displayCurrency(precioAcumulado) + ", el " + String.format("%.2f", (porcentajeGastado)) + "% de su saldo y solo se pudieron comprar "
                            + participacionesCompradas + " de las " + cantidad + " pedidas...\n"
                            + "Desea continuar?", "La compra de las participaciones se ha completado correctamente!",
                            "La compra de las participaciones se ha cancelado correctamente...");
                }
                sePidioConfirmacion = true; // Hay que hacer commit de la transación en VentanaConfirmacion no aquí
            }

            // Añadimos la entrada de compra al historial
            fa.insertarHistorial(new EntradaHistorial(empresa, comprador.getIdUsuario(),
                    new Timestamp(System.currentTimeMillis()), participacionesCompradas, precioMax, EntradaHistorial.TipoEntradaHistorial.COMPRA));

            done = true;
        } catch (SQLException ex) {
            manejarExcepcionSQL(ex);
        } finally {
            // Cerrar stms y confirmar la transación si no se pidió confirmación manual
            try {
                if (!sePidioConfirmacion) {
                    if (done) {
                        con.commit();
                    } else {
                        con.rollback();
                    }
                    con.setAutoCommit(true);
                }
                if (stmParticipaciones != null) {
                    stmParticipaciones.close();
                }
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

    private void darParticipaciones(Usuario destino, int cantidad, String empresa) throws SQLException {
        // Mover las participaciones de la carte de vendedor a la de comprador
        PreparedStatement stmCreacion = null, stmUpdate = null;
        ResultSet rst = null;
        Connection con;
        con = this.getConexion();

        // Aquí el autocommit ya debería estar en false
        String crearComprador = "INSERT INTO public.@(\n"
                + "\tusuario, empresa, numparticipaciones)\n"
                + "\tVALUES (?, ?, ?);";
        String sumarComprador = "update public.@ set numparticipaciones = ? where usuario=? AND empresa=? ";

        if (destino instanceof Inversor) {
            crearComprador = crearComprador.replace("@", "participacionesInversor");
            sumarComprador = sumarComprador.replace("@", "participacionesInversor");
        } else {
            crearComprador = crearComprador.replace("@", "participacionesEmpresa");
            sumarComprador = sumarComprador.replace("@", "participacionesEmpresa");
        }

        try {
            int participacionesPreviasComprador = this.getParticipacionesEmpresa(destino, empresa);

            if (participacionesPreviasComprador == 0) {
                // Crear tabla de participaciones en la cartera del comprador
                stmCreacion = con.prepareStatement(crearComprador);
                stmCreacion.setString(1, destino.getIdUsuario());
                stmCreacion.setString(2, empresa);
                stmCreacion.setInt(3, 0);
                stmCreacion.executeUpdate();
            }
            // Sumar en la cartera del comprador
            stmUpdate = con.prepareStatement(sumarComprador);
            stmUpdate.setInt(1, participacionesPreviasComprador + cantidad);
            stmUpdate.setString(2, destino.getIdUsuario());
            stmUpdate.setString(3, empresa);
            stmUpdate.executeUpdate();
        } finally {
            try {
                if (stmCreacion != null) {
                    stmCreacion.close();
                }
                if (stmUpdate != null) {
                    stmUpdate.close();
                }
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
        String modificarSaldo = "update public.@ set saldo = saldo + ? where id_usuario=?";
        if (u instanceof Inversor) {
            modificarSaldo = modificarSaldo.replace("@", "inversor");
        } else if (u instanceof Empresa) {
            modificarSaldo = modificarSaldo.replace("@", "empresa");
        } else {
            modificarSaldo = modificarSaldo.replace("@", "regulador");
        }

        // Encontramos la tabla para el update en las cartera del comprador
        try {
            stmUpdate = con.prepareStatement(modificarSaldo);
            stmUpdate.setFloat(1, cantidad);
            stmUpdate.setString(2, u.getIdUsuario());
            stmUpdate.executeUpdate();
        } finally {
            try {
                if (stmUpdate != null) {
                    stmUpdate.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public List<EntradaParticipacion> obtenerDatosParticipaciones(Usuario u) {
        List<EntradaParticipacion> result = new ArrayList<>();

        if (u == null || u instanceof Regulador) {
            return result;
        }

        PreparedStatement stmCheck = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "select * "
                + "from @ "
                + "where usuario = ?";

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
                result.add(new EntradaParticipacion(
                        rst.getString("usuario"),
                        rst.getString("empresa"),
                        rst.getInt("numparticipaciones")));
            }
        } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
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
        return result;
    }

}
