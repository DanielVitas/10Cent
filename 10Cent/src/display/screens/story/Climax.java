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

public class Climax extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        String title = "Climax";

        display.widgets.label.Label titleLabel = new Label(title, titleFont, Color.BLACK, new display.frame.misc.Dimension(80, 8), Align.CENTER);
        titleLabel.coordinates = new Coordinates(10, 10);
        addDisplayComponent(titleLabel, mainFrame.panel);

        String text = "After a close victory only fear and doubt cross your mind. Realising how many variations " +
                "of tic-tac-toe are in this world scares you, but it also fills you with intrigue. What kind of " +
                "game will the king challenge you to?\n\n" +
                "You stand before the castle, overgrown with moss. Only a few steps separate you from the fearsome king. " +
                "Turning back might seem tempting, but you know that's not a choice. You gather your courage and open the " +
                "castle doors.";

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
