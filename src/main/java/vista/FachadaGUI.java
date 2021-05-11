package vista;

import aplicacion.*;
import vista.componentes.DialogoInfo;

import javax.swing.*;

public class FachadaGUI {

    // Singleton
    private static FachadaGUI _instance;

    public static FachadaGUI getInstance() {
        if (_instance == null) {
            _instance = new FachadaGUI();
        }
        return _instance;
    }

    private FachadaAplicacion fa;
    private VRegistro vr;
    private VInversor va;
    private VEmpresa ve;
    private VRegulador vreg;
    private VModificarEmpresa vme;
    private VModificarInversor vmi;

    private JFrame ventanaActiva;

    public JFrame getVentanaActiva() {
        return this.ventanaActiva;
    }

    private FachadaGUI() {
        // Bucle infinito!
        // this.fa = FachadaAplicacion.getInstance();
    }

    public void iniciaVista(aplicacion.FachadaAplicacion fa) {
        this.fa = FachadaAplicacion.getInstance();
        if (vr == null) {
            vr = new VRegistro(fa);
        }
        vr.setVisible(true);
        ventanaActiva = vr;
    }

    public static void muestraExcepcion(JFrame padre, String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel, boolean bloqueaInput) {
        _muestraExcepcion(padre, titulo, descripcion, nivel, bloqueaInput);
    }

    public static void muestraExcepcion(JFrame padre, String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        _muestraExcepcion(padre, titulo, descripcion, nivel, false);
    }

    public static void muestraExcepcion(String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        _muestraExcepcion(null, titulo, descripcion, nivel, false);
    }

    public static void muestraExcepcion(String descripcion, DialogoInfo.NivelDeAdvertencia nivel) {
        _muestraExcepcion(null, "Mercado de valores", descripcion, nivel, false);
    }

    public static void muestraExcepcion(String descripcion) {
        _muestraExcepcion(null, "Mercado de valores", descripcion, DialogoInfo.NivelDeAdvertencia.ERROR, false);
    }

    private static void _muestraExcepcion(JFrame padre, String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel,
                                 boolean bloqueaInput) {
        new DialogoInfo(padre, titulo, descripcion, nivel, bloqueaInput);
    }

    public void iniciaInversores(Inversor i, FachadaAplicacion fa) {
        va  = new VInversor(i, fa);
        va.setVisible(true);
        ventanaActiva = va;
    }

    public void iniciaEmpresa(Empresa e, FachadaAplicacion fa) {
        ve = new VEmpresa(e, fa);
        ve.setVisible(true);
        ventanaActiva = ve;
    }

    public void iniciaRegulador(Regulador r, FachadaAplicacion fa) {
        vreg = new VRegulador(r, fa);
        vreg.setVisible(true);
        ventanaActiva = vreg;
    }

    public void iniciaModificarInversor(Inversor i, FachadaAplicacion fa) {
        vmi = new VModificarInversor(i, fa);
        vmi.setVisible(true);
        ventanaActiva = vmi;
    }

    public void iniciaModificarEmpresa(Empresa e, FachadaAplicacion fa) {
        vme = new VModificarEmpresa(e, fa);
        vme.setVisible(true);
        ventanaActiva = vme;
    }
}
