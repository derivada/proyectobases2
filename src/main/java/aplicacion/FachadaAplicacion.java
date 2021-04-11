package aplicacion;

import vista.FachadaGui;

import java.util.Calendar;

/**
 * Fachada principal de la aplicación
 */
public class FachadaAplicacion {

    FachadaGui fgui;
    baseDatos.fachadaBaseDatos fbd;
    GestionUsuarios cu;
    private final long fechaInicio;

    public FachadaAplicacion() {
        fechaInicio = Calendar.getInstance().getTimeInMillis();
        fgui = new FachadaGui(this);
        fbd = new baseDatos.fachadaBaseDatos(this);
        cu = new GestionUsuarios(fgui, fbd);
    }


    public void inicializarGUI() {
        fgui.iniciaVista(this);
    }

    public void muestraExcepcion(String e) {
        fgui.muestraExcepcion(e);
    }

    public String validarUsuario(String nombre, String clave) {
        return cu.validarUsuario(nombre, clave);
    }

    public void menuInversor(String tipo, String id_usuario) {
        cu.iniciaInversor(tipo, this, id_usuario);
    }

    public void menuEmpresa(String tipo, String id_usuario, String clave) {
        cu.iniciaEmpresa(tipo, this, id_usuario, clave);
    }

    public void menuRegulador(String tipo, String id_usuario, String clave) {
        cu.iniciaRegulador(tipo, this, id_usuario, clave);
    }

    public void salir() {
        System.out.println("Tiempo transcurrido: " + (Calendar.getInstance().getTimeInMillis() - fechaInicio) + " ms");
        System.out.println("Fin del programa...");
        System.exit(0);
    }
    
    public boolean registroUsuario(Usuario u){
        return cu.registroUsuario(u);
    }
    
    public boolean registroInversor(Inversor i){
        return cu.registroInversor(i);
    }
    
    public boolean registroEmpresa(Empresa e){
        return cu.registroEmpresa(e);
    }
}
