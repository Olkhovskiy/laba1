import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Васили on 05.12.2017.
 */
public class StructurElement extends JFrame {

    StructurElement(File file, int numR, int numC) {
        setTitle("ololo");
        System.out.println("4");
        JTable table = new JTable(numR, numC);
        System.out.println("5");
        setSize(400, 300);
        table.setRowHeight(numR, 40);
        table.setIntercellSpacing(new Dimension(40, 10));
        table.repaint();

        Box content = new Box(BoxLayout.Y_AXIS);
        JPanel panel = new JPanel();
        float[][] f = new float[numR][numC];
        JButton button = new JButton("ok");
        float[][] matrix = new float[][]{
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = 0; k < numR; k++) {
                    for (int l = 0; l < numC; l++) {
                        System.out.println(k + " " + l + " " + numC + " " + numR);
                        f[k][l] = Float.valueOf(table.getModel().getValueAt(k, l).toString());
                        System.out.println(f[k][l]);
                        dispose();
                    }
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

}
