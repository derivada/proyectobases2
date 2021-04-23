package vista.componentes;

import javax.swing.JTextArea;

public class AreaTexto extends JTextArea {

    public AreaTexto() {
        this.setWrapStyleWord(true);
        this.setLineWrap(true);
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.GRANDE));
        this.setForeground(ColoresGUI.texto);
        this.setBackground(ColoresGUI.fondo);
    }
}
