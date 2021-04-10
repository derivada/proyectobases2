package aplicacion;

import vista.FachadaGui;

/**
 * Fachada principal de la aplicación
 * 
 */
public class FachadaAplicacion {

    FachadaGui fgui;
    baseDatos.fachadaBaseDatos fbd;
    GestionUsuarios cu;

    public FachadaAplicacion() {
        fgui = new FachadaGui(this);
        fbd= new baseDatos.fachadaBaseDatos(this);
        cu= new GestionUsuarios(fgui, fbd);
        
    }
    
    
    public void inicializarGUI() {
        fgui.iniciaVista(this);
    }
    
    public void muestraExcepcion(String e){
     fgui.muestraExcepcion(e);
    }
    
    public String validarUsuario(String nombre, String clave){
        return cu.validarUsuario(nombre, clave);
    }
    
    public void menuUsuarios(String tipo,String id_usuario,String clave){
        cu.iniciaUsuario(tipo,this,id_usuario,clave);
    }
    /*
    public void menuEmpresa(String tipo,Usuario user){
        cu.iniciaEmpresa(this, tipo,user);
    }
    
    public void menuRegulador(String tipo,Usuario user){
        cu.iniciaRegular(this, tipo,user);
    }
*/
}
