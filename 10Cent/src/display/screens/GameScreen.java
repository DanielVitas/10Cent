package display.screens;

import display.frame.misc.Coordinates;
import display.frame.MainFrame;
import display.frame.misc.Dimension;
import display.widgets.buttons.Button;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;
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

        NormalSlider slider = new NormalSlider(new Dimension(30, 1));
        slider.coordinates = new Coordinates(30, 10);
        addDisplayComponent(slider, mainFrame.panel);

        NormalButton button = new NormalButton("Hey", new Dimension(10, 10)) {
            @Override
            public void clicked() {
                this.label.text = Integer.toString((int) (slider.getValue() * 100));
            }
        };
        button.coordinates = new Coordinates(10, 10);
        addDisplayComponent(button, mainFrame.panel);
    }

}
