package aplicacion;

import vista.FachadaGUI;
import baseDatos.FachadaBaseDatos;

import java.sql.Timestamp;
import vista.componentes.DialogoInfo;

import java.util.ArrayList;

import java.util.List;

public class GestionUsuarios {

    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionUsuarios(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public Usuario validarUsuario(String nombre, String clave) {

        List<String> listaNombresUsuarios = fbd.obtenerListaNombresUsuarios();

        if (!listaNombresUsuarios.contains(nombre.replaceAll("\\s+$", ""))) {
            FachadaGUI.muestraExcepcion("Error al autentificar usuario", "El usuario no está registrado!",
                    DialogoInfo.NivelDeAdvertencia.ERROR);
            return null;
        }
        // El usuario está registrado, intentar validarlo
        return fbd.validarUsuario(nombre, clave);
    }

    public java.util.List<Usuario> obtenerListaUsuarios() {
        return fbd.obtenerListaUsuarios();
    }

    public java.util.List<Usuario> obtenerListaEmpresas() {
        return fbd.obtenerListaEmpresas();
    }

    public java.util.List<Usuario> obtenerListaInversores() {
        return fbd.obtenerListaInversores();
    }

    public java.util.List<Usuario> obtenerListaReguladores() {
        return fbd.obtenerListaReguladores();
    }

    public Empresa obtenerDatosEmpresa(Usuario user) {
        return fbd.obtenerDatosEmpresa(user);
    }

    public Inversor obtenerDatosInversor(Usuario user) {
        return fbd.obtenerDatosInversor(user);
    }

    public Regulador obtenerDatosRegulador(Usuario user) {
        return fbd.obtenerDatosRegulador(user);
    }

    public void iniciaInversor(Inversor i, FachadaAplicacion fa) {
        fgui.iniciaInversores(i, fa);
    }

    public void iniciaEmpresa(Empresa e, FachadaAplicacion fa) {
        fgui.iniciaEmpresa(e, fa);
    }

    public void iniciaRegulador(Regulador r, FachadaAplicacion fa) {
        fgui.iniciaRegulador(r, fa);
    }

    public void iniciaModificarInversor(Inversor i, FachadaAplicacion fa) {
        fgui.iniciaModificarInversor(i, fa);
    }

    public void iniciaModificarEmpresa(Empresa e, FachadaAplicacion fa) {
        fgui.iniciaModificarEmpresa(e, fa);
    }

    public boolean registroInversor(Inversor i) {
        return fbd.registroInversor(i);
    }

    public boolean registroEmpresa(Empresa e) {
        return fbd.registroEmpresa(e);
    }


    public ArrayList<Usuario> obtenerUsuarioPorAutorizacion() {
        ArrayList<Usuario> resultado = new ArrayList<>();

        resultado.addAll(fbd.obtenerInversorPorAutorizacion());
        resultado.addAll(fbd.obtenerEmpresaPorAutorizacion());

        return resultado;
    }

    public void autorizarUsuario(String id_usuario) {
        fbd.autorizarUsuario(id_usuario);
    }

    public ArrayList<Usuario> obtenerUsuarioBaja() {
        ArrayList<Usuario> resultado = new ArrayList<>();

        resultado.addAll(fbd.obtenerInversorBaja());
        resultado.addAll(fbd.obtenerEmpresaBaja());

        return resultado;
    }

    public void modificarUsuario(String id_usuario, Usuario u) {
        if (u instanceof Inversor) {
            fbd.modificarInversor(id_usuario, (Inversor) u);
        } else {
            fbd.modificarEmpresa(id_usuario, (Empresa) u);
        }
    }

    public void bajaUsuario(Usuario u, float saldo) {
        if (u instanceof Inversor) {
            fbd.eliminarInversor(u.getIdUsuario(), saldo);

        } else {
            fbd.eliminarEmpresa(u.getIdUsuario(), saldo);
        }
    }

    public void solicitarBaja(String idUsuario) {
        fbd.solicitarBaja(idUsuario);
    }


    public java.util.List<AnuncioBeneficios> obtenerAnunciosRegulador() {
        return fbd.obtenerAnunciosRegulador();
    }

    public void bajaAnuncio(String empresa, Timestamp fecha, Float importe, Integer numparticipaciones) {
        fbd.bajaAnuncio(empresa, fecha, importe, numparticipaciones);
    }

    public boolean comprobarID(String id) {
        return fbd.comprobarID(id);
    }

    public boolean modificarInversor(Inversor i, String pass, String idviejo) {
        return fbd.modificarInversor(i, pass, idviejo);
    }

    public boolean modificarEmpresa(Empresa e, String pass, String idviejo) {
        return fbd.modificarEmpresa(e, pass, idviejo);
    }

    public float obtenerComision(String r) {
        return fbd.obtenerComision(r);
    }

    public void modificarComision(Regulador r, float comision) {
        fbd.modificarComision(r, comision);
    }

    public void modificarSaldo(String id, float saldo, String tipo) {
        if (tipo.equals("Inversor")) {
            fbd.modificarSaldoInversor(id, saldo);
        } else if (tipo.equals("Empresa")) {
            fbd.modificarSaldoEmpresa(id, saldo);
        }
    }

    public Float obtenerSaldo(Usuario u, String tipo) {
        float resultado = (float) 0.0;
        if (tipo.equals("Inversor")) {
            resultado = fbd.obtenerSaldoInversor(u);
        } else if (tipo.equals("Empresa")) {
            resultado = fbd.obtenerSaldoEmpresa(u);
        }

        return resultado;
    }

    public void bloquearSaldo(Empresa e, int cantidad) {
        fbd.bloquearSaldo(e, cantidad);
    }
    public void liberarSaldo(Empresa e, int cantidad){
        fbd.liberarSaldo(e, cantidad);
    }
}
