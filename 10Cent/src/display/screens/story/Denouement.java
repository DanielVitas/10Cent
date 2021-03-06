package display.screens.story;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.screens.Screen;
import display.widgets.label.Align;
import display.widgets.label.TextLabel;

import java.awt.*;

import static display.screens.story.Exposition.addDefaultContinueButton;
import static display.screens.story.Exposition.font;
import static display.screens.story.Exposition.titleLabel;

public class Denouement extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
        String title = "Denouement";
        addDisplayComponent(titleLabel(title), mainFrame.panel);

        String text = "As you win the match, the ceiling starts to crumble. You dash towards the exit and " +
                "leave terrifying being behind. You barely make it in time - the dark cave is sealed behind " +
                "you with a large boulder. Who knows what happened to the monster, but looking up to the sky " +
                "one thing becomes clear - the meaning of life.\n\n" +
                "Tic-tac-toe.";

        TextLabel textLabel = new TextLabel(text, font, Color.BLACK, new display.frame.misc.Dimension(80, 8), Align.LEFT);
        textLabel.coordinates = new Coordinates(10, 25);
        addDisplayComponent(textLabel, mainFrame.panel);

        addDefaultContinueButton(this, mainFrame);
    }

}
