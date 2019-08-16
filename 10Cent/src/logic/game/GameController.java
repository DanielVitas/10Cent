package logic.game;

import display.screens.GameScreen;
import logic.boards.Board;
import logic.boards.Move;
import logic.players.Player;

import java.util.Stack;

public abstract class GameController extends Thread {

    /*
    GameController should be created for every game separately. It controls the game's flow.
     */

    // only one gameController is active at the time
    public static GameController gameController;
    boolean stop = false;  // stop softly stops thread running the game

    public GameScreen gameScreen;  // screen on which the game is played - is set from that screen
    protected Player[] players;
    public Board board;
    int turnCount = 0;  // keeps track of turns passed - also determines player on turn
    public boolean awaitingPlayer = false;  // is set to true while waiting for human to play

    Stack<Move> previousMoves = new Stack<>();  // stack of all previous moves
    public Move currentMove;  // is set by intelligence

    public GameController(Player[] players) {
        this.players = players;
        for (Player player : players)
            player.intelligence.gameController = this;
    }

    // is called whenever player conducting turn is switched (after)
    void onPlayerSwitch() {
        if (gameScreen != null)
            gameScreen.onPlayerSwitch(turnCount);
    }

    // gets previous move played
    public Move previousMove() {
        if (!previousMoves.empty())
            return previousMoves.peek();
        return null;
    }

    // gets player on turn after additionalTurns turns
    public Player getPlayer(int additionalTurns) {
        return players[(turnCount + additionalTurns) % players.length];
    }

    // gets player currently on turn
    public Player currentPlayer() {
        return getPlayer(0);
    }

    // is called when game ends with winner passed as parameter (can be undecided)
    public abstract void onWin(Player player);

    // is called when switching from game screen to another
    public void terminate() {
        stop = true;
        if (gameController == this)
            gameController = null;
    }

    @Override
    public void start() {
        stop = false;
        if (gameController != null)
            gameController.terminate();  // should already be terminated, but doesn't hurt to make sure
        gameController = this;
        super.start();
    }

}
