package display.screens;

import display.frame.DisplayComponent;
import display.frame.DisplayObject;
import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.screens.story.DefeatScreen;
import display.screens.story.StoryRulesScreen;
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
import logic.players.bird.Bird;
import logic.players.crossBloody.CrossBloody;
import logic.players.crown.Crown;
import logic.players.heart.Heart;
import logic.players.nought.Nought;
import logic.players.noughtBloody.NoughtBloody;
import logic.players.scratch.Scratch;
import progress.Progress;
import progress.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static display.screens.MainMenuScreen.storyScreen;

public class CampaignScreen extends Screen {

    public static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 15);
    public static Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.PLAIN, 3);

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

        Waypoint thirdWaypoint = new Waypoint(active(Stage.STAGE3), new Dimension(10, 5)) {
            @Override
            protected void clickedUnlocked() {
                stageDetails(Stage.STAGE3, mainFrame);
            }

            @Override
            protected void clickedLocked() {

            }
        };
        thirdWaypoint.coordinates = new Coordinates(15, 55);
        addDisplayComponent(thirdWaypoint, mainFrame.panel);

        Waypoint fourthWaypoint = new Waypoint(active(Stage.STAGE4), new Dimension(10, 5)) {
            @Override
            protected void clickedUnlocked() {
                stageDetails(Stage.STAGE4, mainFrame);
            }

            @Override
            protected void clickedLocked() {

            }
        };
        fourthWaypoint.coordinates = new Coordinates(30, 25);
        addDisplayComponent(fourthWaypoint, mainFrame.panel);

        stageDetails(Progress.newestStage, mainFrame);

        addDefaultBackButton(mainFrame);
    }

    private void stageDetails(int stage, MainFrame mainFrame) {
        removeStageDetails(mainFrame);
        stageDescription(stage, mainFrame);

        if (stage >= Stage.THEEND)
            return;

        ToggleButton button1 = new ToggleButton("Easy", 5, new Dimension(20, 8));

        ToggleButton button2 = new ToggleButton("Hard", 5, new Dimension(20, 8));
        button2.coordinates = new Coordinates(20, 0);

        ToggleButton[] buttons = new ToggleButton[] {button1, button2};

        ToggleSwitch toggleSwitch = new ToggleSwitch(buttons) {
            @Override
            public void select(DisplayObject displayObject) {
                ((ToggleButton) displayObject).animateClicked();
                if (displayObject == button1)
                    Progress.easy = true;
                else
                    Progress.easy = false;
                super.select(displayObject);
            }

            @Override
            public void deselect(Coordinates coordinates) {
                ((ToggleButton) selectedObject).animateDefault();
                super.deselect(coordinates);
            }
        };
        toggleSwitch.select(Progress.easy ? button1 : button2);
        toggleSwitch.coordinates = new Coordinates(58, 78);
        stageDetailsObjects.add(toggleSwitch);
        addDisplayComponent(toggleSwitch, mainFrame.panel);

        NormalButton startButton = new NormalButton("Start", 5, new Dimension(20, 8)) {
            @Override
            public void clicked() {
                String game = null;
                Screen nextScreen = null;
                Player protagonist = Player.parsePlayer(Progress.selectedPlayer, new Human());
                Intelligence intelligence = new RandomAI();
                Player enemy;
                Player[] players;
                GameController gameController;
                Coordinates coordinates = new Coordinates(20, 20);
                Dimension dimension = new Dimension(60, 60);
                switch (stage) {
                    case Stage.STAGE1:
                        game = Games.TIC_TAC_TOE;
                        if (protagonist instanceof Nought)
                            enemy = new Heart(intelligence);
                        else
                            enemy = new Nought(intelligence);
                        players = new Player[] {protagonist, enemy};
                        if (toggleSwitch.getSelected() == button2)
                            players = reverse(players);
                        gameController = new StandardGameController(players) {
                            @Override
                            public void onWin(Player player) {
                                if (Progress.newestStage == stage)
                                    if (player == protagonist) {
                                        Progress.addPlayer(Nought.NAME);
                                        Progress.newestStage = Stage.STAGE2;
                                        Controller.switchScreenWithoutBacking(storyScreen());
                                    } else if (player == enemy) {
                                        Controller.switchScreenWithoutBacking(new DefeatScreen(stage));
                                    }
                            }
                        };
                        nextScreen = new GameScreen(gameController) {
                            @Override
                            public void loadEnvironment(MainFrame mainFrame) {

                            }

                            @Override
                            public void loadGame(MainFrame mainFrame) {
                                Games.ticTacToe(3, coordinates, dimension, gameController, this, mainFrame);
                            }
                        };
                        break;
                    case Stage.STAGE2:
                        game = Games.SUPER_TIC_TAC_TOE;
                        if (protagonist instanceof Bird)
                            enemy = new Heart(intelligence);
                        else
                            enemy = new Bird(intelligence);
                        players = new Player[] {protagonist, enemy};
                        if (toggleSwitch.getSelected() == button2)
                            players = reverse(players);
                        gameController = new StandardGameController(players) {
                            @Override
                            public void onWin(Player player) {
                                if (Progress.newestStage == stage)
                                    if (player == protagonist) {
                                        Progress.addPlayer(Bird.NAME);
                                        Progress.newestStage = Stage.STAGE3;
                                        Controller.switchScreenWithoutBacking(storyScreen());
                                    } else if (player == enemy) {
                                        Controller.switchScreenWithoutBacking(new DefeatScreen(stage));
                                    }
                            }
                        };
                        nextScreen = new GameScreen(gameController) {
                            @Override
                            public void loadEnvironment(MainFrame mainFrame) {

                            }

                            @Override
                            public void loadGame(MainFrame mainFrame) {
                                Games.ultimateTicTacToe(2, coordinates, dimension, gameController, this, mainFrame);
                            }
                        };
                        break;
                    case Stage.STAGE3:
                        game = Games.ULTIMATE_TIC_TAC_TOE;
                        if (protagonist instanceof Crown)
                            enemy = new Heart(intelligence);
                        else
                            enemy = new Crown(intelligence);
                        players = new Player[] {protagonist, enemy};
                        if (toggleSwitch.getSelected() == button2)
                            players = reverse(players);
                        gameController = new StandardGameController(players) {
                            @Override
                            public void onWin(Player player) {
                                if (Progress.newestStage == stage)
                                    if (player == protagonist ) {
                                        Progress.addPlayer(Crown.NAME);
                                        Progress.newestStage = Stage.STAGE4;
                                        Controller.switchScreenWithoutBacking(storyScreen());
                                    } else if (player == enemy){
                                        Controller.switchScreenWithoutBacking(new DefeatScreen(stage));
                                    }
                            }
                        };
                        nextScreen = new GameScreen(gameController) {
                            @Override
                            public void loadEnvironment(MainFrame mainFrame) {

                            }

                            @Override
                            public void loadGame(MainFrame mainFrame) {
                                Games.ultimateTicTacToe(3, coordinates, dimension, gameController, this, mainFrame);
                            }
                        };
                        break;
                    case Stage.STAGE4:
                        game = Games.ULTIMATE_TIC_TAC_TOE;
                        if (protagonist instanceof Scratch)
                            enemy = new Heart(intelligence);
                        else
                            enemy = new Scratch(intelligence);
                        players = new Player[] {protagonist, enemy};
                        if (toggleSwitch.getSelected() == button2)
                            players = reverse(players);
                        gameController = new StandardGameController(players) {
                            @Override
                            public void onWin(Player player) {
                                if (Progress.newestStage == stage)
                                    if (player == protagonist) {
                                        Progress.addPlayer(Scratch.NAME);
                                        Progress.newestStage = Stage.THEEND;
                                        Controller.switchScreenWithoutBacking(storyScreen());
                                    } else if (player == enemy) {
                                        Controller.switchScreenWithoutBacking(new DefeatScreen(stage));
                                    }
                            }
                        };
                        nextScreen = new GameScreen(gameController) {
                            @Override
                            public void loadEnvironment(MainFrame mainFrame) {

                            }

                            @Override
                            public void loadGame(MainFrame mainFrame) {
                                Games.ultimateTicTacToe(3, coordinates, dimension, gameController, this, mainFrame);
                            }
                        };
                        break;
                }
                if (!Progress.isPlayed(game))
                    Controller.switchScreen(new StoryRulesScreen(game, nextScreen));
                else
                    Controller.switchScreen(nextScreen);
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
                title = "Gateway";

                text = "Stubborn guard blocks your way at the entrance to the capital. He might seem " +
                        "like your average Joe, but this guard takes pride in his knowledge of the " +
                        "ordinary tic-tac-toe. Defeating him will be no easy task.";
                break;
            case Stage.STAGE2:
                title = "Encounter";

                text = "Genius wizard sneaks behind you. Perfect - a test subject. He has crafted " +
                        "his own variation of tic-tac-toe, even deeper, even harder to master. You are " +
                        "unprepared and he well versed in this game. Can you make your way out of this pinch?";
                break;
            case Stage.STAGE3:
                title = "Throne Room";

                text = "Insane aura fills the room. King sits leisurely on his throne almost as he was waiting " +
                        "for you. He smiles. You know that you might not be able to win, but still you step forward. " +
                        "All the games you have played, tokens you've acquired - time for final showdown has come.";
                break;
            case Stage.STAGE4:
                title = "Monster";

                text = "Late at night roars can be heard from the top of the mountain. The citizens have asked you, " +
                        "as the world's best tic-tac-toe player, to find the truth to them. Ambiguous feeling " +
                        "overwhelms you when entering dark cave near the mountain peak. You become certain that what hides " +
                        "here is not human.";
                break;
            case Stage.THEEND:
                title = "The End";

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
