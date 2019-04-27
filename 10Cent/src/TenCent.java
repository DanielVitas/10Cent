import display.Controller;
import display.frame.Coordinates;
import display.images.Images;
import display.frame.MainFrame;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;

import java.nio.file.Paths;

public class TenCent {

    public static void main(String[] args) throws InvalidMoveException {

        Images.loadImages(Paths.get(Images.resourcesPath,"images").toString());

        MainFrame mainFrame = new MainFrame();
        new Controller(mainFrame);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

}
