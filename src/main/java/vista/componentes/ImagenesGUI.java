package vista.componentes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImagenesGUI {
    public static final String IMG_ROOT = "src\\main\\resources\\";

    public static ImageIcon getIcon(String path, String description, int size) {
        BufferedImage imagen = getImage(path, size);
        return new ImageIcon(getScaledImage(imagen, size, size), description);
    }

    public static BufferedImage getImage(String path, int size) {
        BufferedImage imagen;
        try {
            File archivoImagen = new File(IMG_ROOT + path);
            //System.out.println("CARGANDO IMAGEN DESDE: " + archivoImagen.getAbsolutePath());
            imagen = ImageIO.read(archivoImagen);
            //System.out.println("IMAGEN CARGADA!");
            return imagen;
        } catch (Exception e) {
            //System.err.println("NO SE PUDO CARGAR LA IMAGEN!");
            return null;
        }
    }

    private static Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }
}
