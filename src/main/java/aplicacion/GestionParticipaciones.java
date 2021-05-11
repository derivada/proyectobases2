package aplicacion;

import baseDatos.FachadaBaseDatos;
import vista.FachadaGUI;
import vista.componentes.DialogoInfo;

import java.sql.Timestamp;
import java.util.List;

public class GestionParticipaciones {

    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionParticipaciones(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
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

    public List<OfertaVenta> getOfertasVenta(String empresa, float precio) {
        return fbd.getOfertasVenta(empresa, precio);
    }

    public List<OfertaVenta> getOfertasVentaPropias(String usuario) {
        return fbd.getOfertasVentaPropias(usuario);
    }

    public int getParticipacionesEmpresa(Usuario u, Empresa e) {
        return fbd.getParticipacionesEmpresa(u, e);
    }

    public int getParticipacionesVendibles(Usuario u, Empresa e) {
        return fbd.getParticipacionesVendibles(u, e);
    }

    public int getParticipacionesTotales(Usuario u) {
        return fbd.getParticipacionesTotales(u);
    }

    public void crearOfertaVenta(Usuario u, Empresa empresa, int numero, float precioVenta) {
        fbd.crearOfertaVenta(u, empresa, numero, precioVenta);
    }

    public void bajaOfertaVenta(Usuario usuario, Timestamp fecha) {
        fbd.bajaOfertaVenta(usuario, fecha);
    }

    public void comprarParticipaciones(Usuario comprador, Empresa empresa, int cantidad, float precioMax) {
        fbd.comprarParticipaciones(comprador, empresa, cantidad, precioMax);
    }

    public void crearAnuncio(Float importe, Empresa e, Timestamp fecha, Integer numeroParticipaciones) {
        fbd.crearAnuncio(importe, e, fecha, numeroParticipaciones);
    }

    public void pagarBeneficios(Float importe, Integer participaciones, Empresa empresa, AnuncioBeneficios a) {
        fbd.pagarBeneficios(importe, participaciones, empresa, a);
    }

    public List<AnuncioBeneficios> obtenerAnuncios(String empresa) {
        return fbd.obtenerAnuncios(empresa);
    }

    public void solicitarBajaAnuncio(String empresa, Timestamp fechaPago) {
        boolean realizado = fbd.solicitarBajaAnuncio(empresa, fechaPago);
        if (!realizado) {
            FachadaGUI.muestraExcepcion("Error al solicitar la baja del anuncio", DialogoInfo.NivelDeAdvertencia.ERROR);
        } else {
            FachadaGUI.muestraExcepcion("Baja solicitada correctamente", DialogoInfo.NivelDeAdvertencia.INFORMACION);
        }
    }

    public List<AnuncioBeneficios> obtenerAnunciosRegulador() {
        return fbd.obtenerAnunciosRegulador();
    }

    public void bajaAnuncio(String empresa, Timestamp fecha, Float importe, Integer numparticipaciones) {
        fbd.bajaAnuncio(empresa, fecha, importe, numparticipaciones);
    }

    public int getNumeroParticipaciones(String idUsuario, String tipo) {
        int resultado = 0;
        if (tipo.equals("Inversor")) {
            resultado = fbd.getNumeroParticipacionesInversor(idUsuario);
        } else if (tipo.equals("Empresa")) {
            resultado = fbd.getNumeroParticipacionesEmpresa(idUsuario);
        }
        return resultado;
    }

    public List<EntradaParticipacion> obtenerDatosParticipaciones(Usuario u){
        return fbd.obtenerDatosParticipaciones(u);
    }
}
