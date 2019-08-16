package display.screens.story;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.screens.Screen;
import display.widgets.label.Align;
import display.widgets.label.TextLabel;

import java.awt.*;

import static display.screens.story.Exposition.*;

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

        addDefaultContinueButton(this, mainFrame);
    }

}
