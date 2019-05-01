package display.screens;

import display.frame.misc.Coordinates;
import display.frame.MainFrame;
import display.frame.misc.Dimension;
import display.widgets.buttons.Button;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;

public class GameScreen extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        TwoDimensionalBoard board = new TwoDimensionalBoard(2) {
            @Override
            protected TwoDimensionalBoard installBoard() {
                return new TwoDimensionalBoard(2);
            }
        };
        board.coordinates = new Coordinates(30, 30);
        addDisplayComponent(board, mainFrame.panel);
    }

}
