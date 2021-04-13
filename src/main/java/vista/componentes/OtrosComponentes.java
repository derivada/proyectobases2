package vista.componentes;

import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class OtrosComponentes {

    /*
     * En esta clase "estática" se recogen configuraciones rápidas para otros componentes que no son subclases, debido
     * a que son de poco uso o a que se consideró demasiado tarde hacer un diseño completo
     */

    private OtrosComponentes(){}

    public static void configurarTabbedPane(JTabbedPane panel){
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
}
