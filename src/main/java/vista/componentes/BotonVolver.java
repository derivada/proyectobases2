package vista.componentes;

import aplicacion.FachadaAplicacion;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * Encapsula la lógica y diseño de un botón para volver atrás o salir genérico
 */

public class BotonVolver extends Boton implements MouseListener {
    // La ventana a la que se vuelve al clickar
    private JFrame ventanaPadre;
    // La ventana actual
    private JFrame ventanaActual;

    // Determina si se saldrá de la aplicación al clickar o solo se volverá al padre
    private boolean salida;

    private FachadaAplicacion fa;

    public BotonVolver() {
        super();
        this.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA, FuentesGUI.Size.GRANDE));
        this.setForeground(ColoresGUI.textoClaro);
        this.setBackground(ColoresGUI.destacado);
        this.addMouseListener(this);
        this.setBorder(null);
        this.setIcon(ImagenesGUI.getIcon("back.png", "Volver", 96));
        this.setVerticalAlignment(CENTER);
        this.setVerticalTextPosition(BOTTOM);
        this.setHorizontalTextPosition(CENTER);
        this.addMouseListener(this);
    }

    public void configurar(FachadaAplicacion fa, JFrame ventanaActual, JFrame ventanaPadre, boolean salida) {
        this.fa = fa;
        this.ventanaActual = ventanaActual;
        this.ventanaPadre = ventanaPadre;
        this.salida = salida;
    }

    public void esSalir(boolean salir) {
        if (salir)
            this.setText("Salir");
        else
            this.setText("Volver");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (ventanaActual == null) {
            System.out.println("El click al botón de salida no tuvo ningun efecto. No se han" +
                    "especificado la ventana actual");
            return;
        }
        if (ventanaPadre == null && !salida) {
            System.out.println("El click al botón de salida no tuvo ningun efecto. No se ha" +
                    "especificado la ventana a la cuál volver");
            return;
        }
        if (salida) {
            fa.salir();
        } else {
            ventanaActual.dispose();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (getBackground().equals(ColoresGUI.destacado))
            setBackground(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.ROJO));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (getBackground().equals(ColoresGUI.getGUIColorExtraClaro(ColoresGUI.Colores.ROJO)))
            setBackground(ColoresGUI.destacado);
    }
}
