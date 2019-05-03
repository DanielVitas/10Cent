import audio.AudioPlayer;
import display.frame.MainFrame;
import display.images.Images;
import display.screens.Controller;
import settings.Settings;

import java.nio.file.Paths;

public class TenCent {

    public static void main(String[] args) {
        Images.loadImages(Paths.get(Images.RESOURCES_PATH,"images").toString());
        AudioPlayer.setup();
        Settings.initialize();

        MainFrame mainFrame = new MainFrame();

        Controller.install(mainFrame);
        //mainFrame.pack();
        mainFrame.setVisible(true);
    }

}
