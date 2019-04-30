import audio.AudioPlayer;
import audio.MusicPlayer;
import display.Controller;
import display.images.Images;
import display.frame.MainFrame;
import javafx.embed.swing.JFXPanel;
import logic.boards.exceptions.InvalidMoveException;
import settings.Settings;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;

public class TenCent {

    static double test;
    public static void main(String[] args) throws InvalidMoveException{
        Images.loadImages(Paths.get(Images.resourcesPath,"images").toString());

        MainFrame mainFrame = new MainFrame();
        Controller.install(mainFrame);
        mainFrame.pack();
        mainFrame.setVisible(true);

        JFXPanel panel = new JFXPanel();

        //System.out.println(Double.valueOf("teee"));
        //AudioPlayer.play(MusicPlayer.TEST);
        Settings.setup();
        Settings.musicVolume = 25;
        Settings.save();

    }

}
