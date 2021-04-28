package aplicacion;

import vista.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.sql.Date;
import java.sql.Timestamp;
import vista.componentes.DialogoInfo;

import java.util.ArrayList;

import java.util.List;

public class GestionUsuarios {
    FachadaGui fgui;
    FachadaBaseDatos fbd;


    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public Usuario validarUsuario(String nombre, String clave) {

        List<String> listaNombresUsuarios = fbd.obtenerListaNombresUsuarios();

        if (!listaNombresUsuarios.contains(nombre.replaceAll("\\s+$", ""))) {
            fbd.getFachadaAplicacion().muestraExcepcion("Error al autentificar usuario", "El usuario no está registrado!",
                    DialogoInfo.NivelDeAdvertencia.ERROR);
            return null;
        }
        // El usuario está registrado, intentar validarlo
        return  fbd.validarUsuario(nombre, clave);
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

    public void iniciaModificarInversor(Inversor i, FachadaAplicacion fa){
        fgui.iniciaModificarInversor(i,fa);
    }

    public void iniciaModificarEmpresa(Empresa e, FachadaAplicacion fa){
        fgui.iniciaModificarEmpresa(e,fa);
    }

    public boolean registroInversor(Inversor i) {
        return fbd.registroInversor(i);
    }

    public boolean registroEmpresa(Empresa e) {
        return fbd.registroEmpresa(e);
    }

    public int getPartPropEmpresa(Empresa e) {
        return fbd.getPartPropEmpresa(e);
    }

    public void emitirParticipaciones(Empresa e, int emision) {
        fbd.emitirParticipaciones(e, emision);
    }

    public void bajaParticipaciones(Empresa e, int baja) {
        fbd.bajaParticipaciones(e, baja);
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
    
    public java.util.List<OfertaVenta> getOfertasVenta(String empresa, float precio){
        return fbd.getOfertasVenta(empresa, precio);
    }

    public int getParticipacionesEmpresa(Usuario u, Empresa e) {
        return fbd.getParticipacionesEmpresa(u, e);
    }
    
    public int getParticipacionesEmpresa2(Usuario u, Empresa e) {
        return fbd.getParticipacionesEmpresa2(u, e);
    }

    public int getParticipacionesTotales(Usuario u) {
        return fbd.getParticipacionesTotales(u);
    }

    public void crearOfertaVenta(Usuario u, Empresa empresa, int numero, float precioVenta) {
        fbd.crearOfertaVenta(u, empresa, numero, precioVenta);

    }

    public void comprarParticipaciones(Usuario comprador, Empresa empresa, int cantidad, float precioMax){
        fbd.comprarParticipaciones(comprador, empresa, cantidad, precioMax);
    }

    public void bajaUsuario(Usuario u) {
        if(u instanceof Inversor){
            fbd.eliminarInversor(u.getIdUsuario());

        } else{
            fbd.eliminarEmpresa(u.getIdUsuario());
        }
    }

    public void solicitarBaja(String idUsuario) {
        fbd.solicitarBaja(idUsuario);
    }

    public void crearAnuncio(Float importe, Empresa e,Date fecha,Integer numeroParticipaciones){
        int aux= fbd.crearAnuncio(importe, e, fecha,numeroParticipaciones);
        if(aux==1){
          fbd.getFachadaAplicacion().muestraExcepcion("Anuncio creado correctamente",
                    DialogoInfo.NivelDeAdvertencia.INFORMACION);
        }
        else if (aux==2){
            fbd.getFachadaAplicacion().muestraExcepcion("El importe que tiene la empresa no es suficiente",
                    DialogoInfo.NivelDeAdvertencia.ERROR);

        }
        else{
            fbd.getFachadaAplicacion().muestraExcepcion("El numero de participacione que tiene la empresa no es suficiente",
                    DialogoInfo.NivelDeAdvertencia.ERROR);
        }
    }

    public void pagarBeneficios(Float importe,Integer participaciones,Empresa empresa,AnuncioBeneficios a){
        fbd.pagarBeneficios(importe,participaciones, empresa,a);
    }

    public java.util.List<AnuncioBeneficios> obtenerAnuncios(String empresa){
        return fbd.obtenerAnuncios(empresa);
    }

    public void solicitarBajaAnuncio(String empresa,Timestamp fechaPago){
        boolean realizado=fbd.solicitarBajaAnuncio(empresa, fechaPago);
         if(realizado==false){
            fbd.getFachadaAplicacion().muestraExcepcion("Error al solicitar la baja del anuncio",DialogoInfo.NivelDeAdvertencia.ERROR);
        }
    }

     public java.util.List<AnuncioBeneficios> obtenerAnunciosRegulador(){
        return fbd.obtenerAnunciosRegulador();
    }

      public void bajaAnuncio(String empresa,Timestamp fecha,Float importe){
         fbd.bajaAnuncio(empresa, fecha, importe);
     }

    public java.util.List<EntradaHistorial> obtenerHistorial() {
        return fbd.obtenerHistorial();
    }
    public java.util.List<EntradaHistorial> obtenerHistorial(Usuario u) {
        return fbd.obtenerHistorial(u);
    }
    
    public void insertarHistorial(EntradaHistorial h){
        fbd.insertarHistorial(h);
   }

    public boolean comprobarID(String id){
        return fbd.comprobarID(id);
    }

    public boolean modificarInversor(Inversor i, String pass, String idviejo){
        return fbd.modificarInversor(i, pass, idviejo);
    }

    public boolean modificarEmpresa(Empresa e, String pass, String idviejo){
        return fbd.modificarEmpresa(e, pass, idviejo);
    }
    
        
    public float obtenerComision(String r) {
        return fbd.obtenerComision(r);
    }
    
    public void modificarComision(Regulador r, float comision){
        fbd.modificarComision(r, comision);
    }
    
}
