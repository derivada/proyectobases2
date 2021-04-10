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
        va = new VUsuarios(user,tipo,fa);
        va.setVisible(true);
    }
    
    public void iniciaEmpresa(aplicacion.FachadaAplicacion fa, String tipo, Usuario user){
        ve = new VEmpresa(user,tipo,fa);
        ve.setVisible(true);
    }
        
    public void iniciaRegulador(aplicacion.FachadaAplicacion fa, String tipo, Usuario user){
        vreg = new VRegulador(user,tipo,fa);
        vreg.setVisible(true);
    }
}
