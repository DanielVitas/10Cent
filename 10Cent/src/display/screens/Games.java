package display.screens;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import logic.boards.Move;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;
import logic.game.GameController;

public final class Games {

    /*
    Used to save space in other classes where it is more crucial.
     */

    public final static String TIC_TAC_TOE = "Tic-tac-toe";
    public final static String SUPER_TIC_TAC_TOE = "Super Tic-tac-toe";
    public final static String ULTIMATE_TIC_TAC_TOE = "Ultimate Tic-tac-toe";


    public static void ticTacToe(int size, Coordinates coordinates, Dimension dimension, GameController gameController, GameScreen screen, MainFrame mainFrame) {
        if (screen.board == null) {
            screen.board = new TwoDimensionalBoard(dimension, null, gameController, size);
            setBoard(coordinates, gameController, screen, mainFrame);
        }
        screen.addDisplayComponent(screen.board, mainFrame.panel);
    }

    public static void ultimateTicTacToe(int size, Coordinates coordinates, Dimension dimension, GameController gameController, GameScreen screen, MainFrame mainFrame) {
        if (screen.board == null) {
            screen.board = new TwoDimensionalBoard(dimension, null, gameController, size) {
                @Override
                protected TwoDimensionalBoard installBoard(Dimension dimension, Move move) {
                    return new TwoDimensionalBoard(dimension, move, gameController, size);
                }
            };
            setBoard(coordinates, gameController, screen, mainFrame);
        }
        screen.addDisplayComponent(screen.board, mainFrame.panel);
    }

    private static void setBoard(Coordinates coordinates, GameController gameController, GameScreen screen, MainFrame mainFrame) {
        gameController.board = screen.board;
        // if (!gameController.hasStarted)
        gameController.start();
        screen.board.coordinates = coordinates;
    }

}
