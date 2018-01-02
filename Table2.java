import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Васили on 05.12.2017.
 */
public class Table2 extends JFrame {
    static int x, y;
    static int centX, centY;
    static float[][] f;

    Table2() {
        setLayout(new BorderLayout());
        setSize(400, 300);
        setTitle("Размеры структурирующего элемента");
        GridBagLayout gbl = new GridBagLayout();
        // setLayout(gbl);
        JLabel label = new JLabel("Введите размер структурирующего элемента");
        JLabel label2 = new JLabel("Введите координаты центрального элемента");
        JTextField filterX = new JTextField(3);
        filterX.setSize(10, 5);
        JTextField filterY = new JTextField(3);
        filterY.setSize(10, 5);
        JTextField centerX = new JTextField(3);
        centerX.setSize(10, 5);
        JTextField centerY = new JTextField(3);
        centerY.setSize(10, 5);


        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = Integer.parseInt(filterX.getText());
                y = Integer.parseInt(filterY.getText());
                System.out.println("3");
                StructurElement structurElement = new StructurElement(x, y);
                f= structurElement.f;
                centX = Integer.parseInt(centerX.getText())-1;
                centY = Integer.parseInt(centerY.getText())-1;
                System.out.println("6");
                dispose();

            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(gbl);
        panel.add(label, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 10, 10));
        panel.add(filterX, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 10));
        panel.add(filterY, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 10));
        panel.add(label2, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(20, 0, 0, 0), 10, 10));
        panel.add(centerX, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 10));
        panel.add(centerY, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 10));
        panel.add(button, new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(20, 0, 0, 0), 10, 10));

        add(panel);
        setVisible(true);
    }
}
