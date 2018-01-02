import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Васили on 21.10.2017.
 */
public class Histogram extends JFrame {
    public File getFile() {
        return file;
    }

    public File setFile(File file) {
        this.file = file;
        return file;
    }

    public File getOutputFile() {
        return outputFile;
    }

    private BufferedImage buffer;
    private File file;

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    private File outputFile;

    public Histogram() {
        setTitle("Эквализируемое изображение ");
    }

    public void create(String name) {
        GridBagLayout gbl = new GridBagLayout();
        setTitle(name);
        setSize(1280, 720);
        ImageComponent imageComponent = new ImageComponent(outputFile, buffer);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //panel.setLayout(gbl);
        JButton button1 = new JButton("построить гистограммы по исходному");
        JButton button2 = new JButton("построить гистограммы по целевому");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] a = new int[256];
//                for (int i = 0; i < 256; i++) {
//                    System.out.println(a[i] + "  " + i);
//                }
                File filehist = null;
                int[] hist = new int[0];
                int sum = 0;
                int max = 0;
                int[] b = new int[256];
                JFrame frame = new JFrame();
                frame.setTitle("Гистограммы");
                frame.setSize(1024, 720);
                //System.out.println(file.getAbsolutePath() + "   " + file.getAbsolutePath());
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                BufferedImage in;
                try {
                    in = ImageIO.read(file);
                    hist = histogram(in);


                    for (int i = 0; i < hist.length; i++) {
                        if (max < hist[i]) max = hist[i];
                    }
// b[i] - высота столбца в пикселях цвета i
                    for (int i = 0; i < hist.length; i++) {
                        b[i] = 700 * hist[i] / max;
                    }
                    // ширина столбца - 1200/256
                    Color newcolor;
                    BufferedImage out = new BufferedImage(1024, 720, 1);

                    for (int i = 719; i > 0; i--) {
                        for (int j = 0; j < 256; j++) {
                            if ((i < 720 - b[j]))
                                newcolor = new Color(255, 255, 255);

                            else
                                newcolor = new Color(0, 0, 0);
                            int rgb = newcolor.getRGB();
                            for (int k = 0; k < 4; k++) {
                                //for()
                                //System.out.println(j*4+k + "   " + i + "  " + rgb);
                                out.setRGB(j * 4 + k, i, rgb);
                            }
                        }
                    }
                    filehist = new File("histogram.png");
                    ImageIO.write(out, "jpg", filehist);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ImageComponent image1 = new ImageComponent(filehist, buffer);
                panel.add(BorderLayout.CENTER, image1);
                frame.add(panel);
                frame.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Гистограммы");
                frame.setSize(1024, 720);
                // System.out.println(outputFile.getAbsolutePath() + "   " + file.getAbsolutePath());
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                File filehist = null;
                int[] hist = new int[0];
                int sum = 0;
                int max = 0;
                int[] b = new int[256];

                BufferedImage in;
                try {
                    in = ImageIO.read(outputFile);
                    hist = histogram(in);

                    for (int i = 0; i < hist.length; i++) {
                        if (max < hist[i]) max = hist[i];
                    }
// b[i] - высота столбца в пикселях цвета i
                    for (int i = 0; i < hist.length; i++) {
                        b[i] = 700 * hist[i] / max;
                    }
                    // ширина столбца - 1200/256
                    Color newcolor;
                    BufferedImage out = new BufferedImage(1024, 720, 1);


                    for (int i = 719; i > 0; i--) {
                        for (int j = 0; j < 256; j++) {
                            if ((i < 720 - b[j]))
                                newcolor = new Color(255, 255, 255);
                            else
                                newcolor = new Color(0, 0, 0);
                            int rgb = newcolor.getRGB();
                            for (int k = 0; k < 4; k++) {
                                //for()
                                //System.out.println(j*4+k + "   " + i + "  " + rgb);
                                out.setRGB(j * 4 + k, i, rgb);
                            }
                        }
                    }
                    filehist = new File("histogramAn.png");
                    ImageIO.write(out, "jpg", filehist);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ImageComponent image1 = new ImageComponent(filehist, buffer);
                panel.add(BorderLayout.CENTER, image1);
                frame.add(panel);
                frame.setVisible(true);

            }
        });

        JButton filterButton = new JButton("Flter");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter filter = new Filter(outputFile);

            }
        });


        panel.add(BorderLayout.CENTER, imageComponent);
        JPanel panelDown = new JPanel();
        panelDown.setLayout(gbl);
        panelDown.add(button1, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
       // panelDown.add(filterButton, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
        panelDown.add(button2, (new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10)));

        panel.add(BorderLayout.SOUTH, panelDown);
        add(panel);
    }

    public int[] histogram(BufferedImage in) {
        int[] av = new int[256];
        for (int i = 0; i < in.getHeight(); i++) {
            for (int j = 0; j < in.getWidth(); j++) {
                Color c = new Color(in.getRGB(j, i));
                av[c.getGreen()]++;
            }
        }
        return av;
    }


    public File equalization(File file) throws IOException {
        BufferedImage in = ImageIO.read(file);
        int width = in.getWidth();
        int height = in.getHeight();
        int gray;
        int maxGray = -1;
        int minGray = 255;
        int[] av = histogram(in);

        for (int i = 0; i < av.length; i++) {
            if ((i > maxGray) && (av[i] != 0)) maxGray = i;
            if ((i < minGray) && (av[i] != 0)) minGray = i;
        }

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                Color c = new Color(in.getRGB(i, j));
                gray = (c.getRed() - minGray) * (av.length - 1) / (maxGray - minGray);
                Color newcolor = new Color(gray, gray, gray);
                in.setRGB(i, j, newcolor.getRGB());
            }
        String pathname = "eq/" + file.getName();
        outputFile = new File(pathname);
        System.out.println(outputFile.getAbsolutePath());
        ImageIO.write(in, "jpg", outputFile);
        Histogram frame = new Histogram();
        frame.setFile(file);


        // System.out.println(outputFile.toString());
        frame.setOutputFile(outputFile);
        frame.create("eq" + outputFile.getName());
        frame.setVisible(true);
        return file;
//
        //setVisible(true);

    }
}
