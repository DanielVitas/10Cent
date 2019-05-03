package display.screens;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;

public class MainMenuScreen extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        NormalButton gameButton = new NormalButton("Game", 5, new Dimension(40, 8)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new GameScreen());
            }
        };
        gameButton.coordinates = new Coordinates(30, 25);
        addDisplayComponent(gameButton, mainFrame.panel);

        NormalButton settings = new NormalButton("Settings", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new SettingsScreen());
            }
        };
        settings.coordinates = new Coordinates(30,35);
        addDisplayComponent(settings, mainFrame.panel);

    }

}
