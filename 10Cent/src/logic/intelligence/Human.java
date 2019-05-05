package logic.intelligence;

import logic.boards.Move;
import logic.game.StandardGameController;
import logic.players.Token;
import logic.players.empty.Empty;
import logic.players.empty.EmptyToken;

import java.util.HashSet;
import java.util.Set;

public class Human extends Intelligence {

    Set<Token> tokens = new HashSet<>();

    @Override
    public void play() {
        Set<Move> moves;
        if(gameController.previousMoves.empty()){
            moves = StandardGameController.legalMoves(gameController.board.logicBoard, null);
        } else {
            moves = StandardGameController.legalMoves(gameController.board.logicBoard, gameController.previousMoves.peek());
        }
        for (Move move : moves)
            tokens.add(gameController.board.getToken(move));
        for (Token token : tokens)
            ((EmptyToken) token).setWaiting(true);
        gameController.awaitingPlayer = true;
    }

    @Override
    public void close() {
        for (Token token : tokens)
            if (token instanceof EmptyToken)
                ((EmptyToken) token).setWaiting(false);
        tokens.clear();
    }

}
