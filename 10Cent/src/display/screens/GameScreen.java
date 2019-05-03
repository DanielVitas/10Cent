package display.screens;

import audio.AudioPlayer;
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
import logic.intelligence.RandomAI;
import logic.players.Player;
import logic.players.cross.Cross;
import logic.players.nought.Nought;

public class GameScreen extends Screen {

    public GameScreen() {

    }

    @Override
    public void load(MainFrame mainFrame) {
        GameController gameController = new StandardGameController(new Player[]{new Cross(new Human()), new Nought(new RandomAI())});
        TwoDimensionalBoard board = new TwoDimensionalBoard(null, gameController, 3) {
            @Override
            protected TwoDimensionalBoard installBoard(Move move) {
                return new TwoDimensionalBoard(move, gameController, 3);
            }
        };
        gameController.board = board;
        gameController.start();
        board.coordinates = new Coordinates(30, 30);
        addDisplayComponent(board, mainFrame.panel);

        NormalButton backButton = new NormalButton("Back", 5,  new Dimension(10, 5)) {
            @Override
            public void clicked() {
                Controller.back();
            }
        };
        backButton.coordinates = new Coordinates(5, 5);
        addDisplayComponent(backButton, mainFrame.panel);
    }

}
