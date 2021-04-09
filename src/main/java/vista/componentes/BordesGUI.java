package vista.componentes;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class BordesGUI {

    static {
        // Los empty border son para el padding

        BordeBoton = BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createBevelBorder(BevelBorder.RAISED, ColoresGUI.destacadoFuerte,
                                ColoresGUI.destacadoExtra),
                        BorderFactory.createLineBorder(ColoresGUI.getGUIColorOscuro(ColoresGUI.Colores.AZUL), 1, true)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    public static Border BordeBoton;


}
