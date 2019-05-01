import audio.AudioPlayer;
import audio.MusicPlayer;
import display.screens.Controller;
import display.images.Images;
import display.frame.MainFrame;
import logic.boards.exceptions.InvalidMoveException;
import settings.Settings;

import java.nio.file.Paths;

public class TenCent {

    public static void main(String[] args) throws InvalidMoveException {

        Images.loadImages(Paths.get(Images.RESOURCES_PATH,"images").toString());

        MainFrame mainFrame = new MainFrame();
        Controller.install(mainFrame);
        mainFrame.pack();
        mainFrame.setVisible(true);
        Settings.setup();
        AudioPlayer.play(MusicPlayer.TEST);

    }

}
