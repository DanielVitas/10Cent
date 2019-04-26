import display.Images;
import display.MainFrame;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.finalBoard.FinalBoard;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;

public class TenCent {

    public static void main(String[] args) throws InvalidMoveException {

        MainFrame mainFrame = new MainFrame();
        TwoDimensionalBoard board = new TwoDimensionalBoard(2);
        mainFrame.panel.addDisplayComponent(board);
        mainFrame.pack();
        mainFrame.setVisible(true);

        Images.getFileNames(Images.resourcesPath + "/images/cross/default","");

    }

}
