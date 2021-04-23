package vista.componentes;

import javax.swing.*;

public class Deslizador extends JSlider {
    public Deslizador(){
        // No usar, requerido por Netbeans
    }
    public Deslizador(int min, int max){
        super(min, max, 0);
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
    }
}
