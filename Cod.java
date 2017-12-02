import javax.swing.*;
import java.awt.*;

/**
 * Created by Васили on 02.12.2017.
 */
public class Cod extends JFrame {

    JLabel label1, label2, label3, label4, label5;
    JTextField textField1, textField2, textField3, textField4, textField5;

    public Cod() {
        label1 = new JLabel("Введите словарь");
        textField1 = new JTextField();
        label2 = new JLabel("Введите слово");
        textField2 = new JTextField();
        label3 = new JLabel("Результат кодировки");
        textField3 = new JTextField();
        label4 = new JLabel("Введите цифровую последовательность для декодирования");
        textField4 = new JTextField();
        label5 = new JLabel("Результат декодирования");
        textField5 = new JTextField();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(20, 0, 0, 0), 0, 0));
        panel.add(textField1, new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(label2, new GridBagConstraints(0, 2, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(textField2, new GridBagConstraints(0, 3, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(label3, new GridBagConstraints(0, 4, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(textField3, new GridBagConstraints(0, 5, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(label4, new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(textField4, new GridBagConstraints(0, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(label5, new GridBagConstraints(0, 8, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(textField5, new GridBagConstraints(0, 9, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));



        add(panel);
        setSize(600, 400);
        setVisible(true);

    }
}
