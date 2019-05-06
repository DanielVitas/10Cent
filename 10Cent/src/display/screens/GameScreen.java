package display.screens;

import display.frame.MainFrame;
import logic.boards.Board;
import logic.game.GameController;

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

    @Override
    public void unload(MainFrame mainFrame) {
        gameController.terminate();
        super.unload(mainFrame);
    }

    public abstract void loadEnvironment(MainFrame mainFrame);

    public abstract void loadGame(MainFrame mainFrame);


}
