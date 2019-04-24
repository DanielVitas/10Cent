import display.MainFrame;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.finalBoard.FinalBoard;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TenCent {

    public static void main(String[] args) throws InterruptedException {

        MainFrame mainFrame = new MainFrame();
        FinalBoard board = new FinalBoard();
        mainFrame.panel.addDisplayComponent(board);
        mainFrame.pack();
        mainFrame.setVisible(true);

        String bip = "10Cent/test.mp3";
        final JFXPanel fxPanel = new JFXPanel();
        Media hit;
        hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.1);
        while(true){
            System.out.println(mediaPlayer.getVolume());
            mediaPlayer.setVolume(mediaPlayer.getVolume() + 0.1);
            TimeUnit.SECONDS.sleep(3);
        }
    }

}
