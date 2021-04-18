package vista;

import aplicacion.*;
import vista.componentes.DialogoInfo;

import javax.swing.*;

public class FachadaGui {

    FachadaAplicacion fa;
    VRegistro vr;
    VInversor va;
    VEmpresa ve;
    VRegulador vreg;
    VModificarEmpresa vme;
    VModificarUsuario vmi;

    public FachadaGui(FachadaAplicacion fa) {
        this.fa = fa;

    }

    public void iniciaVista(aplicacion.FachadaAplicacion fa) {
        if (vr == null)
            vr = new VRegistro(fa);
        vr.setVisible(true);
    }

    public void muestraExcepcion(JFrame padre, String titulo, String descripcion, DialogoInfo.NivelDeAdvertencia nivel, boolean bloqueaInput) {

        DialogoInfo dialogoExcepcion = new DialogoInfo(padre, titulo, descripcion, nivel, bloqueaInput);
    }

    public void iniciaInversores(Inversor i, FachadaAplicacion fa) {
        va = new VInversor(i, fa);
        va.setVisible(true);
    }

    public void iniciaEmpresa(Empresa e, FachadaAplicacion fa) {
        ve = new VEmpresa(e, fa);
        ve.setVisible(true);
    }

    public void iniciaRegulador(Regulador r, FachadaAplicacion fa) {
        vreg = new VRegulador(r, fa);
        vreg.setVisible(true);
    }
    
    public void iniciaModificarInversor(Inversor i, FachadaAplicacion fa){
        vmi=new VModificarUsuario(i, fa);
        vmi.setVisible(true);
    }


}
