package vista.componentes;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.List;

public class SelecionBox<E> extends JComboBox<E> implements MouseListener {

    public SelecionBox() {
        // Netbeans necesita un constructor vacío para añadir beans al designer,
        // no usar al implementarlo (ir a customize code tras añadirlo al designer y meter el de abajo)
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
        this.setBackground(ColoresGUI.blanco);
        this.setBorder(BordesGUI.BordeTextBox);
        this.addMouseListener(this);
    }
    public SelecionBox(E[] items) {
        super(items);
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
        this.setBackground(ColoresGUI.blanco);
        this.setBorder(BordesGUI.BordeTextBox);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
