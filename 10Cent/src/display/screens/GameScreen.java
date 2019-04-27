package display.screens;

import display.frame.Coordinates;
import display.frame.MainFrame;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;

public class GameScreen extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        TwoDimensionalBoard board = new TwoDimensionalBoard(2);
        board.coordinates = new Coordinates(100, 100);
        addDisplayComponent(board, mainFrame.panel);
    }

}
