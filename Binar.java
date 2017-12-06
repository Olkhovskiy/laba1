import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Васили on 04.12.2017.
 */
public class Binar extends JFrame {
    static int x, y;
    static int centX, centY;
    static float[][] f;
    static BufferedImage in;

    Binar(File file, BufferedImage in, int inten) {
        setTitle("Бинаризация");
        // ImageComponent component = new ImageComponent(file, in);
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
        this.in = in;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        GridBagLayout gbl = new GridBagLayout();
        // panel.setLayout(gbl);
        // panel.add(image1, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
        //  GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
        panel.add(BorderLayout.CENTER, image1);
        JButton button = new JButton("Задать структурирующий элемент");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("2");
                Table2 table2 = new Table2(file);
                x = table2.x;
                y = table2.y;
                centX = table2.centX;
                centY = table2.centY;
                f = table2.f;
                System.out.println("7");
            }
        });
        JButton button1 = new JButton("Дилатация");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage out = dilatation();
                ImageComponent imageComponent = new ImageComponent(file, out);
                JFrame frame = new JFrame();
                frame.setSize(1200, 700);
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.add(BorderLayout.CENTER, imageComponent);
                frame.add(panel);
                frame.setVisible(true);

            }
        });
        JButton button2 = new JButton("Эрозия");
        JButton button3 = new JButton("Замыкание");
        JButton button4 = new JButton("Размыкание");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(button);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel.add(BorderLayout.SOUTH, panel2);

        add(panel);
        setSize(600, 400);
        setVisible(true);
    }

    public BufferedImage dilatation() {
        int height = in.getHeight(null);
        int width = in.getWidth(null);

        for (int imageY = 0; imageY < height; imageY++) {
            for (int imageX = 0; imageX < width; imageX++) {

                int pixel = (new Color(in.getRGB(imageX, imageY))).getRed();
                if (pixel != 0) continue;

//Нашли пиксель на изображении
                for (int filterY = 0; filterY < y; filterY++) {
                    for (int filterX = 0; filterX < x; filterX++) {
                        if (f[filterY][filterX] != 1) continue; // проходим этот пиксель структ. эл-та
                        if (imageX + filterX - centX >= width || imageY + filterY - centY >= height || imageX + filterX - centX < 0 || imageY + filterY - centY < 0)
                            continue; // за границей изображения
                        in.setRGB(imageX + filterX - centX, imageY + filterY - centY, (new Color(0, 0, 0)).getRGB());
                    }
                }
            }
        }
        return in;
    }


    public BufferedImage erozia() {


        int height = in.getHeight(null);
        int width = in.getWidth(null);
        //  BufferedImage newImage = getClone(image);

        for (int imageY = 0; imageY < height; imageY++) {
            for (int imageX = 0; imageX < width; imageX++) {

                int pixel = (new Color(in.getRGB(imageX, imageY))).getRed();
                if (pixel != 255) continue;

//Нашли пиксель на изображении
                for (int filterY = 0; filterY < in.getHeight(); filterY++) {
                    for (int filterX = 0; filterX < in.getWidth(); filterX++) {
                        if (f[filterY][filterX] != 1) continue; // проходим этот пиксель структ. эл-та
                        if (imageX + filterX - centX >= width || imageY + filterY - centY >= height || imageX + filterX - centX < 0 || imageY + filterY - centY < 0)
                            continue; // за границей изображения
                        in.setRGB(imageX + filterX - centX, imageY + filterY - centY, (new Color(255, 255, 255)).getRGB());
                    }
                }
            }
        }
        return in;
    }
}
