/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.componentes;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Usuario
 */
public class EntradaFecha extends JTextField implements MouseListener {
    public EntradaFecha() {
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setForeground(ColoresGUI.texto);
        this.setBackground(ColoresGUI.blanco);
        this.setBorder(BordesGUI.BordeTextBox);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    public Date getFecha() throws DateTimeParseException {
        /*
         * ISO_DATE admite fechas como 2001-20-12 (YYYY-MM-DD).
         */
        // devuelve la "sql-date"
        // Cambio / por - solo por si alguien los pone, para dar algo más de flexibilidad
        return Date.valueOf(LocalDate.parse(this.getText().replace('/', '-'), DateTimeFormatter.ISO_DATE));
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
