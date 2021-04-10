package vista.componentes;

import javax.swing.JLabel;

public class Etiqueta extends JLabel{
    
    public Etiqueta(){
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
    }
}
