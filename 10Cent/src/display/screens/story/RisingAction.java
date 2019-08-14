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

import static display.screens.story.Exposition.titleFont;
import static display.screens.story.Exposition.font;
import static display.screens.story.Exposition.titleLabel;

public class RisingAction extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        String title = "Rising Action";
        addDisplayComponent(titleLabel(title), mainFrame.panel);

        String text = "You've beaten the gate guard and have progressed further into the capital. Once most " +
                "magnificent city, crowded with joyful people, now a mere shadow of what it used to be. It saddens " +
                "you to see it like this - your resentment towards the king increases. You will end his reign.\n\n" +
                "But even the most evil of people have sympathizers. As you walk through empty streets a dark figure " +
                "approaches you. It's seems you must face another obstacle to your just goal.";

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
