package aplicacion;

import vista.FachadaGui;

/**
 * Fachada principal de la aplicación
 * 
 */
public class FachadaAplicacion {

    FachadaGui fgui;
    baseDatos.fachadaBaseDatos fbd;

    public FachadaAplicacion() {
        fgui = new FachadaGui(this);
        fbd= new baseDatos.fachadaBaseDatos(this);
 
    }
    
    
    public void inicializarGUI() {
        fgui.iniciaVista();
    }
    
    public void muestraExcepcion(String e){
     fgui.muestraExcepcion(e);
 }
    

}
