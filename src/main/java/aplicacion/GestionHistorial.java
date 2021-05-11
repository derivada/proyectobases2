package aplicacion;

import baseDatos.FachadaBaseDatos;
import vista.FachadaGUI;

import java.util.List;

public class GestionHistorial {

    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionHistorial(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public List<EntradaHistorial> obtenerHistorial() {
        return fbd.obtenerHistorial();
    }

    public List<EntradaHistorial> obtenerHistorial(Usuario u) {
        return fbd.obtenerHistorial(u);
    }

    public void insertarHistorial(EntradaHistorial h) {
        fbd.insertarHistorial(h);
    }

}
