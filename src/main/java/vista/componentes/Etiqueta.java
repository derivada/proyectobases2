package vista.componentes;

import javax.swing.*;

public class Etiqueta extends JLabel {

    public Etiqueta() {
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
    }

    public Etiqueta(String texto) {
        this();
        this.setText(texto);
    }

    public Etiqueta(ImageIcon img) {
        this();
        this.setIcon(img);
    }

    public Etiqueta(String texto, ImageIcon img) {
        this();
        this.setText(texto);
        this.setIcon(img);
    }
}
