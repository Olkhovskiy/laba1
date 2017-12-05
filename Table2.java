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

    Table2(File file) {
        setLayout(new BorderLayout());
        setSize(300, 200);
        setTitle("Размеры структурирующего элемента");
        GridBagLayout gbl = new GridBagLayout();
        // setLayout(gbl);
        JLabel label = new JLabel("Введите размер структурирующего элемента");

        JTextField filterX = new JTextField(3);
        filterX.setSize(10, 5);
        JTextField filterY = new JTextField(3);
        filterY.setSize(10, 5);

        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = Integer.parseInt(filterX.getText());
                y = Integer.parseInt(filterY.getText());
                System.out.println("3");
                StructurElement structurElement = new StructurElement(file, x, y);
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
        panel.add(button, new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(20, 0, 0, 0), 10, 10));
        add(panel);
        setVisible(true);
    }
}
