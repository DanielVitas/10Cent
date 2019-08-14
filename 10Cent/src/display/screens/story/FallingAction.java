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
import static display.screens.story.Exposition.titleLabel;

public class FallingAction extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        String title = "Falling Action";
        addDisplayComponent(titleLabel(title), mainFrame.panel);

        String text = "The king falls to the ground. He is defeated - finally. Before you could celebrate, " +
                "king starts his disgusting laugh. \"You think you have won?\" says king: \"I am a mere pawn, " +
                "puppet. You have no idea what you are doing. This world - it's not as it seems.\" The king proceeds " +
                "to laugh as he slowly looses consciousness.\n\n" +
                "Perhaps there are no true victories in life and this is the most one can do, but you are left with " +
                "feeling there is more to this story.";

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
