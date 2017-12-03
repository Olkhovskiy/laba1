import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Васили on 02.12.2017.
 */
public class Cod extends JFrame {

    private static double[][] table;
    JLabel label1, label2, label3, label4, label5;


    static JTextField textField1, textField2, textField3, textField4, textField5;
    static float left, right;
    static String alpha;


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
        JButton button = new JButton("ok");
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


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coding();
                decoding();
            }
        });
        panel.add(button, new GridBagConstraints(0, 10, 1, 1, 0, 0.2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        add(panel);
        setSize(600, 400);
        setVisible(true);
    }


    public static JTextField getTextField1() {
        return textField1;
    }

    public static JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public static void coding() {
        alpha = getTextField1().getText();
        System.out.println(alpha);
        String[] alpha2 = alpha.split("");
        String word = getTextField2().getText();
        String[] word2 = word.split("");
        int length = alpha.length();   // длина словаря
        System.out.println(length);
        //float probability = 0;
        float oldleft = 0, oldright = 1;
        float newleft = 0, newright = 1;

        for (int i = 0; i < word.length(); i++) {
            oldleft = newleft;
            oldright = newright;
            // System.out.println(i);
            newleft = oldleft + (oldright - oldleft) * (alpha.indexOf(word2[i])) / length;
            newright = oldleft + (oldright - oldleft) * (alpha.indexOf(word2[i]) + 1) / length;
            System.out.println("left: " + newleft + "  right:  " + newright);
            left = newleft;
            right = newright;
        }


        float avg = (float) (left+((right - left) * 1.0) / 2);
        System.out.println("avg "+avg);
        textField3.setText(String.valueOf(avg));
        System.out.println("left " + left + " right  " + right);
        //System.out.println(alpha2[0]);
        //System.out.println(alpha2[1]);
        // System.out.println(alpha2[2]);
        table = new double[alpha.length()][2];
      //  System.out.println(alpha.length());
        for (int i = 0; i < length; i++) {
            table[i][0] = (float) ((i * 1.0) / length);
            table[i][1] = (float) (i + 1) / length;
            //System.out.println(i*1.0/length+ " fefewfwfeefweffew!!!!!!");
            System.out.println("Table left: " + table[i][0] + "  right: " + table[i][1]);
        }
        System.out.println("rfefewfewewfef");

    }

    public static void decoding() {
        //float oldleft = 0, oldright = 1;
        //float newleft = 0, newright = 1;
       // double avg = (right - left) / 2;
        String slovo = "";
        String[] alpha2 = alpha.split("");
        //int i = 0;
       // boolean flag = true;

        String cod = textField3.getText();
        double avg = Double.parseDouble(cod);
        System.out.println(avg+ " !!!!!!");

        while (avg < table[alpha.length()-1][0]) {

            for (int j = 0; j < alpha.length(); j++) {
                System.out.println(avg+ " 2342342342   ");
                if ((avg > table[j][0]) & (avg < table[j][1])) {
                    slovo = slovo + alpha.split("")[j];

                    avg = (avg - table[j][0]) / (table[j][1] - table[j][0]);
                    System.out.println(avg + "  avg ");
                    System.out.println("slovo    "+slovo);
                }
            }
        }

    }
}
