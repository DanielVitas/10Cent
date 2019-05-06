package display.screens;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.screens.story.*;
import display.widgets.buttons.NormalButton;
import progress.Progress;
import progress.Stage;

import java.awt.event.WindowEvent;

public class MainMenuScreen extends Screen {

    @Override
    public void load(MainFrame mainFrame) {
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
        gameButton.coordinates = new Coordinates(30, 25);
        addDisplayComponent(gameButton, mainFrame.panel);

        NormalButton practice = new NormalButton("Practice", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new PracticeScreen(mainFrame));
            }
        };
        practice.coordinates = new Coordinates(30,35);
        addDisplayComponent(practice, mainFrame.panel);

        NormalButton settings = new NormalButton("Settings", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new SettingsScreen());
            }
        };
        settings.coordinates = new Coordinates(30,45);
        addDisplayComponent(settings, mainFrame.panel);

        NormalButton quit = new NormalButton("Quit", 5, new Dimension(40,8)) {
            @Override
            public void clicked() {
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
            }
        };
        quit.coordinates = new Coordinates(30,55);
        addDisplayComponent(quit, mainFrame.panel);
    }

    public static Screen storyScreen() {
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
            case Stage.THEEND:
                screen = new Denouement();
                break;
        }
        return screen;
    }

}
