package vista;

import aplicacion.*;

public class FachadaGui {

    FachadaAplicacion fa;
    VRegistro vr;
    VInversor va;
    VEmpresa ve;
    VRegulador vreg;


    public FachadaGui(FachadaAplicacion fa) {
        this.fa = fa;

    }

    public void iniciaVista(aplicacion.FachadaAplicacion fa) {
        vr = new VRegistro(fa);
        vr.setVisible(true);
    }

    public void muestraExcepcion(String txtExcepcion) {
       /*VAviso va;
       
       va = new VAviso(vp, true, txtExcepcion);
       va.setVisible(true);*/
        System.out.println(txtExcepcion);
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
}
