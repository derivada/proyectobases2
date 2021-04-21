/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.componentes;

import javax.swing.JTextArea;

/**
 *
 * @author Pablo
 */
public class AreaTexto extends JTextArea {

    public AreaTexto() {
        this.setWrapStyleWord(true);
        this.setLineWrap(true);
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.GRANDE));
        this.setForeground(ColoresGUI.texto);
        this.setBackground(ColoresGUI.fondo);
    }
}
