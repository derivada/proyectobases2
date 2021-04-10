package vista.componentes;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class BordesGUI {

    // "No hagáis clases contenedores de datos" - Pet

    static {
        // Los empty border son para el padding

        BordeBoton = BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createBevelBorder(BevelBorder.RAISED, ColoresGUI.destacadoFuerte, ColoresGUI.destacadoExtra),
                        BorderFactory.createLineBorder(ColoresGUI.getGUIColorOscuro(ColoresGUI.Colores.AZUL), 1, true)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        BordeBotonVolver =  BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ColoresGUI.getGUIColorOscuro(ColoresGUI.Colores.ROJO), 2, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        BordeTextBox = BorderFactory.createLineBorder(ColoresGUI.getGUIColorOscuro(ColoresGUI.Colores.AZUL), 1, true);
        BordePasswordField = BorderFactory.createLineBorder(ColoresGUI.getGUIColorOscuro(ColoresGUI.Colores.VIOLETA), 1, true);

    }

    public static final Border BordeBoton;
    public static final Border BordeBotonVolver;
    public static final Border BordeTextBox;
    public static final Border BordePasswordField;


}
