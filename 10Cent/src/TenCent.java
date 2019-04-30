import display.screens.Controller;
import display.images.Images;
import display.frame.MainFrame;
import display.screens.SettingsScreen;
import javafx.embed.swing.JFXPanel;
import logic.boards.exceptions.InvalidMoveException;
import settings.Settings;

import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TenCent {

    public static void main(String[] args) throws InvalidMoveException, InterruptedException{

        Images.loadImages(Paths.get(Images.resourcesPath,"images").toString());

        MainFrame mainFrame = new MainFrame();
        Controller.install(mainFrame);
        mainFrame.pack();
        mainFrame.setVisible(true);

        Settings.setup();

    }

}
