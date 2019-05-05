package display.screens;

import display.frame.DisplayComponent;
import display.frame.DisplayObject;
import display.frame.MainFrame;
import display.frame.MainPanel;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.widgets.buttons.*;
import display.widgets.label.Align;
import display.widgets.dropdownMenu.PlayerDropdownMenu;
import display.widgets.label.Label;
import display.widgets.label.TextLabel;
import fonts.CustomFonts;
import logic.game.GameController;
import logic.game.StandardGameController;
import logic.intelligence.Human;
import logic.intelligence.Intelligence;
import logic.intelligence.RandomAI;
import logic.players.Player;
import logic.players.nought.Nought;
import progress.Progress;
import progress.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static display.screens.MainMenuScreen.storyScreen;

public class CampaignScreen extends Screen {

    public static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 15);
    public static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 3);

    private List<DisplayComponent> stageDetailsObjects = new ArrayList<>();

    @Override
    public void load(MainFrame mainFrame) {
        Player[] players = Progress.getPlayers();
        List<String> oldPlayers = Progress.getOldPlayers();

        PlayerDropdownMenu playerMenu = new PlayerDropdownMenu(players, oldPlayers, Align.RIGHT, 3, new display.frame.misc.Dimension(8,8)) {
            @Override
            public void refresh() {
                super.refresh();
                Progress.selectedPlayer = getValue();
            }
        };
        playerMenu.coordinates = new Coordinates(90,2);
        playerMenu.displayPriority = 1;
        playerMenu.setValue(Progress.selectedPlayer);
        addDisplayComponent(playerMenu, mainFrame.panel);

        Waypoint firstWaypoint = new Waypoint(active(Stage.STAGE1), new Dimension(10, 5)) {
            @Override
            protected void clickedUnlocked() {
                stageDetails(Stage.STAGE1, mainFrame);
            }

            @Override
            protected void clickedLocked() {

            }
        };
        firstWaypoint.coordinates = new Coordinates(10, 85);
        addDisplayComponent(firstWaypoint, mainFrame.panel);

        Waypoint secondWaypoint = new Waypoint(active(Stage.STAGE2), new Dimension(10, 5)) {
            @Override
            protected void clickedUnlocked() {
                stageDetails(Stage.STAGE2, mainFrame);
            }

            @Override
            protected void clickedLocked() {

            }
        };
        secondWaypoint.coordinates = new Coordinates(40, 75);
        addDisplayComponent(secondWaypoint, mainFrame.panel);

        stageDetails(Progress.newestStage, mainFrame);

        addDefaultBackButton(mainFrame);
    }

    private void stageDetails(int stage, MainFrame mainFrame) {
        removeStageDetails(mainFrame);
        stageDescription(stage, mainFrame);

        ToggleButton button1 = new ToggleButton("Easy", 5, new Dimension(20, 8));

        ToggleButton button2 = new ToggleButton("Hard", 5, new Dimension(20, 8));
        button2.coordinates = new Coordinates(20, 0);

        ToggleButton[] buttons = new ToggleButton[] {button1, button2};

        ToggleSwitch toggleSwitch = new ToggleSwitch(buttons) {
            @Override
            public void select(DisplayObject displayObject) {
                ((ToggleButton) displayObject).animateClicked();
                super.select(displayObject);
            }

            @Override
            public void deselect(Coordinates coordinates) {
                ((ToggleButton) selectedObject).animateDefault();
                super.deselect(coordinates);
            }
        };
        toggleSwitch.select(button1);
        toggleSwitch.coordinates = new Coordinates(58, 78);
        stageDetailsObjects.add(toggleSwitch);
        addDisplayComponent(toggleSwitch, mainFrame.panel);

        NormalButton startButton = new NormalButton("Start", 5, new Dimension(20, 8)) {
            @Override
            public void clicked() {
                Player protagonist = Player.parseString(Progress.selectedPlayer, new Human());
                switch (stage) {
                    case Stage.STAGE1:
                        Intelligence intelligence = new RandomAI();
                        Player enemy = new Nought(intelligence);
                        Player[] players = new Player[] {protagonist, enemy};
                        if (toggleSwitch.getSelected() == button2)
                            players = reverse(players);
                        GameController gameController = new StandardGameController(players) {
                            @Override
                            public void onWin(Player player) {
                                if (player == protagonist) {
                                    Progress.addPlayer(Nought.NAME);
                                    Progress.newestStage = 2;
                                    Controller.switchScreenWithoutBacking(storyScreen());
                                }
                            }
                        };
                        Controller.switchScreen(new GameScreen(gameController) {
                            @Override
                            public void loadEnvironment(MainFrame mainFrame) {

                            }

                            @Override
                            public void loadGame(MainFrame mainFrame) {
                                Games.ticTacToe(3, new Coordinates(20, 20),
                                        new Dimension(60, 60), gameController,
                                        this, mainFrame);
                            }
                        });
                        break;
                    case Stage.STAGE2:
                        break;
                }
            }
        };
        startButton.coordinates = new Coordinates(78,90);
        stageDetailsObjects.add(startButton);
        addDisplayComponent(startButton, mainFrame.panel);
    }

    private void stageDescription(int stage, MainFrame mainFrame) {
        String title = null;
        String text = null;
        switch (stage) {
            case Stage.STAGE1:
                title = "Gate Guard";

                text = "Stubborn guard blocks your way at the entrance to the capital. He might seem " +
                        "like your average Joe, but this guard takes pride in his knowledge of the " +
                        "ordinary tic-tac-toe. Will you be able to defeat him?";
                break;
            case Stage.STAGE2:
                title = "Encounter";

                text = "";
                break;
        }
        display.widgets.label.Label titleLabel = new Label(title, titleFont, Color.BLACK, new Dimension(40, 10), Align.CENTER);
        titleLabel.coordinates = new Coordinates(58, 20);
        stageDetailsObjects.add(titleLabel);
        addDisplayComponent(titleLabel, mainFrame.panel);

        TextLabel textLabel = new TextLabel(text, font, Color.BLACK, new Dimension(40, 8), Align.LEFT);
        textLabel.coordinates = new Coordinates(58, 32);
        stageDetailsObjects.add(textLabel);
        addDisplayComponent(textLabel, mainFrame.panel);
    }

    private void removeStageDetails(MainFrame mainFrame) {
        for (DisplayComponent displayComponent : stageDetailsObjects)
            removeDisplayComponent(displayComponent, mainFrame.panel);
    }

    private static Player[] reverse(Player[] array) {
        Player[] newArray = new Player[array.length];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[array.length - i - 1];
        return newArray;
    }

    public static Active active(int stage) {
        if (stage < Progress.newestStage)
            return Active.UNLOCKED;
        else if (stage == Progress.newestStage)
            return Active.NEXT;
        return Active.LOCKED;
    }

    @Override
    public void setPreviousScreen(Screen screen) {
        if (screen instanceof CampaignScreen)
            super.setPreviousScreen(screen.getPreviousScreen());
        else
            super.setPreviousScreen(screen);
    }

}
