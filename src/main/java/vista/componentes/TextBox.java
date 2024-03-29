package vista.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.function.Predicate;

public class TextBox extends JTextField {

    private Predicate<String> validator = null;
    private Color validationFailedColor = ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.ROJO);

    public TextBox() {
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
        this.setBackground(ColoresGUI.blanco);
        this.setBorder(BordesGUI.BordeTextBox);
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

    public void setValidationFailedColor(Color validationFailedColor) {
        this.validationFailedColor = validationFailedColor;
    }

    public boolean validateInput() {
        if (validator == null || validator.test(this.getText())) {
            this.setBackground(ColoresGUI.blanco);
            return true;
        } else {
            this.setBackground(validationFailedColor);
            return false;
        }
    }

    public static boolean validateSet(List<TextBox> campos) {
        boolean failed = false;
        for (TextBox t : campos) {
            if (!t.validateInput())
                failed = true;
        }
        return !failed;
    }

}