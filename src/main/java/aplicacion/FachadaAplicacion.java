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

    public Usuario validarUsuario(String nombre, String clave) {
        return cu.validarUsuario(nombre, clave);
    }

    public void menuInversor(Inversor inversor) {
        cu.iniciaInversor(inversor, this);
    }

    public void menuEmpresa(Empresa empresa) {
        cu.iniciaEmpresa(empresa, this);
    }

    public void menuRegulador(Regulador regulador) {
        cu.iniciaRegulador(regulador, this);
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
    
    public int getPartPropEmpresa(Empresa e){
        return cu.getPartPropEmpresa(e);
    }
}
