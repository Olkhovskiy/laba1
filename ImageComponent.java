import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Васили on 23.10.2017.
 */
public class ImageComponent extends JComponent {
    private Image image;
    private BufferedImage buffer;

    public ImageComponent(File file, BufferedImage buffer) {
        try {
            this.buffer = buffer;
           image = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rebuidBuffer(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (buffer == null) {
            rebuidBuffer(g);
        }
        g.drawImage(buffer, 0, 0, null);
    }
}