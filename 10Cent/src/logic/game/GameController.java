package logic.game;

import logic.boards.Board;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static display.frame.MainFrame.targetedFramerate;
import static logic.boards.Board.empty;

public abstract class GameController extends Thread {

    /*
    GameController should be created for every game separately.
     */

    public boolean hasStarted = false;

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

    public Player getPlayerOnTurnCount(int turnCount) {
        return players[(turnCount+this.turnCount) % players.length];
    }

    public Player getCurrentPlayer() {
        return players[turnCount % players.length];
    }

  //  public abstract Set<Move> legalMoves();

 /*   public boolean isLegal(Move move) {
        for (Move legalMove : legalMoves())
            if (legalMove.equals(move))
                return true;
        return false;
    }*/

    public abstract void onWin(Player player);

    @Override
    public void start() {
        super.start();
        hasStarted = true;
    }

}
