package vista;

import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;
import aplicacion.Inversor; 

public class FachadaGui {
    
    FachadaAplicacion fa;
    VRegistro vr;
    VInversor va;
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
    
    public void iniciaInversores(aplicacion.FachadaAplicacion fa, String tipo, Inversor i){
        va = new VInversor(i,tipo,fa);
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
