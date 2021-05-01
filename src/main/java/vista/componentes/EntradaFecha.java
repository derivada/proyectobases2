package vista.componentes;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

public class EntradaFecha extends JTextField {

    private Predicate<Timestamp> validator = null;

    public EntradaFecha() {
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

    public void setValidator(Predicate<Timestamp> validator) {
        this.validator = validator;
    }

    public boolean validateInput() {
        Timestamp f = this.getFecha();
        if (f == null || (validator != null && !validator.test(f))) {
            this.setBackground(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.ROJO));
            return false;
        } else {
            this.setBackground(ColoresGUI.blanco);
            return true;
        }
    }

    public Timestamp getFecha() throws DateTimeParseException {
        /*
         * ISO_DATE admite fechas como 2001-20-12 (YYYY-MM-DD).
         */
        // devuelve la "sql-date"
        // Cambio / por - solo por si alguien los pone, para dar algo más de flexibilidad
        try {
            return Timestamp.valueOf(LocalDate.parse(this.getText().replace('/', '-'), DateTimeFormatter.ISO_DATE)
                    .atTime(LocalTime.MIDNIGHT));
        } catch (Exception e) {
            return null;
        }
    }
}
