import display.MainFrame;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.finalBoard.FinalBoard;

public class TenCent {

    public static void main(String[] args) throws InvalidMoveException {

        MainFrame mainFrame = new MainFrame();
        FinalBoard board = new FinalBoard();
        mainFrame.panel.addDisplayComponent(board);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

}
