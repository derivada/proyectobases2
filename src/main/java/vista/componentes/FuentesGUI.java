package vista.componentes;

import java.awt.*;

public class FuentesGUI {

    private static String _fuente = "Segoe UI";

    public static String getFamiliaFuentes() {
        return _fuente;
    }

    public static void setFamiliaFuentes(String fuente) {
        GraphicsEnvironment g = null;
        g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // Comprueba que la fuente está instalada
        for (String fuentes : g.getAvailableFontFamilyNames()) {
            if (fuente.equals(fuentes)) {
                _fuente = fuente;
                break;
            }
        }
    }

    // Tamaños predefinidos, se pueden añadir nuevos fácilmente pero el objetivo
    // es tener pocos para tener una interfaz más consistente
    public enum Size {
        TITULO(64), ENORME(32), GRANDE(20), NORMAL(16), PEQUENA(14), ENANA(12);
        private final int size;

        Size(int size) {
            this.size = size;
        }
    }

    public enum Modificador {
        NORMAL(Font.PLAIN), NEGRITA(Font.BOLD), CURSIVA(Font.ITALIC);
        private final int mod;

        Modificador(int mod) {
            this.mod = mod;
        }
    }

    // Obtiene una fuente a partir de las enumeraciones para mantener un estilo consistente
    // Uso: Font fuente = FuentesGUI.getFuente(Modificador.NEGRITA, Size.ENORME);
    public static Font getFuente(Modificador modificador, Size size) {
        return new Font(_fuente, modificador.mod, size.size);
    }

}
