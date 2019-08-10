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
import progress.Stage;

import java.awt.*;

public class DefeatScreen extends Screen {

    private static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 20);
    private static Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.PLAIN, 3);

    private int stage;

    public DefeatScreen(int stage) {
        this.stage = stage;
    }

    @Override
    public void load(MainFrame mainFrame) {
        String title = "Defeat";
        String text = "";
        switch (stage) {
            case Stage.STAGE1:
                text = "\"Scram peasant,\" says the guard as he pushes you away from the gateway. Your " +
                        "story hasn't even begun. Will you really let it end like this?";
                break;
            case Stage.STAGE2:
                text = "\"Hehe-hehe-hehehehehehe,\" mage laughs frantically: \"of course, no match for me, " +
                        "but you fought well nevertheless.\" Your grind your teeth in frustration. If you " +
                        "can't overcome some petty magician, you won't stand a chance against the king.";
                break;
            case Stage.STAGE3:
                text = "The king smirks as he turns away, leaving you laying on the ground. You feel like not even " +
                        "worth taunting - pathetic. You remember the times when you were a child, playing tic-tac-toe " +
                        "with your grandpa. He wasn't particularly good, but for you he was the best. You feel grandpa " +
                        "pushing you forwards. You rise up. This match is not over yet.";
                break;
            case Stage.STAGE4:
                text = "The monster devours you. But it seems you have very little left to be devoured. You rise " +
                        "up - simply because rising up is the only thing you know how to do.";
                break;
        }
        Label titleLabel = new Label(title, titleFont, Color.RED, new display.frame.misc.Dimension(80, 8), Align.CENTER);
        titleLabel.coordinates = new Coordinates(10, 10);
        addDisplayComponent(titleLabel, mainFrame.panel);

        TextLabel textLabel = new TextLabel(text, font, Color.BLACK, new display.frame.misc.Dimension(80, 8), Align.LEFT);
        textLabel.coordinates = new Coordinates(10, 25);
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
