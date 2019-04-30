import display.screens.Controller;
import display.images.Images;
import display.frame.MainFrame;
import display.screens.GameScreen;
import logic.boards.exceptions.InvalidMoveException;

import java.nio.file.Paths;

public class TenCent {

    public static void main(String[] args) throws InvalidMoveException {

        Images.loadImages(Paths.get(Images.resourcesPath,"images").toString());

        MainFrame mainFrame = new MainFrame();
        Controller.install(mainFrame);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

}
