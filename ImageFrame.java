import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Васили on 21.10.2017.
 */


public class ImageFrame extends JFrame {

    public BufferedImage getBuffer() {
        return buffer;
    }

    public File getFile() {
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


    public void create(String name) {
        setTitle(name);
        // File file;
        setSize(1280, 720);
        ImageComponent imageComponent = new ImageComponent(file, buffer);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JButton button = new JButton("Эквализировать");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Histogram histogram = new Histogram();
                histogram.setSize(1280, 720);
                try {
                    histogram.setFile(histogram.equalization(file));

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton button2 = new JButton("Бинаризация");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage in = ImageIO.read(file);
                    Binar frame = new Binar(file,in);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        panel.add(BorderLayout.CENTER, imageComponent);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(BorderLayout.NORTH, button);
        panel2.add(BorderLayout.SOUTH, button2);
        panel.add(BorderLayout.SOUTH, panel2);
        add(panel);
    }

    public void control(boolean state, boolean state1, boolean state2, boolean state3, boolean state4) throws IOException {

        if (state) fast();
        if (state1) cor();
        if (state2) des();
        if (state3) gradMax();
        if (state4) gradMin();
    }

    public void fast() throws IOException {

        BufferedImage in = ImageIO.read(file);
        buffer = in;
        int width = in.getWidth();
        int height = in.getHeight();
        int red = 0;
        int green = 0;
        int blue = 0;

        Color c;
        //File outputFile;
        int sum;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                c = new Color(in.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                sum = (int) (0.3 * (red + green + blue));
                Color newcolor = new Color(sum, sum, sum);
                int rgb = newcolor.getRGB();
                in.setRGB(i, j, rgb);
            }
        String pathname = "fast/" + file.getName();
        outputFile = new File(pathname);
        ImageIO.write(in, "jpg", outputFile);
        ImageFrame frame = new ImageFrame();
        frame.setFile(outputFile);
        frame.setOutputFile(outputFile);
        // System.out.println(outputFile.toString());
        frame.create("fast" + file.getName());
        File fileq = new File("fast/" + file.getName());
        frame.setFile(fileq);
        System.out.println(fileq.getAbsolutePath());
        frame.setVisible(true);

    }

    private void gradMax() throws IOException {
        BufferedImage in = ImageIO.read(file);
        buffer = in;
        int width = in.getWidth();
        int height = in.getHeight();
        int red = 0;
        int green = 0;
        int blue = 0;

        Color c;
        //   File outputFile;
        int sum;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                c = new Color(in.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                sum = Math.max(red, Math.max(green, blue));
                Color newcolor = new Color(sum, sum, sum);
                int rgb = newcolor.getRGB();
                in.setRGB(i, j, rgb);
            }
        String pathname = "gradMax/" + file.getName();
        outputFile = new File(pathname);
        ImageIO.write(in, "jpg", outputFile);
        ImageFrame frame = new ImageFrame();
        frame.setFile(outputFile);
        frame.setOutputFile(outputFile);
        frame.create("gradMax" + file.getName());
        File fileq = new File("gradMax/" + file.getName());
        frame.setFile(fileq);
        frame.setVisible(true);
    }

    private void gradMin() throws IOException {
        BufferedImage in = ImageIO.read(file);
        buffer = in;
        int width = in.getWidth();
        int height = in.getHeight();
        int red = 0;
        int green = 0;
        int blue = 0;

        Color c;
        // File outputFile;
        int sum;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                c = new Color(in.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                sum = Math.min(red, Math.min(green, blue));
                Color newcolor = new Color(sum, sum, sum);
                int rgb = newcolor.getRGB();
                in.setRGB(i, j, rgb);
            }
        String pathname = "gradMin/" + file.getName();
        outputFile = new File(pathname);
        ImageIO.write(in, "jpg", outputFile);
        ImageFrame frame = new ImageFrame();
        frame.setFile(outputFile);
        frame.setOutputFile(outputFile);
        frame.create("gradMin" + file.getName());
        File fileq = new File("gradMin/" + file.getName());
        frame.setFile(fileq);
        frame.setVisible(true);
    }

    private void des() throws IOException {
        BufferedImage in = ImageIO.read(file);
        buffer = in;
        int width = in.getWidth();
        int height = in.getHeight();
        int red = 0;
        int green = 0;
        int blue = 0;
        Color c;
        // File outputFile;
        int sum;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                c = new Color(in.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                sum = ((Math.max(red, Math.max(green, blue)) + Math.min(red, Math.min(green, blue))) / 2);
                Color newcolor = new Color(sum, sum, sum);
                int rgb = newcolor.getRGB();
                in.setRGB(i, j, rgb);
            }
        String pathname = "des/" + file.getName();
        outputFile = new File(pathname);
        ImageIO.write(in, "jpg", outputFile);
        ImageFrame frame = new ImageFrame();
        frame.setFile(outputFile);
        frame.setOutputFile(outputFile);
        frame.create("des" + file.getName());
        File fileq = new File("des/" + file.getName());
        frame.setFile(fileq);
        frame.setVisible(true);
    }

    private void cor() throws IOException {
        BufferedImage in = ImageIO.read(file);
        buffer = in;
        int width = in.getWidth();
        int height = in.getHeight();
        int red = 0;
        int green = 0;
        int blue = 0;

        Color c;
        // File outputFile;
        int sum;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                c = new Color(in.getRGB(i, j));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                sum = (int) (0.3 * red + 0.59 * green + 0.11 * blue);
                Color newcolor = new Color(sum, sum, sum);
                int rgb = newcolor.getRGB();
                in.setRGB(i, j, rgb);
            }
        String pathname = "cor/" + file.getName();
        outputFile = new File(pathname);
        ImageIO.write(in, "jpg", outputFile);
        ImageFrame frame = new ImageFrame();
        frame.setFile(outputFile);
        frame.setOutputFile(outputFile);
        frame.create("cor" + file.getName());
        File fileq = new File("cor/" + file.getName());
        frame.setFile(fileq);
        frame.setVisible(true);
    }

    public File setFile(File file) {
        this.file = file;
        return file;
    }
}
