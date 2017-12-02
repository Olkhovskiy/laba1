import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Васили on 18.10.2017.
 */
public class MainMenu extends JFrame {
    static File file = null;
    //private static final String[][] FILTERS = {{"jpg", "файлы jpg"},{"png", "файлы png"}};

    // private static final String[] FILTERS = {"jpg","png"};

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setSize(700, 250);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Выберите картинку");
        BorderLayout borderLayout = new BorderLayout();
        JPanel panelChoose = new JPanel();
        panelChoose.add(label);

        JButton buttonChoose = new JButton("Выбрать");
        buttonChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChosser();
                System.out.println(file);
            }
        });
        panelChoose.add(buttonChoose);
        JFrame frameFileChooser = new JFrame();
        GridBagLayout gbl = new GridBagLayout();

        final JPanel content = new JPanel();
        content.setLayout(gbl);
        Checkbox fastCheckbox = new Checkbox();
        Checkbox corCheckBox = new Checkbox();
        Checkbox desatur = new Checkbox();
        Checkbox gradMax = new Checkbox();
        Checkbox gradMin = new Checkbox();
        content.add(new JLabel(("Быстрый")), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
        content.add(fastCheckbox, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 25, 5, 5), 10, 40));

        content.add(new JLabel("Коррекция под человеческий глаз"), new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
        content.add(corCheckBox, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 75, 5, 5), 10, 40));

        content.add(new JLabel("Десатурация"), new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
        content.add(desatur, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 25, 5, 5), 10, 40));

        content.add(new JLabel("градация по максимуму"), new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
        content.add(gradMax, new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 65, 5, 5), 10, 40));

        content.add(new JLabel("градация по минимуму"), new GridBagConstraints(4, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
        content.add(gradMin, new GridBagConstraints(4, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 65, 5, 5), 10, 40));

        JPanel res = new JPanel(new GridBagLayout());

        res.add(panelChoose, new GridBagConstraints(0, 0, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        res.add(content, new GridBagConstraints(0, 1, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        frame.add(res);

        JPanel panelButton = new JPanel();
        JButton button = new JButton("Выполнить");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageFrame draw = new ImageFrame();
                draw.setFile(file);
                draw.create(file.getName());
                try {
                    draw.control(fastCheckbox.getState(), corCheckBox.getState(), desatur.getState(), gradMax.getState(), gradMin.getState());
                    // draw.setOutputFile(outputFile);
                    //System.out.println("tratata");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panelButton.add(button);
        res.add(panelButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        frame.setVisible(true);
    }

    public static void fileChosser() {
        JFrame frameFileChooser = new JFrame();
        JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg & png", "jpg", "png");
        fileChooser.setFileFilter(filter);
        int ret = fileChooser.showOpenDialog(frameFileChooser);
        if (ret == JFileChooser.APPROVE_OPTION) {

            file = fileChooser.getSelectedFile();
        }

    }
}
