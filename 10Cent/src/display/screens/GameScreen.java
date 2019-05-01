package display.screens;

import display.frame.misc.Coordinates;
import display.frame.MainFrame;
import display.frame.misc.Dimension;
import display.widgets.buttons.Button;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;
import logic.boards.Move;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;
import logic.game.GameController;
import logic.game.StandardGameController;
import logic.intelligence.Human;
import logic.players.Player;
import logic.players.cross.Cross;

public class GameScreen extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        GameController gameController = new StandardGameController(new Player[]{new Cross(new Human()), new Cross(new Human())});
        TwoDimensionalBoard board = new TwoDimensionalBoard(null, gameController, 2) {
            @Override
            protected TwoDimensionalBoard installBoard(Move move) {
                return new TwoDimensionalBoard(move, gameController, 2);
            }
        };
        gameController.board = board;
        gameController.start();
        board.coordinates = new Coordinates(30, 30);
        addDisplayComponent(board, mainFrame.panel);
    }

}
