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

public class Exposition extends Screen {

    private static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 20);
    public static Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.PLAIN, 3);

    static Label titleLabel(String title) {
        Label label = new Label(title, titleFont, Color.BLACK, new Dimension(80, 8), Align.CENTER);
        label.coordinates = new Coordinates(10, 10);
        return label;
    }

    @Override
    public void load(MainFrame mainFrame) {
        String title = "Exposition";
        addDisplayComponent(titleLabel(title), mainFrame.panel);

        String text = "We live in a society where tic-tac-toe is everything. Wealth, number of tokens one has acquired, " +
                "prestige, number of tournaments one has won, and power, number of games one has played.\n\n" +
                "Dark era has begun for such a world, as new king has claimed the throne. In times like this a hero is " +
                "needed more than ever. Will you, a no-name tic-tac-toe player, be able to save the world and free it's people?";

        TextLabel textLabel = new TextLabel(text, font, Color.BLACK, new Dimension(80, 8), Align.LEFT);
        textLabel.coordinates = new Coordinates(10, 25);
        addDisplayComponent(textLabel, mainFrame.panel);

        addDefaultContinueButton(this, mainFrame);
    }

    static void addDefaultContinueButton(Screen screen, MainFrame mainFrame) {
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
