import audio.AudioPlayer;
import display.frame.MainFrame;
import display.images.Images;
import display.screens.Controller;
import fonts.CustomFonts;
import progress.Progress;
import settings.Settings;

public class TenCent {

    /*
    Main class used to setup / load all the necessary classes. Also creates MainFrame and sets it visible.
     */

    public static void main(String[] args) {
        CustomFonts.load();
        Progress.load();
        Images.load();
        AudioPlayer.setup();
        Settings.initialize();

        MainFrame mainFrame = new MainFrame();

        Controller.install(mainFrame);
        mainFrame.setVisible(true);
    }

}
