import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Васили on 18.11.2017.
 */
public class Table extends JFrame {

    Table(File file, int numR, int numC, int i, int j) {
        JTable table = new JTable(numR, numC);
        setSize(400, 300);
        table.setRowHeight(numR, 40);
        table.setIntercellSpacing(new Dimension(40, 10));
        table.repaint();
        Box content = new Box(BoxLayout.Y_AXIS);
        JPanel panel = new JPanel();
        int[][] f = new int[numR][numC];
        JButton button = new JButton("ok");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = 0; k < numR; k++) {
                    for (int l = 0; l < numC; l++) {
                        System.out.println(k + " " + l + " " + numC + " " + numR);
                        f[k][l] = Integer.valueOf(table.getModel().getValueAt(k, l).toString());
                        System.out.println(f[k][l]);
                    }
                }

                BufferedImage image = null;
                try {
                    image = ImageIO.read(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    matric(image, f, numR, numC, i, j);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        BorderLayout bl = new BorderLayout();
        panel.setLayout(bl);
        panel.add(BorderLayout.CENTER, table);
        panel.add(BorderLayout.SOUTH, button);
        setContentPane(panel);
        setVisible(true);
    }


    public static void matric(BufferedImage image, int[][] f, int numR, int numC, int i, int j) throws IOException {
        BufferedImage im = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int r, g, b;
        Color c;
        float koeff = 0;
        for (int p = 0; p < numR; p++) {
            for (int y = 0; y < numC; y++) {
                System.out.println();
                koeff += f[p][y];
            }
        }
        if (koeff == 0) koeff = 1;
        int wi = image.getWidth();
        int h = image.getHeight();
        for (int k = 0; k < wi; k++) {
            for (int l = 0; l < h; l++) {
                r = 0;
                g = 0;
                b = 0;

                for (int q = 0; q < numC; q++) {
                    for (int w = 0; w < numR; w++) {
                        int y = (h + l + w - i + 1) % h;
                        int x = (wi + k + q - j + 1) % wi;
                        //c = new Color(image.getRGB(2*(k+q-i+1)/wi,2*(l+w-j+1)/h));
                        c = new Color(image.getRGB(x, y));
                        r = r + (int) (c.getRed() * f[w][q]);
                        g = g + (int) (c.getGreen() * f[w][q]);
                        b = b + (int) (c.getBlue() * f[w][q]);
                    }
                }
                r = (int) (r / koeff);
                g = (int) (g / koeff);
                b = (int) (b / koeff);
                if (r < 0) r = 0;
                if (r > 255) r = 255;
                if (g < 0) g = 0;
                if (g > 255) g = 255;
                if (b < 0) b = 0;
                if (b > 255) b = 255;
                c = new Color(r, g, b);
                im.setRGB(k, l, c.getRGB());
            }
        }


        //  JFrame frame = new JFrame();
        // frame.setSize(1200,700);
        File out = new File("matrix.png");
        try {
            ImageIO.write(im, "png", out);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("fefe");
        JPanel panel = new JPanel();
        ImageComponent image1 = new ImageComponent(out, im);
        panel.add(BorderLayout.CENTER, image1);
        //frame.add(panel);
        //  frame.setVisible(true);


        ImageIO.write(im, "jpg", out);
        Histogram frame = new Histogram();
        frame.setFile(out);


        // System.out.println(outputFile.toString());
        frame.setOutputFile(out);
        frame.create("eq" + out.getName());
        frame.setVisible(true);
    }

}
