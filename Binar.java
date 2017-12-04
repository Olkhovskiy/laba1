import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Васили on 04.12.2017.
 */
public class Binar extends JFrame {
    Binar(File file, BufferedImage in, int inten) {
        ImageComponent component = new ImageComponent(file, in);
        int width = in.getWidth();
        int height = in.getHeight();
        int gray;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                Color c = new Color(in.getRGB(i, j));
                gray = c.getRed();
                if (gray < inten) {
                    in.setRGB(i, j, new Color(0, 0, 0).getRGB());
                } else
                    in.setRGB(i, j, new Color(255, 255, 255).getRGB());
            }

        ImageComponent image1 = new ImageComponent(file, in);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.CENTER, image1);
        add(panel);
        setSize(600,400);
        setVisible(true);

    }
}
