package display.frame;

import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import settings.Settings;

import javax.swing.*;
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

        panel = new MainPanel(new Dimension(600, 600));
        add(panel);

        repaintThread = new RepaintThread(panel);
        repaintThread.start();

        mainFrame.setUndecorated(!Settings.windowedMode);
        if(Settings.windowedMode){
            mainFrame.setExtendedState(JFrame.NORMAL);
            mainFrame.setSize(Settings.windowSizeX,Settings.windowSizeY);
            mainFrame.setLocation(Settings.windowLocationX,Settings.windowLocationY);
        } else {
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

    private void onClose() {
        saveSizeAndLoc();
        Settings.save();
    }

    // gets the original window scale - it's used when calling paint from MainPanel
    public static Scale getScale() {
        return new Scale(mainFrame.panel.getWidth() / 100f, mainFrame.panel.getHeight() / 100f);
    }

    // takes windowedMode setting from Settings and switches to Fullscreen if false and to Windowed if true
    public synchronized static void switchToWindowed(){
        mainFrame.dispose();
        mainFrame.setUndecorated(!Settings.windowedMode);
        if(Settings.windowedMode){
            mainFrame.setExtendedState(JFrame.NORMAL);
            mainFrame.setSize(Settings.windowSizeX,Settings.windowSizeY);
            mainFrame.setLocation(Settings.windowLocationX,Settings.windowLocationY);
        } else {
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        mainFrame.setVisible(true);
    }

    public static void saveSizeAndLoc(){
        if(Settings.windowedMode){
            Settings.windowSizeX = mainFrame.getSize().width;
            Settings.windowSizeY = mainFrame.getSize().height;
            Settings.windowLocationX = mainFrame.getLocation().x;
            Settings.windowLocationY = mainFrame.getLocation().y;
        }
    }
}
