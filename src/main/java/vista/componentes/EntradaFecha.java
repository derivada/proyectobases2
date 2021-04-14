/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.componentes;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date; 

/**
 *
 * @author Usuario
 */
public class EntradaFecha extends JTextField implements MouseListener{
     public EntradaFecha(){
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
 

    public Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
            
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
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
