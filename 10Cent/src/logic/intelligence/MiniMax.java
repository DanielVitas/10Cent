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

    public MiniMax(int depth) {
        this.depth = depth;
    }

    protected double evaluate(LogicBoard logicBoard, Player player) {
        return StandardGameController.evaluate(logicBoard, player);
    }

    @Override
    public void play() {
        Move move = bestMove(gameController.board.logicBoard, gameController.previousMove(), 0,
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).move;
        move.setPlayer(gameController.currentPlayer()); // if all moves are losing, player for the move is not set
        gameController.currentMove = move;
    }

    private EvaluatedMove bestMove(LogicBoard logicBoard, Move previousMove, int additionalTurn, double alpha, double beta) {
        Player outcome = logicBoard.outcome();
        if (additionalTurn == depth || outcome != empty) {
            EvaluatedMove evaluatedMove = new EvaluatedMove(previousMove, 0);
            evaluatedMove.rating = evaluate(logicBoard, player);
            return evaluatedMove;
        }

        Player currentPlayer = gameController.getPlayer(additionalTurn);
        Set<Move> moves = StandardGameController.legalMoves(logicBoard, previousMove); // cannot be empty

        EvaluatedMove currentBestMove = new EvaluatedMove(moves.iterator().next(),
                currentPlayer == player ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);

        for (Move move : moves) {
            LogicBoard newLogicBoard = logicBoard.clone();
            move.setPlayer(currentPlayer);
            newLogicBoard.play(move);
            double rating = bestMove(newLogicBoard, move, additionalTurn +  1, alpha, beta).rating;

            if (currentPlayer == player) {
                if (rating > currentBestMove.rating) {
                    currentBestMove = new EvaluatedMove(move, rating);
                    alpha = Math.max(alpha, rating);
                }
            } else {
                if (rating < currentBestMove.rating) {
                    currentBestMove = new EvaluatedMove(move, rating);
                    beta = Math.min(beta, rating);
                }
            }

            if (alpha >= beta)
                break; // future calculations are irrelevant
        }
        return currentBestMove;
    }


    @Override
    public void close() {

    }

    @Override
    public void terminate() {

    }
}
