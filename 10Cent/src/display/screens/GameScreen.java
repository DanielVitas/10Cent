package display.screens;

import display.frame.MainFrame;
import logic.boards.Board;
import logic.game.GameController;

public abstract class GameScreen extends Screen {

    /*
    Screen used for playing games.
     */

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

    @Override
    public void unload(MainFrame mainFrame) {
        gameController.terminate();
        super.unload(mainFrame);
    }

    // loads background and other visual candies
    public abstract void loadEnvironment(MainFrame mainFrame);

    // loads the game that will be played on screen
    public abstract void loadGame(MainFrame mainFrame);


}
