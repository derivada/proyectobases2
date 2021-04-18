package vista.componentes;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class Tabla extends JTable {

    public Tabla(){
        super();
        
        // Customizar el header (primera fila)
        JTableHeader tableHeader = this.getTableHeader();
        tableHeader.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA, FuentesGUI.Size.NORMAL));
        tableHeader.setForeground(ColoresGUI.blanco);
        tableHeader.setBackground(ColoresGUI.getGUIColorPastel(ColoresGUI.Colores.AZUL));
        tableHeader.setBorder(BordesGUI.BordeTextBox);
        
        // Customizar el cuerpo de la tabla
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.NORMAL));
        this.setBackground(ColoresGUI.blanco);
        this.setForeground(ColoresGUI.texto);
        this.setBorder(BordesGUI.BordeTextBox);
        
        
    }

   
    
    
}
