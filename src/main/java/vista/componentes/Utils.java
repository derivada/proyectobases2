package vista.componentes;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class Utils {

    /*
     * En esta clase "estática" se recogen otros métodos usados por la GUI que no fueron recogidos en ninguna
     * otra clase
     */

    private Utils() {
    }

    public static void configurarTabbedPane(JTabbedPane panel) {
        panel.setForeground(ColoresGUI.texto);
        panel.setBackground(ColoresGUI.fondo);
        panel.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.GRANDE));
        panel.setBorder(null);
        panel.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                highlight = ColoresGUI.getGUIColor(ColoresGUI.Colores.AZUL);
                lightHighlight = ColoresGUI.getGUIColorOscuro(ColoresGUI.Colores.AZUL);
                shadow = ColoresGUI.getGUIColorExtraOscuro(ColoresGUI.Colores.AZUL);
                darkShadow = ColoresGUI.getGUIColorExtraOscuro(ColoresGUI.Colores.AZUL);
            }
        });
    }

    public static String displayCurrency(float cantidad) {
        return NumberFormat.getCurrencyInstance(Locale.GERMAN).format(cantidad).replace('¤', '$');
    }
}
