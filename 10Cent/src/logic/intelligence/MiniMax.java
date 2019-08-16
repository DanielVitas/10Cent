package logic.intelligence;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.game.StandardGameController;
import logic.players.Player;

import java.util.Set;

import static logic.boards.Board.empty;

public class MiniMax extends Intelligence {

    /*
    Intelligence playing with well known mini-max algorithm. It includes alpha-beta pruning.
     */

    public final static String NAME = "Minimax";
    private int depth;
    private Thread calculationThread;

    public MiniMax(int depth) {
        this.depth = depth;
    }

    // should be overridden, if any other evaluate function is desired
    protected double evaluate(LogicBoard logicBoard) {
        return StandardGameController.evaluate(logicBoard, player);
    }

    @Override
    public void play() {
        // bestMove can take a long time to calculate, that's why it's ran by a new thread
        calculationThread = new Thread() {
            @Override
            public void run() {
                setName("Minimax thread");

                Move move = bestMove(gameController.board.logicBoard, gameController.previousMove(), 0,
                        Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).move;
                move.setPlayer(gameController.currentPlayer());  // if all moves are losing, player for the move is not set
                gameController.currentMove = move;
            }
        };
        calculationThread.start();  // even if the currentMove is set after the game terminates, it won't have any effect
    }

    // returns best move chosen with minimax algorithm including alpha beta pruning
    private EvaluatedMove bestMove(LogicBoard logicBoard, Move previousMove, int additionalTurn, double alpha, double beta) {
        Player outcome = logicBoard.outcome();
        if (additionalTurn == depth || outcome != empty) {
            EvaluatedMove evaluatedMove = new EvaluatedMove(null, 0);
            evaluatedMove.rating = evaluate(logicBoard);  // evaluates current board
            return evaluatedMove;
        }

        Player currentPlayer = gameController.getPlayer(additionalTurn);  // gets player on turn of calculation
        Set<Move> moves = StandardGameController.legalMoves(logicBoard, previousMove);  // cannot be empty

        // sets default best move - it will be returned only if all moves are losing
        EvaluatedMove currentBestMove = new EvaluatedMove(moves.iterator().next(),
                currentPlayer == player ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);

        for (Move move : moves) {
            LogicBoard newLogicBoard = logicBoard.clone();
            move.setPlayer(currentPlayer);  // move has empty player set by default
            newLogicBoard.play(move);
            // evaluates new logic board
            double rating = bestMove(newLogicBoard, move, additionalTurn +  1, alpha, beta).rating;

            if (currentPlayer == player) {
                if (rating > currentBestMove.rating) {
                    currentBestMove = new EvaluatedMove(move, rating);  // sets new best move
                    alpha = Math.max(alpha, rating);  // changes alpha
                }
            } else {
                if (rating < currentBestMove.rating) {
                    currentBestMove = new EvaluatedMove(move, rating);  // sets new best move
                    beta = Math.min(beta, rating);  // changes beta
                }
            }

            if (alpha >= beta)
                break;  // future calculations are irrelevant, as move won't be picked by either player
        }
        return currentBestMove;
    }


    @Override
    public void close() {

    }

    /*
    The following method uses deprecated function. That, of course, should be avoided, but stopping thread softly would
    take quite a bit of extra effort.
     */

    @Override
    public void terminate() {
        if (calculationThread != null)
            calculationThread.stop();  // so it won't slow down the program
    }
}
