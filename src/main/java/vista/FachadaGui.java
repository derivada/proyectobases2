package vista;

import aplicacion.FachadaAplicacion;

public class FachadaGui {
    
    FachadaAplicacion fa;
    VRegistro vr;
    
    public FachadaGui(FachadaAplicacion fa) {
        this.fa=fa;
       
    }
    
    public void iniciaVista(aplicacion.FachadaAplicacion fa){
        vr = new VRegistro(fa);
        vr.setVisible(true);
    }
    
    public void muestraExcepcion(String txtExcepcion){
       /*VAviso va;
       
       va = new VAviso(vp, true, txtExcepcion);
       va.setVisible(true);*/
       System.out.println(txtExcepcion);
    }
}
