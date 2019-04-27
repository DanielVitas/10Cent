import display.MainFrame;
import logic.boards.finalBoard.FinalBoard;

public class TenCent {

    public static void main(String[] args){

        MainFrame mainFrame = new MainFrame();
        FinalBoard board = new FinalBoard();
        mainFrame.panel.addDisplayComponent(board);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }
    }


