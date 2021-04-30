package vista.componentes;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Predicate;

public class PasswordField extends JPasswordField {

    private Predicate<String> validator = null;

    public PasswordField(){
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
        this.setBackground(ColoresGUI.blanco);
        this.setBorder(BordesGUI.BordePasswordField);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateInput();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                validateInput();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                validateInput();
            }
        });

    }

    public void setValidator(Predicate<String> validator) {
        this.validator = validator;
    }

    public boolean validateInput() {
        if (validator == null || validator.test(this.getText())) {
            this.setBackground(ColoresGUI.blanco);
            return true;
        } else {
            this.setBackground(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.ROJO));
            return false;
        }
    }
}
