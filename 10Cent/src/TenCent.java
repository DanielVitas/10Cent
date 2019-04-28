import audio.AudioPlayer;
import audio.MusicPlayer;
import display.Controller;
import display.images.Images;
import display.frame.MainFrame;
import javafx.embed.swing.JFXPanel;
import logic.boards.exceptions.InvalidMoveException;

import java.nio.file.Paths;

public class TenCent {

    public static void main(String[] args) throws InvalidMoveException {
        Images.loadImages(Paths.get(Images.resourcesPath,"images").toString());

        MainFrame mainFrame = new MainFrame();
        Controller.install(mainFrame);
        mainFrame.pack();
        mainFrame.setVisible(true);

        JFXPanel panel = new JFXPanel();

        System.out.println(AudioPlayer.FILEPATH_MUSIC);
        AudioPlayer.play(MusicPlayer.TEST);
    }

}
