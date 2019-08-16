package logic.intelligence;

import logic.boards.Move;
import logic.game.StandardGameController;
import logic.players.Token;
import logic.players.empty.EmptyToken;

import java.util.HashSet;
import java.util.Set;

public class Human extends Intelligence {

    /*
    Human intelligence only takes care of most basic things - it waits for player's input.
     */

    public final static String NAME = "Human";
    private Set<Token> tokens = new HashSet<>();

    @Override
    public void play() {
        Set<Move> moves = StandardGameController.legalMoves(gameController.board.logicBoard, gameController.previousMove());
        for (Move move : moves)
            tokens.add(gameController.board.getToken(move));  // gathers tokens for all legal moves
        for (Token token : tokens)
            ((EmptyToken) token).setWaiting(true);  // animates them
        gameController.awaitingPlayer = true;
    }

    @Override
    public void close() {
        for (Token token : tokens)
            if (token instanceof EmptyToken)
                ((EmptyToken) token).setWaiting(false);  // makes them display default animation again
        tokens.clear();
    }

    @Override
    public void terminate() {
        close();
    }

}
