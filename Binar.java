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
                System.out.println("7");
            }
        });
        JButton button1 = new JButton("Дилатация");
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
        panel.add(BorderLayout.SOUTH,panel2);

        add(panel);
        setSize(600, 400);
        setVisible(true);
    }


}
