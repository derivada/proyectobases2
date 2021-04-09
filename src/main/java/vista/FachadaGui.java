package vista;

import aplicacion.FachadaAplicacion;

public class FachadaGui {
    
    FachadaAplicacion fa;
    VRegistro vr;
    
    public FachadaGui(FachadaAplicacion fa) {
        this.fa=fa;
        
        
    }
    
    public void iniciaVista(){
        vr = new VRegistro();
        vr.setVisible(true);
    }

}
