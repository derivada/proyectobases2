package vista.componentes;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static vista.componentes.FuentesGUI.*;

public class Boton extends JButton implements MouseListener {

    public Boton() {
        // Crea un botón predeterminado
        super();
        this.setFont(FuentesGUI.getFuente(Modificador.NEGRITA, Size.GRANDE));
        this.setForeground(ColoresGUI.blanco);
        this.setBackground(ColoresGUI.getGUIColorPastel(ColoresGUI.Colores.AZUL));
        this.setBorder(BordesGUI.BordeBoton);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(getBackground().equals(ColoresGUI.getGUIColorPastel(ColoresGUI.Colores.AZUL)))
            setBackground(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.AZUL));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(getBackground().equals(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.AZUL)))
            setBackground(ColoresGUI.getGUIColorPastel(ColoresGUI.Colores.AZUL));
    }

    public void addActionListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
