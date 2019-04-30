package display.frame;

import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.screens.SettingsScreen;
import settings.Settings;

import javax.swing.*;
import java.util.Set;

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

    //Takes windowedMode setting from  Settings and switches to Fullscreen if false and to Windowed if true
    public synchronized static void switchToWindowed(){
        mainFrame.dispose();
        mainFrame.setUndecorated(!Settings.windowedMode);
        if(Settings.windowedMode){
            mainFrame.setSize(Settings.windowSizeX,Settings.windowSizeY);
            mainFrame.setLocation(Settings.windowLocationX,Settings.windowLocationY);
        } else {
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        mainFrame.setVisible(true);
    }

    public static void saveSizeAndLoc(){
        if(Settings.windowedMode && SettingsScreen.windowed){
            Settings.windowSizeX = mainFrame.getSize().width;
            Settings.windowSizeY = mainFrame.getSize().height;
            Settings.windowLocationX = mainFrame.getLocation().x;
            Settings.windowLocationY = mainFrame.getLocation().y;
        }
    }
}
