package display.screens;

import display.frame.MainFrame;
import display.frame.MainPanel;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.label.Align;
import display.widgets.label.Label;
import fonts.CustomFonts;
import logic.boards.Board;
import logic.game.GameController;
import logic.players.Token;

import java.awt.*;

public abstract class GameScreen extends Screen {

    /*
    Screen used for playing games.
     */

    public static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 15);

    protected GameController gameController;
    protected Board board;
    private Token tokenDisplayed;
    private MainFrame mainFrame;

    GameScreen(GameController gameController, MainFrame mainFrame) {
        gameController.gameScreen = this;
        this.gameController = gameController;
        this.mainFrame = mainFrame;
    }

    // should be called from load environment, when overriding
    void standardEnvironmentSetup(String[] playerNames, MainPanel panel) {
        addNameLabels(playerNames[0], playerNames[1], panel);
    }

    // adds name labels on default location
    private void addNameLabels(String playerName, String opponentName, MainPanel panel) {
        Label opponentNameLabel = new Label(opponentName, titleFont, Color.BLACK, new display.frame.misc.Dimension(60, 8), Align.LEFT);
        opponentNameLabel.coordinates = new Coordinates(40, 5);
        addDisplayComponent(opponentNameLabel, panel);

        Label playerNameLabel = new Label(playerName, titleFont, Color.BLACK, new display.frame.misc.Dimension(60, 8), Align.LEFT);
        playerNameLabel.coordinates = new Coordinates(40, 87);
        addDisplayComponent(playerNameLabel, panel);
    }

    // is called from gameController when it switches player on turn
    public void onPlayerSwitch(int turnCount) {
        // there are situations where this function is called after the screen has been switched
        if (active) {
            if (tokenDisplayed != null)
                removeDisplayComponent(tokenDisplayed, mainFrame.panel);
            Token token = gameController.currentPlayer().newToken(null, null, new Dimension(8, 8));
            if (turnCount % 2 == 0)
                token.coordinates = new Coordinates(30, 87);
            else
                token.coordinates = new Coordinates(30, 5);
            tokenDisplayed = token;
            addDisplayComponent(token, mainFrame.panel);
            token.animatePlace();  // thread sleeps
        }
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
