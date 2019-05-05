package display.screens;

import display.frame.misc.Coordinates;
import display.frame.MainFrame;
import display.frame.misc.Dimension;
import display.screens.Controller;
import display.screens.Screen;
import display.widgets.buttons.NormalButton;
import logic.boards.Board;
import logic.boards.Move;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;
import logic.game.GameController;
import logic.game.StandardGameController;
import logic.intelligence.Human;
import logic.intelligence.RandomAI;
import logic.players.Player;
import logic.players.cross.Cross;
import logic.players.nought.Nought;

public abstract class GameScreen extends Screen {

    protected GameController gameController;
    protected Board board;

    public GameScreen(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void load(MainFrame mainFrame) {
        loadEnvironment(mainFrame);
        loadGame(mainFrame);

        addDefaultBackButton(mainFrame);
    }

    public abstract void loadEnvironment(MainFrame mainFrame);

    public abstract void loadGame(MainFrame mainFrame);


}
