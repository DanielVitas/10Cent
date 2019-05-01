package display.screens;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;

public class MainMenuScreen extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
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

        NormalButton gameButton = new NormalButton("Game", new Dimension(10, 10)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new GameScreen());
            }
        };
        gameButton.coordinates = new Coordinates(5, 20);
        addDisplayComponent(gameButton, mainFrame.panel);

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
