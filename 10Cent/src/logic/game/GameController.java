package logic.game;

import logic.boards.Board;
import logic.boards.Move;
import logic.players.Player;

import java.util.Stack;

public abstract class GameController extends Thread {

    /*
    GameController should be created for every game separately. It controls the game's flow.
     */

    // generally only 1 gameController is active at the time, but it can have "sub-gameControllers"
    public static GameController gameController;
    public boolean hasStarted = false;
    public boolean stop = false;

    protected Player[] players;
    public Board board;
    protected int turnCount = 0;
    public boolean awaitingPlayer = false;

    public Stack<Move> previousMoves = new Stack<>();
    public Move currentMove;

    public GameController(Player[] players) {
        this.players = players;
        for (Player player : players)
            player.intelligence.gameController = this;
    }

    public Move previousMove() {
        if (!previousMoves.empty())
            return previousMoves.peek();
        return null;
    }

    public Player getPlayer(int additionalTurns) {
        return players[(turnCount + additionalTurns) % players.length];
    }

    public Player currentPlayer() {
        return getPlayer(0);
    }

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
            gameController.terminate();
        gameController = this;
        super.start();
        hasStarted = true;
    }

}
