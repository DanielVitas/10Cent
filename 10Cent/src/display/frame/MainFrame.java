package display.frame;

import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import progress.Progress;
import settings.Settings;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    /*
    JFrame with more suitable constructor and a few additional fields and methods.
     */

    public static int targetedFrameRate = 60;

    public MainPanel panel;
    private RepaintThread repaintThread;
    private static MainFrame mainFrame;

    // constructor sets up various things - from panel, size to repaintThread
    public MainFrame() {
        super("Ultimate Tic-tac-toe");
        mainFrame = this;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                onClose();
                super.windowClosing(windowEvent);
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                saveSizeAndLocation();
            }
            @Override
            public void componentMoved(ComponentEvent componentEvent) {
                saveSizeAndLocation();
            }
        });

        panel = new MainPanel(new Dimension(600, 600));
        add(panel);

        repaintThread = new RepaintThread(panel);
        repaintThread.start();

        setWindow();
    }

    private void onClose() {
        Progress.save();
        saveSizeAndLocation();
        Settings.save();
    }

    // gets the original window scale - it's used when calling paint from MainPanel
    public static Scale getScale() {
        return new Scale(mainFrame.panel.getWidth() / 100f, mainFrame.panel.getHeight() / 100f);
    }

    // takes windowedMode setting from Settings and switches to Fullscreen if false and to Windowed if true
    public synchronized static void switchToWindowed(){
        mainFrame.dispose();
        setWindow();
        mainFrame.setVisible(true);
    }

    private static void setWindow() {
        mainFrame.setUndecorated(!Settings.windowedMode);
        if (Settings.windowedMode) {
            mainFrame.setExtendedState(JFrame.NORMAL);
            mainFrame.setSize(Settings.windowSize[0], Settings.windowSize[1]);
            mainFrame.setLocation(Settings.windowLocation[0], Settings.windowLocation[1]);
        } else {
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

    public static void saveSizeAndLocation(){
        if (Settings.windowedMode) {
            Settings.windowSize = new int[]{mainFrame.getSize().width, mainFrame.getSize().height};
            Settings.windowLocation = new int[]{mainFrame.getLocation().x, mainFrame.getLocation().y};
        }
    }
}
