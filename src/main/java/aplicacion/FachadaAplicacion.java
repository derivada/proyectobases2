package aplicacion;

import vista.FachadaGui;

/**
 * Fachada principal de la aplicación
 * 
 */
public class FachadaAplicacion {

    FachadaGui fgui;

    public FachadaAplicacion() {
        fgui = new FachadaGui(this);
 
    }
    
    
    public void inicializarGUI() {
        fgui.iniciaVista();
    }
    

}
