import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Васили on 04.12.2017.
 */
public class Binar extends JFrame {
    static int x, y;
    static int centX, centY;
    static float[][] f;
    static BufferedImage in;
    static int i=0;
    static File outputFile;
    static BufferedImage out;
    static Table2 table2;

    Binar(File file, BufferedImage in, int inten) {
        //Table2 table2;
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
        String pathname = "binar.png";
         outputFile = new File(pathname);

        try {
            ImageIO.write(in, "jpg", outputFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
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
                table2 = new Table2();
                table2.x = table2.x;
                x = table2.x;
                table2.y=table2.y;
                y = table2.y;
                //table2.centX=table2.centX-1;
                centX = table2.centX;
                //table2.centY=table2.centY-1;
                centY = table2.centY;
                f = table2.f;
                System.out.println("7");
            }
        });
        JButton button1 = new JButton("Дилатация");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pathname = "binar.png";
               File file = new File(pathname);
                File result = null;
                try {

                    System.out.println("x: "+ table2.x+ "  y: "+y+ " centX: "+ centX+ " centY: "+ centY);
                     result = dilatation(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                ImageComponent imageComponent = new ImageComponent(result, out);
                JFrame frame = new JFrame();
                frame.setSize(1200, 700);
                frame.setTitle("Дилатация");
                frame.add(imageComponent);
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.add(BorderLayout.CENTER, imageComponent);
                frame.add(panel);
                frame.setVisible(true);

            }
        });
        JButton button2 = new JButton("Эрозия");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pathname = "binar.png";
                File file = new File(pathname);
                File result = null;
                try {
                    System.out.println("x: "+ table2.x+ "  y: "+y+ " centX: "+ centX+ " centY: "+ centY);
                    result = erozia(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                ImageComponent imageComponent = new ImageComponent(result, out);
                JFrame frame = new JFrame();
                frame.setSize(1200, 700);
                frame.setTitle("Эрозия");
                frame.add(imageComponent);
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.add(BorderLayout.CENTER, imageComponent);
                frame.add(panel);
                frame.setVisible(true);

            }
        });
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
        setSize(700, 400);
        setVisible(true);
    }

    public File dilatation(File file) throws IOException {
        in = ImageIO.read(file);
        int height = in.getHeight(null);
        int width = in.getWidth(null);
        BufferedImage Image = getClone(in);
        for (int imageY = 0; imageY < height; imageY++) {
            for (int imageX = 0; imageX < width; imageX++) {

                int pixel = (new Color(in.getRGB(imageX, imageY))).getRed();
                if (pixel != 0) continue;

//Нашли пиксель на изображении
                for (int filterY = 0; filterY < table2.y; filterY++) {
                    for (int filterX = 0; filterX < table2.x; filterX++) {
                        if (table2.f[filterY][filterX] != 1) continue; // проходим этот пиксель структ. эл-та
                        if (imageX + filterX - table2.centX >= width || imageY + filterY - table2.centY >= height || imageX + filterX - table2.centX < 0 || imageY + filterY - table2.centY < 0)
                            continue; // за границей изображения
                        Image.setRGB(imageX + filterX - table2.centX, imageY + filterY - table2.centY, (new Color(0, 0, 0)).getRGB());
                    }
                }
            }
            }
       // out = Image;
        //File outputFile;
        String pathname = "Дилатация.png";
        File outputFile2 = new File(pathname);

        try {
            ImageIO.write(Image, "jpg", outputFile2);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return outputFile2;
    }


    private static BufferedImage getClone(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), 1);

        for (int y = 0; y < image.getHeight(); y++)
            for (int x = 0; x < image.getWidth(); x++)
                newImage.setRGB(x, y, image.getRGB(x, y));

        return newImage;
    }


    public File erozia(File file) throws IOException {

        in = ImageIO.read(file);
        int height = in.getHeight(null);
        int width = in.getWidth(null);
          BufferedImage Image = getClone(in);

        for (int imageY = 0; imageY < height; imageY++) {
            for (int imageX = 0; imageX < width; imageX++) {
                boolean flag = false;
                int pixel = (new Color(in.getRGB(imageX, imageY))).getRed();
                if (pixel == 255) {
                    //flag = true;
                    continue;

                }
                for (int filterY = 0; filterY < table2.y; filterY++) {
                    for (int filterX = 0; filterX < table2.x; filterX++) {
                        if (imageX + filterX - table2.centX >= width || imageY + filterY - table2.centY >= height || imageX + filterX - table2.centX < 0 || imageY + filterY - table2.centY < 0)
                            continue; // за границей изображения
                        //if (f[filterY][filterX] == 1)
                            if ((new Color(in.getRGB(imageX + filterX - table2.centX, imageY + filterY - table2.centY))).getRed() == 255)
                                flag = true;
                    }
                }
                if (flag) Image.setRGB(imageX, imageY, (new Color(255, 255, 255)).getRGB());
                }
            }

        String pathname = "Эрозия.png";
        File outputFile2 = new File(pathname);

        try {
            ImageIO.write(Image, "png", outputFile2);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return outputFile2;
    }
}
