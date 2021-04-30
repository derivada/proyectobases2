package baseDatos;

import aplicacion.*;
import vista.componentes.DialogoInfo;

import java.sql.*;

import vista.FachadaGui;
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
     * @param empesa Nombre de la empresa
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
                stmConsulta.close();
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
    public int getParticipacionesEmpresa2(Usuario u, Empresa e) {
        if (u == null || u instanceof Regulador) {
            return 0;
        }

        int result = 0;
        PreparedStatement stmCheck = null;
        ResultSet rst;

        PreparedStatement stmComprobacion = null;
        ResultSet rstComprobacion;

        PreparedStatement stmAnuncios = null;
        ResultSet rstAnuncios;
        int numero = 0;
        float importe = 0.f;
        float saldo = 0.f;

        Connection con;

        con = this.getConexion();

        String consulta = "select numparticipaciones as result "
                + "from @ "
                + "where usuario = ? AND empresa = ? ";
        String consulta2 = "select distinct(saldo) as s,sum(numeroparticipaciones) as p,sum(importeparticipacion) as i "
                + "from empresa as e inner join anunciobeneficios as a "
                + "	on ( e.id_usuario=a.empresa and e.id_usuario= ? ) "
                + "group by saldo";

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
            if (u instanceof Empresa) {
                try {
                    stmAnuncios = con.prepareStatement(consulta2);
                    stmAnuncios.setString(1, e.getIdUsuario());
                    rstAnuncios = stmAnuncios.executeQuery();
                    while (rstAnuncios.next()) {

                        numero = rstAnuncios.getInt("p");
                        importe = rstAnuncios.getFloat("i");
                        saldo = rstAnuncios.getFloat("s");

                        //Ahora se hace la comprobación de cuantas participaciones se pueden vender como máximo 
                        //Se parte del máximo, mientras no se pueda, hasta que llegue a un número que si que se pueda 
                        int newresult = result;
                        boolean afrontar = false;
                        while (afrontar == false) {
                            if (newresult == 0) { //No podría vender ninguna
                                afrontar = true;
                            }
                            if ((newresult + this.participacionesVendidas(e.getIdUsuario())) * numero < result
                                    && ((newresult + this.participacionesVendidas(e.getIdUsuario())) * importe < saldo)) {
                                afrontar = true;
                            } else {
                                newresult--;
                            }

                        }
                        return newresult;
                    }

                } catch (SQLException ex) {//hay que cambiar la exception de e a ex, lo hago abajo tambien
                    manejarExcepcionSQL(ex);
                } finally {
                    try {
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
                stmCheck.close();
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
        int antiguasPart = getPartPropEmpresa(e);
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

    public void crearOfertaVenta(Usuario u, Empresa e, int numero, float precioVenta) {
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
            stmOferta.setString(2, e.getIdUsuario());
            stmOferta.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
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

            fa.insertarHistorial(new EntradaHistorial(e.getIdUsuario(), u.getIdUsuario(), new Timestamp(System.currentTimeMillis()),
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

    public void comprarParticipaciones(Usuario comprador, Empresa empresa, int cantidad, float precioMax,
            float comision, Usuario regulador) {
        // Actualizar datos

        if (comprador == null || comprador instanceof Regulador) {
            manejarExcepcion(new Exception("El usuario no puede comprar participaciones!"));
            return;
        }

        // Actualizar datos por si son incorrectos al llamar al método
        if (comprador instanceof Inversor) {
            comprador = fa.obtenerDatosInversor(comprador);
        }
        if (comprador instanceof Empresa) {
            comprador = fa.obtenerDatosEmpresa(comprador);
        }

        empresa = fa.obtenerDatosEmpresa(empresa);

        boolean dineroAgotado = false;
        int participacionesIteracion = 0, participacionesCompradas = 0;
        float precioAcumulado = 0, precioIteracion = 0, saldoCompra = 0;
        PreparedStatement stmParticipaciones = null, stmUpdate = null, stmEliminacion = null;
        ResultSet rst;
        Connection con;
        boolean done = false;

        con = this.getConexion();

        // Lista ordenada por precio de las mejores ofertas
        String listaMejoresOfertas = "select * from ofertaventa \n"
                + "where empresa = ? \n"
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
            stmParticipaciones.setString(1, empresa.getIdUsuario());
            rst = stmParticipaciones.executeQuery();

            // Mientras queden suficientes ofertas && no hallamos comprado todas las que necesitamos && quede dinero
            while (rst.next() && participacionesCompradas < cantidad && !dineroAgotado) {
                // Numero de participaciones disponibles en la oferta actual y precio en la oferta actual

                participacionesIteracion = rst.getInt("numparticipaciones");
                precioIteracion = rst.getFloat("precio") + (comision * rst.getFloat("precio"));

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
                    stmEliminacion.setTimestamp(2, rst.getTimestamp("fecha"));

                    stmEliminacion.executeUpdate();
                } else {
                    stmUpdate = con.prepareStatement(updateOferta);
                    stmUpdate.setInt(1, participacionesIteracion - partCompradasIteraccion);
                    stmUpdate.setString(2, rst.getString("usuario"));
                    stmUpdate.setTimestamp(3, rst.getTimestamp("fecha"));

                    stmUpdate.executeUpdate();
                }

                String nombreVendedor = rst.getString("usuario");
                Usuario vendedor = fa.obtenerDatosInversor(new Usuario(nombreVendedor, false, true));
                if (vendedor == null) {
                    vendedor = fa.obtenerDatosEmpresa(new Usuario(nombreVendedor, false, true));
                }

                participacionesCompradas += partCompradasIteraccion;

                darParticipaciones(comprador, participacionesCompradas, empresa);

                // Le damos su dinero al vendedor, el del comprador lo podemos restar al final fuera del bucle
                modificarSaldo(vendedor, partCompradasIteraccion * (precioIteracion - rst.getFloat("precio") * comision));

                // Hay que añadirle el saldo de la comision al regulador en su saldo
                modificarSaldo(regulador, partCompradasIteraccion * (rst.getFloat("precio") * comision));
            }

            modificarSaldo(comprador, -precioAcumulado);

            // Pedir confirmación si el dinero gastado es grande
            float porcentajeGastado = (precioAcumulado / saldoCompra) * 100;
            if (porcentajeGastado > CONFIRMACION_LIMITE) {
                if (participacionesCompradas == cantidad) {
                    // Se pudieron comprar todas, pero se gasto mucho dinero
                    new VentanaConfirmacion(FachadaGui.getInstance().getVentanaActiva(), con, "Esta compra le costará "
                            + Utils.displayCurrency(precioAcumulado) + ", el " + String.format("%.2f", (porcentajeGastado)) + "% de su saldo \n"
                            + "Desea continuar?", "La compra de las participaciones se ha completado correctamente!",
                            "La compra de las participaciones se ha cancelado correctamente...");
                } else {
                    // No se pudieron comprar todas, se gastó tod0 el dinero además
                    new VentanaConfirmacion(FachadaGui.getInstance().getVentanaActiva(), con, "Esta compra le costará "
                            + Utils.displayCurrency(precioAcumulado) + ", el " + String.format("%.2f", (porcentajeGastado)) + "% de su saldo y solo se pudieron comprar "
                            + participacionesCompradas + " de las " + cantidad + " pedidas...\n"
                            + "Desea continuar?", "La compra de las participaciones se ha completado correctamente!",
                            "La compra de las participaciones se ha cancelado correctamente...");
                }
                sePidioConfirmacion = true; // Hay que cerrar la transación en VentanaConfirmacion no aquí
            }
            fa.insertarHistorial(new EntradaHistorial(empresa.getIdUsuario(), comprador.getIdUsuario(),
                    new Timestamp(System.currentTimeMillis()), cantidad, precioMax, EntradaHistorial.TipoEntradaHistorial.COMPRA));
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

    private void darParticipaciones(Usuario destino, int cantidad, Empresa empresa) throws SQLException {
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
                stmCreacion.setString(2, empresa.getIdUsuario());
                stmCreacion.setInt(3, 0);
                stmCreacion.executeUpdate();
            }
            // Sumar en la cartera del comprador
            stmUpdate = con.prepareStatement(sumarComprador);
            stmUpdate.setInt(1, participacionesPreviasComprador + cantidad);
            stmUpdate.setString(2, destino.getIdUsuario());
            stmUpdate.setString(3, empresa.getIdUsuario());
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
}
