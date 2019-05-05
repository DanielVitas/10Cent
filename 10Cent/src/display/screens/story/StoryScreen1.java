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
import fonts.CustomFonts;

import java.awt.*;
import java.awt.event.WindowEvent;

public class StoryScreen1 extends Screen {

    public static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 20);
    public static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 3);

    @Override
    public void load(MainFrame mainFrame) {
        String title = "Exposition";

        Label titleLabel = new Label(title, titleFont, Color.BLACK, new Dimension(80, 8), Align.CENTER);
        titleLabel.coordinates = new Coordinates(10, 10);
        addDisplayComponent(titleLabel, mainFrame.panel);

        String text = "We live in a society where tic-tac-toe is everything. Wealth, number of tokens one has acquired, " +
                "prestige, number of tournaments one has won, and power, number of games one has played.\n\n" +
                "Dark era has begun for such a world, as new king has claimed the throne, and it's up to you to save it.";

        TextLabel textLabel = new TextLabel(text, font, Color.BLACK, new Dimension(80, 8), Align.LEFT);
        textLabel.coordinates = new Coordinates(10, 22);
        addDisplayComponent(textLabel, mainFrame.panel);

        addDefaultContinueButton(this, mainFrame);
    }

    public static void addDefaultContinueButton(Screen screen, MainFrame mainFrame) {
        NormalButton continueButton = new NormalButton("Continue", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                Controller.switchScreenWithoutBacking(new CampaignScreen());
            }
        };
        continueButton.coordinates = new Coordinates(30,90);
        screen.addDisplayComponent(continueButton, mainFrame.panel);
    }

}
