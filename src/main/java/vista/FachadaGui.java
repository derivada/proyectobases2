package vista;

import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;

public class FachadaGui {
    
    FachadaAplicacion fa;
    VRegistro vr;
    VUsuarios va;
    VEmpresa ve;
    VRegulador vreg;
    
    
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
    
    public void iniciaUsuarios(aplicacion.FachadaAplicacion fa, String tipo, Usuario user){
        va = new VUsuarios(fa, tipo, user);
        va.setVisible(true);
    }
    
    public void iniciaEmpresa(aplicacion.FachadaAplicacion fa){
        ve = new VEmpresa(fa);
        ve.setVisible(true);
    }
        
    public void iniciaRegular(aplicacion.FachadaAplicacion fa){
        vreg = new VRegulador(fa);
        vreg.setVisible(true);
    }
}
