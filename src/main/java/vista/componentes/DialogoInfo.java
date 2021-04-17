package vista.componentes;

import javax.swing.*;
import java.awt.*;

public class DialogoInfo extends JDialog {

    static {
        IMAGEN_ERROR = ImagenesGUI.getImage("error.png", 64);
        IMAGEN_ADVERTENCIA = ImagenesGUI.getImage("advertencia.png", 64);
        IMAGEN_INFORMACION = ImagenesGUI.getImage("info.png", 64);
        IMAGEN_ERROR_BASEDATOS = ImagenesGUI.getImage("database_error.png", 64);
    }

    private static boolean imagenesCargadasCorrectamente;
    private static final Image IMAGEN_ERROR;
    private static final Image IMAGEN_ADVERTENCIA;
    private static final Image IMAGEN_INFORMACION;
    private static final Image IMAGEN_ERROR_BASEDATOS;

    private NivelDeAdvertencia nivel;
    private String descripcion;

    // Crea un dialógo (popup)
    public DialogoInfo(JFrame owner, String titulo, String descripcion, NivelDeAdvertencia nivel, boolean bloqueaInput) {
        super(owner);
        this.setTitle(titulo);
        this.setTitle(titulo);
        this.setIconImage(ImagenesGUI.getImage("database.png", 128));
        this.setSize(480, 360);
        this.setLocationRelativeTo(null); // Este método centra el diálogo
        this.descripcion = descripcion;
        this.setModal(bloqueaInput);
        this.nivel = nivel;
        this.mostrar();
    }

    private void mostrar() {
        BorderLayout layout = new BorderLayout();
        layout.setVgap(32);
        setLayout(layout);
        JLabel descIcono = null;
        switch (nivel) {
            case INFORMACION:
                if (IMAGEN_INFORMACION != null) {
                    this.setIconImage(IMAGEN_INFORMACION);
                    descIcono = new Etiqueta("Información!");
                    descIcono.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA, FuentesGUI.Size.ENORME));
                    descIcono.setForeground(ColoresGUI.getGUIColor(ColoresGUI.Colores.AZUL));
                }
                break;
            case ADVERTENCIA:
                if (IMAGEN_ADVERTENCIA != null) {
                    this.setIconImage(IMAGEN_ADVERTENCIA);
                    descIcono = new Etiqueta("Advertencia!");
                    descIcono.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA, FuentesGUI.Size.ENORME));
                    descIcono.setForeground(ColoresGUI.getGUIColorOscuro(ColoresGUI.Colores.AMARILLO));
                }
                this.setModalityType(ModalityType.APPLICATION_MODAL);
                break;
            case ERROR_BASEDATOS:
                if (IMAGEN_ERROR_BASEDATOS != null) {
                    this.setIconImage(IMAGEN_ERROR_BASEDATOS);
                    descIcono = new Etiqueta("Error de base de datos!");
                    descIcono.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA, FuentesGUI.Size.GRANDE));
                    descIcono.setForeground(ColoresGUI.getGUIColor(ColoresGUI.Colores.ROJO));
                }
                this.setModalityType(ModalityType.APPLICATION_MODAL);
                break;
            default:
                if (IMAGEN_ERROR != null) {
                    this.setIconImage(IMAGEN_ERROR);
                    descIcono = new Etiqueta("Error!");
                    descIcono.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NEGRITA, FuentesGUI.Size.ENORME));
                    descIcono.setForeground(ColoresGUI.getGUIColor(ColoresGUI.Colores.ROJO));
                }
                this.setModalityType(ModalityType.APPLICATION_MODAL);
                break;
        }
        if (descIcono != null)
            add(descIcono, BorderLayout.PAGE_START);
        JTextArea texto = new JTextArea(descripcion);
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        texto.setFont(FuentesGUI.getFuente(FuentesGUI.Modificador.NORMAL, FuentesGUI.Size.GRANDE));
        texto.setForeground(ColoresGUI.texto);
        texto.setBackground(ColoresGUI.fondo);
        add(texto, BorderLayout.CENTER);

        Boton salir = new Boton();
        salir.setText("Ok");
        salir.addActionListener(e -> {
            this.dispose();
        });
        add(salir, BorderLayout.AFTER_LAST_LINE);

        this.setVisible(true);
    }

    public enum NivelDeAdvertencia {
        // Ejemplo: Usar ERROR para el catch de exceptions predecibles
        // Usar ADVERTENCIA para advertencias propiamente dichas o para errores en la introducción de
        // datos (por ejemplo, intentar loggear con la contraseña no introducida)
        // Usar INFORMACION para ventanas de información que no impliquen que ha pasado nada raro
        ERROR, ERROR_BASEDATOS, ADVERTENCIA, INFORMACION
    }
}
