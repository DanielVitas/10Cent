package display.frame;

import display.frame.misc.Dimension;
import display.frame.misc.Scale;

import javax.swing.*;

public class MainFrame extends JFrame {

    public static int targetedFramerate = 60;

    public MainPanel panel;
    private RepaintThread repaintThread;
    private static MainFrame mainFrame;  // only here until better solution is found


    public MainFrame() {
        super();
        mainFrame = this;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new MainPanel(new Dimension(600, 600));
        add(panel);
        repaintThread = new RepaintThread(panel);
        repaintThread.start();
    }

    @Deprecated
    public static Scale getScale() {  // find better place for this function
        return new Scale(mainFrame.panel.getWidth() / 100f, mainFrame.panel.getHeight() / 100f);
    }
}
