package display.screens;

import audio.AudioPlayer;
import audio.Music;
import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.screens.story.*;
import display.widgets.buttons.NormalButton;
import display.widgets.label.Align;
import display.widgets.label.Label;
import fonts.CustomFonts;
import progress.Progress;
import progress.Stage;

import java.awt.*;
import java.awt.event.WindowEvent;

public class MainMenuScreen extends Screen {

    /*
    The first screen that opens when the game starts.
     */

    private static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 20);

    @Override
    public void load(MainFrame mainFrame) {
        AudioPlayer.play(Music.MAIN_MENU);

        display.widgets.label.Label titleLabel = new Label("Ultimate Tic-tac-toe", titleFont, Color.RED, new display.frame.misc.Dimension(80, 8), Align.CENTER);
        titleLabel.coordinates = new Coordinates(10, 20);
        addDisplayComponent(titleLabel, mainFrame.panel);

        // adds a few buttons pointing to different screens
        NormalButton gameButton = new NormalButton("Campaign", 5, new Dimension(40, 8)) {
            @Override
            public void clicked() {
                if (Progress.campaignDialog) {
                    Progress.campaignDialog = false;
                    Controller.switchScreen(storyScreen());
                } else {
                    Controller.switchScreen(new CampaignScreen());
                }
            }
        };
        gameButton.coordinates = new Coordinates(30, 40);
        addDisplayComponent(gameButton, mainFrame.panel);

        NormalButton practice = new NormalButton("Practice", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new PracticeScreen(mainFrame));
            }
        };
        practice.coordinates = new Coordinates(30,50);
        addDisplayComponent(practice, mainFrame.panel);

        NormalButton settings = new NormalButton("Settings", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new SettingsScreen());
            }
        };
        settings.coordinates = new Coordinates(30,60);
        addDisplayComponent(settings, mainFrame.panel);

        NormalButton quit = new NormalButton("Quit", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
            }
        };
        quit.coordinates = new Coordinates(30,70);
        addDisplayComponent(quit, mainFrame.panel);
    }

    // gets current story screen
    static Screen storyScreen() {
        Screen screen = null;
        switch (Progress.newestStage) {
            case Stage.STAGE1:
                screen = new Exposition();
                break;
            case Stage.STAGE2:
                screen = new RisingAction();
                break;
            case Stage.STAGE3:
                screen = new Climax();
                break;
            case Stage.STAGE4:
                screen = new FallingAction();
                break;
            case Stage.THE_END:
                screen = new Denouement();
                break;
        }
        return screen;
    }

}
