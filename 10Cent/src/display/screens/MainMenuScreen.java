package display.screens;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;

public class MainMenuScreen extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        NormalButton gameButton = new NormalButton("Game", new Dimension(10, 10)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new GameScreen());
            }
        };
        gameButton.coordinates = new Coordinates(5, 5);
        addDisplayComponent(gameButton, mainFrame.panel);

        NormalButton settings = new NormalButton("Settings", new Dimension(10,10)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new SettingsScreen());
            }
        };
        settings.coordinates = new Coordinates(5,20);
        addDisplayComponent(settings, mainFrame.panel);

    }

}
