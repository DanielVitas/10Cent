package display.screens.story;

import display.frame.DisplayComponent;
import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.screens.CampaignScreen;
import display.screens.Controller;
import display.screens.Screen;
import display.widgets.buttons.NormalButton;
import display.widgets.label.Align;
import display.widgets.label.Label;
import fonts.CustomFonts;
import progress.Progress;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static display.screens.RulebookScreen.rules;

public class StoryRulesScreen extends Screen {

    public static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 15);
    public static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 3);

    private String game;
    private Screen nextScreen;

    public StoryRulesScreen(String game, Screen nextScreen) {
        this.game = game;
        this.nextScreen = nextScreen;
    }

    @Override
    public void load(MainFrame mainFrame) {
        display.widgets.label.Label label = new Label(game, titleFont, Color.BLACK, new display.frame.misc.Dimension(80, 8), Align.CENTER);
        label.coordinates = new Coordinates(10, 10);
        addDisplayComponent(label, mainFrame.panel);

        rules(game, new Coordinates(10, 22), new Dimension(80, 8), this, mainFrame);

        addDefaultContinueButton(mainFrame);
    }

    private void addDefaultContinueButton(MainFrame mainFrame) {
        NormalButton continueButton = new NormalButton("Continue", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                Progress.addGame(game);
                Controller.switchScreenWithoutBacking(nextScreen);
            }
        };
        continueButton.coordinates = new Coordinates(30,90);
        addDisplayComponent(continueButton, mainFrame.panel);
    }

}
