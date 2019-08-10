package display.screens.story;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.screens.CampaignScreen;
import display.screens.Controller;
import display.screens.Screen;
import display.widgets.buttons.NormalButton;
import display.widgets.label.Align;
import display.widgets.label.Label;
import display.widgets.label.TextLabel;

import java.awt.*;

import static display.screens.story.Exposition.font;
import static display.screens.story.Exposition.titleFont;

public class Denouement extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        String title = "Denouement";

        display.widgets.label.Label titleLabel = new Label(title, titleFont, Color.BLACK, new display.frame.misc.Dimension(80, 8), Align.CENTER);
        titleLabel.coordinates = new Coordinates(10, 10);
        addDisplayComponent(titleLabel, mainFrame.panel);

        String text = "As you win the match, the ceiling starts to crumble. You dash towards the exit and " +
                "leave terrifying being behind. You barely make it in time - the dark cave is sealed behind " +
                "you with a large boulder. Who knows what happened to the monster, but looking up to the sky " +
                "one thing becomes clear - the meaning of life.\n\n" +
                "Tic-tac-toe.";

        TextLabel textLabel = new TextLabel(text, font, Color.BLACK, new display.frame.misc.Dimension(80, 8), Align.LEFT);
        textLabel.coordinates = new Coordinates(10, 25);
        addDisplayComponent(textLabel, mainFrame.panel);

        NormalButton continueButton = new NormalButton("Continue", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                Controller.switchScreenWithoutBacking(new CampaignScreen());
            }
        };
        continueButton.coordinates = new Coordinates(30,90);
        addDisplayComponent(continueButton, mainFrame.panel);
    }

}
