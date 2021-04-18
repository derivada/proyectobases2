package vista.componentes;

import aplicacion.FachadaAplicacion;

import javax.swing.*;
import java.awt.event.*;

/*
 * Encapsula la lógica y diseño de un botón para volver atrás o salir genérico
 */

public class BotonVolver extends Boton implements MouseListener {

    // La ventana actual
    private JFrame ventanaActual;

    // Determina si se saldrá de la aplicación al clickar o solo se volverá al padre
    private boolean salida;

    private FachadaAplicacion fa;

    private static final ImageIcon ICONO_SALIR;
    private static final ImageIcon ICONO_VOLVER;

    static {
        ICONO_SALIR = ImagenesGUI.getIcon("exit.png", "Salir", 96);
        ICONO_VOLVER = ImagenesGUI.getIcon("back.png", "Volver", 96);
    }

    public BotonVolver() {
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA, FuentesGUI.Size.GRANDE));
        this.setForeground(ColoresGUI.textoClaro);
        this.setBackground(ColoresGUI.destacado);
        this.addMouseListener(this);
        this.setBorder(null);
        this.salida = false;
        this.setIcon(ICONO_VOLVER); // por defecto el de volver
        this.setVerticalAlignment(CENTER);
        this.setVerticalTextPosition(BOTTOM);
        this.setHorizontalTextPosition(CENTER);
        this.addMouseListener(this);
        this.setBorder(BordesGUI.BordeBotonVolver);
        this.salida = true;
    }

    public void configurar(FachadaAplicacion fa, JFrame ventanaActual, boolean salida) {
        this.fa = fa;
        this.ventanaActual = ventanaActual;
        this.salida = salida;
        if (this.salida) {
            this.setIcon(ICONO_SALIR);
        }
    }

    public void esSalir(boolean salir) {
        if (salir)
            this.setText("Salir");
        else
            this.setText("Volver");
    }

    private void volver() {
        if (ventanaActual == null && !salida) {
            System.out.println("El click al botón de salida no tuvo ningun efecto. No se han" +
                    "especificado la ventana actual");
            return;
        }
        if (salida) {
            fa.salir();
        } else {
            ventanaActual.dispose();
            fa.inicializarGUI();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        volver();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (getBackground().equals(ColoresGUI.destacado))
            setBackground(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.AZUL));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (getBackground().equals(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.AZUL)))
            setBackground(ColoresGUI.destacado);
    }
}
