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
import settings.Settings;

public class GameScreen extends Screen {

    public GameScreen() {

    }

    @Override
    public void load(MainFrame mainFrame) {
        GameController gameController = new StandardGameController(new Player[]{new Cross(new Human()), new Nought(new RandomAI())});
        TwoDimensionalBoard board = new TwoDimensionalBoard(new Dimension(60, 60), null, gameController, 2) {
            @Override
            protected TwoDimensionalBoard installBoard(Dimension dimension, Move move) {
                return new TwoDimensionalBoard(dimension, move, gameController, 2);
            }
        };
        gameController.board = board;
        gameController.start();
        board.coordinates = new Coordinates(20, 20);
        addDisplayComponent(board, mainFrame.panel);

        NormalButton backButton = new NormalButton("Back", 3, new Dimension(20, 8)) {
            @Override
            public void clicked() {
                Controller.back();
            }
        };
        backButton.coordinates = new Coordinates(2, 2);
        addDisplayComponent(backButton, mainFrame.panel);
    }

}
