package display.screens;

import display.frame.misc.Coordinates;
import display.frame.MainFrame;
import display.frame.misc.Dimension;
import display.widgets.buttons.Button;
import display.widgets.buttons.NormalButton;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;

public class GameScreen extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        TwoDimensionalBoard board = new TwoDimensionalBoard(2) {
            @Override
            protected TwoDimensionalBoard installBoard() {
                return new TwoDimensionalBoard(2);
            }
        };
        board.coordinates = new Coordinates(30, 30);
        addDisplayComponent(board, mainFrame.panel);

        NormalButton button = new NormalButton("Hey", new Dimension(10, 10)) {
            @Override
            public void clicked() {
                this.label.text = "fey";
            }
        };
        button.coordinates = new Coordinates(10, 10);
        addDisplayComponent(button, mainFrame.panel);

        NormalButton settings = new NormalButton("Settings", new Dimension(10,10)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new SettingsScreen());
            }
        };
        settings.coordinates = new Coordinates(20,20);
        addDisplayComponent(settings, mainFrame.panel);

    }

}
