package vista.componentes;

import java.awt.*;

/**
 * Clase que contiene colores comunes usados por la aplicación
 */

public class ColoresGUI {

    // Escala de grises
    public static final Color blanco = new Color(255, 255, 255);
    public static final Color fondo = new Color(240, 240, 240);
    public static final Color destacado = new Color(220, 220, 220);
    public static final Color destacadoFuerte = new Color(190, 190, 190);
    public static final Color destacadoExtra = new Color(160, 160, 160);
    public static final Color textoClaro = new Color(80, 80, 80);
    public static final Color texto = new Color(40, 40, 40);
    public static final Color textoOscuro = new Color(20, 20, 20);
    public static final Color negro = new Color(0, 0, 0);

    public static Color getColorEscalaGrises(int valor) {
        // Usar los de arriba preferentemente para dar consistencia al diseño
        return new Color(valor, valor, valor);
    }

    public enum Colores {
        // Lista de colores prefijada. Si se añade aquí añadir también sus variantes en los métodos
        ROJO, VERDE, AMARILLO, AZUL, VIOLETA, CYAN
    }


    // Los siguientes métodos proporcionan variantes de colores: OSCURO, NORMAL, PASTEL, CLARO, EXTRACLARO
    public static Color getGUIColorExtraOscuro(Colores color) {
        switch (color) {
            case ROJO:
                return new Color(87, 0, 0);
            case VERDE:
                return new Color(6, 95, 0);
            case AMARILLO:
                return new Color(118, 112, 0);
            case AZUL:
                return new Color(0, 120, 141);
            case VIOLETA:
                return new Color(47, 0, 94);
            case CYAN:
                return new Color(0, 71, 31);
            default:
                return new Color(0, 0, 0);
        }
    }
    public static Color getGUIColorOscuro(Colores color) {
        switch (color) {
            case ROJO:
                return new Color(151, 0, 0);
            case VERDE:
                return new Color(10, 142, 0);
            case AMARILLO:
                return new Color(158, 150, 0);
            case AZUL:
                return new Color(0, 140, 163);
            case VIOLETA:
                return new Color(68, 0, 142);
            case CYAN:
                return new Color(0, 116, 56);
            default:
                return new Color(0, 0, 0);
        }
    }

    public static Color getGUIColor(Colores color) {
        switch (color) {
            case ROJO:
                return new Color(255, 0, 0);
            case VERDE:
                return new Color(24, 255, 0);
            case AMARILLO:
                return new Color(255, 244, 0);
            case AZUL:
                return new Color(0, 216, 255);
            case VIOLETA:
                return new Color(119, 0, 255);
            case CYAN:
                return new Color(0, 255, 122);
            default:
                return new Color(255, 255, 255);
        }
    }

    public static Color getGUIColorClaro(Colores color) {
        switch (color) {
            case ROJO:
                return new Color(255, 105, 105);
            case VERDE:
                return new Color(113, 248, 98);
            case AMARILLO:
                return new Color(255, 248, 108);
            case AZUL:
                return new Color(84, 230, 255);
            case VIOLETA:
                return new Color(165, 88, 255);
            case CYAN:
                return new Color(87, 255, 188);
            default:
                return new Color(255, 255, 255);
        }
    }

    public static Color getGUIColorPastel(Colores color) {
        switch (color) {
            case ROJO:
                return new Color(213, 110, 110);
            case VERDE:
                return new Color(149, 219, 106);
            case AMARILLO:
                return new Color(219, 217, 106);
            case AZUL:
                return new Color(106, 202, 219);
            case VIOLETA:
                return new Color(176, 106, 219);
            case CYAN:
                return new Color(106, 219, 170);
            default:
                return new Color(255, 255, 255);
        }
    }

    public static Color getGUIColorExtraClaro(Colores color) {
        switch (color) {
            case ROJO:
                return new Color(255, 196, 196);
            case VERDE:
                return new Color(212, 255, 186);
            case AMARILLO:
                return new Color(255, 254, 172);
            case AZUL:
                return new Color(175, 243, 255);
            case VIOLETA:
                return new Color(223, 173, 255);
            case CYAN:
                return new Color(158, 255, 212);
            default:
                return new Color(255, 255, 255);
        }
    }
}
