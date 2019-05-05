import audio.AudioPlayer;
import display.frame.MainFrame;
import display.images.Images;
import display.screens.Controller;
import fonts.CustomFonts;
import progress.Progress;
import settings.Settings;

import java.nio.file.Paths;

public class TenCent {

    public static void main(String[] args) {
        CustomFonts.load();
        Progress.load();
        Images.load();
        AudioPlayer.setup();
        Settings.initialize();

        MainFrame mainFrame = new MainFrame();

        Controller.install(mainFrame);
        //mainFrame.pack();
        mainFrame.setVisible(true);
    }

}
