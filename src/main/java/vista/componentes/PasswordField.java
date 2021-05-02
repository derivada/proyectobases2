package vista.componentes;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PasswordField extends JPasswordField {

    private StringBuilder validationErrorMessage = new StringBuilder();
    private boolean validated;
    private boolean validationLevel = true; // Si es true se comprobarán las reglas, si no solo la longitud mínima/máxima de SQL
    private boolean admiteVacio = false;

    public String getValidationError() {
        return this.validationErrorMessage.toString();
    }

    public boolean isValidated() {
        if (validationLevel)
            validatePassword();
        else
            weakValidatePassword();
        return this.validated;
    }

    public void setValidationLevel(boolean validationLevel) {
        this.validationLevel = validationLevel;
    }

    // Variables para la política de contraseñas
    public static final boolean NEEDS_UPPERCASE = true;
    public static final boolean NEEDS_NUMBERS = true;
    public static final boolean NEEDS_NON_ALPHANUMERIC = true;
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 64;

    public PasswordField() {
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
        this.setBackground(ColoresGUI.blanco);
        this.setBorder(BordesGUI.BordePasswordField);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (validationLevel)
                    validatePassword();
                else
                    weakValidatePassword();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (validationLevel)
                    validatePassword();
                else
                    weakValidatePassword();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (validationLevel)
                    validatePassword();
                else
                    weakValidatePassword();
            }
        });

    }

    private void weakValidatePassword() {
        validated = true;
        if (getText().isEmpty() && admiteVacio) {
            changeValidationColor();
            return;
        }
        if (getText().length() < 3 || this.getText().length() > 72) {
            validated = false;
        }
        changeValidationColor();
    }

    private void validatePassword() {
        validated = true;
        if (getText().isEmpty() && admiteVacio) {
            changeValidationColor();
            return;
        }
        validationErrorMessage = new StringBuilder();
        String input = this.getText();

        int length = input.length();
        Pattern p1 = Pattern.compile("[A-Z]");
        boolean hasUppercase = p1.matcher(input).find();

        Pattern p2 = Pattern.compile("[0-9]");
        boolean hasNumber = p2.matcher(input).find();

        Pattern p3 = Pattern.compile("[^a-zA-Z0-9]");
        boolean hasNonAlphanumeric = p3.matcher(input).find();

        if (NEEDS_UPPERCASE && !hasUppercase) {
            validated = false;
            validationErrorMessage.append("La contraseña debe tener al menos una letra mayúscula!\n");
        }
        if (NEEDS_NUMBERS && !hasNumber) {
            validated = false;
            validationErrorMessage.append("La contraseña debe tener al menos un número!\n");
        }
        if (NEEDS_NON_ALPHANUMERIC && !hasNonAlphanumeric) {
            validated = false;
            validationErrorMessage.append("La contraseña debe tener al menos un carácter no alfanumérico!\n");
        }
        if (MIN_LENGTH > length) {
            validated = false;
            validationErrorMessage.append("La contraseña debe tener al menos " + MIN_LENGTH + " dígitos!\n");
        }
        if (MAX_LENGTH < length) {
            validated = false;
            validationErrorMessage.append("La contraseña debe tener como máximo " + MAX_LENGTH + " dígitos!\n");
        }
        changeValidationColor();
    }

    private void changeValidationColor() {
        if (validated)
            this.setBackground(ColoresGUI.blanco);
        else
            this.setBackground(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.ROJO));
    }

    public void admiteVacio(boolean admiteVacio) {
        this.admiteVacio = admiteVacio;
    }
}
