package logic.intelligence;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.game.StandardGameController;
//import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MiniMax extends Intelligence{
    /*
        the AI is currently recursive but we would want it to be iterative
     */


    public final static String NAME = "Minimax";
    private int depth;

    public MiniMax(int depth) {
        this.depth = depth;
    }


    @Override
    public void play() {
        Move nextMove = getMove(gameController.board.logicBoard.clone(), gameController.previousMoves);
        nextMove.setPlayer(gameController.getCurrentPlayer());
        gameController.currentMove = nextMove;
    }

    private Move getMove(LogicBoard logicBoard, Stack<Move> previousMove) {
        List<Move> moves = new ArrayList<>(StandardGameController.legalMoves(logicBoard, previousMove));
        Double bestDouble = Double.NEGATIVE_INFINITY;
        Move bestMove = moves.get(0);

        LogicBoard testLogicBoard = logicBoard.clone();
        bestMove.setPlayer(gameController.getPlayerOnTurnCount(0));
        testLogicBoard.play(bestMove);

        bestDouble = evaluateMove(depth - 1, testLogicBoard, bestMove, false);


        for(Move move: moves) {
            if(move==bestMove)
                continue;
            LogicBoard newLogicBoard = logicBoard.clone();
            move.setPlayer(gameController.getPlayerOnTurnCount(0));
            newLogicBoard.play(move);
            Double evaluatedMove = evaluateMove(depth - 1, newLogicBoard, move, false);
            if(evaluatedMove > bestDouble) {
                bestDouble = evaluatedMove;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private double evaluateMove(int depth, LogicBoard logicBoard, Move previousMove, Boolean maximizing) {
        List<Move> validMoves = new ArrayList<>(StandardGameController.legalMoves(logicBoard, previousMove));
        List<Double> moveValues = new ArrayList<>();

        if(depth==0 || validMoves.isEmpty()) {
            moveValues.add(StandardGameController.evaluate(logicBoard, gameController.getCurrentPlayer()));
        }else {
            for(Move move : validMoves) {
                LogicBoard newLogicBoard = logicBoard.clone();
                move.setPlayer(gameController.getPlayerOnTurnCount(this.depth - depth));
                newLogicBoard.play(move);

                moveValues.add(evaluateMove(depth - 1, newLogicBoard, move, !maximizing));
            }
        }
/*
        if(gameController.getCurrentPlayer() == gameController.getPlayerOnTurnCount(this.depth - depth)) {
            return Collections.max(moveValues);
        }
        return Collections.min(moveValues);*/
        if(maximizing) {
            return Collections.max(moveValues);
        }
        return Collections.min(moveValues);
    }

    @Override
    public void close() {

    }

    @Override
    public void terminate() {

    }

}
