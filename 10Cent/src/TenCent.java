import audio.AudioPlayer;
import audio.MusicPlayer;
import display.MainFrame;
import javafx.embed.swing.JFXPanel;
import logic.boards.finalBoard.FinalBoard;

public class TenCent {

    public static void main(String[] args){

        MainFrame mainFrame = new MainFrame();
        FinalBoard board = new FinalBoard();
        mainFrame.panel.addDisplayComponent(board);
        mainFrame.pack();
        mainFrame.setVisible(true);

        JFXPanel panel = new JFXPanel();

        AudioPlayer.play(MusicPlayer.TEST);
    }
    }


