package display;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static int targetedFramerate = 60;

    public MainPanel panel;
    private RepaintThread repaintThread;

    public MainFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new MainPanel(new Dimension(600, 400));
        add(panel);
        repaintThread = new RepaintThread(panel);
        repaintThread.start();
    }

}
