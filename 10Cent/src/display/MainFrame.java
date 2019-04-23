package display;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static int targetedFramerate = 60;

    private MainPanel panel;
    private RepaintThread repaintThread;

    public MainFrame() {
        super();
        panel = new MainPanel(new Dimension(400, 600));
        repaintThread = new RepaintThread(panel);
        repaintThread.start();
    }

}
