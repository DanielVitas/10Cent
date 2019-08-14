package display.screens;

import audio.AudioPlayer;
import audio.Music;
import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.input.NumberFieldInput;
import display.widgets.label.Align;
import display.widgets.label.Label;
import display.widgets.buttons.NormalButton;
import display.widgets.dropdownMenu.DropdownMenu;
import display.widgets.dropdownMenu.NormalDropdownMenu;
import display.widgets.dropdownMenu.PlayerDropdownMenu;
import logic.game.GameController;
import logic.game.StandardGameController;
import logic.intelligence.Human;
import logic.intelligence.Intelligence;
import logic.intelligence.MiniMax;
import logic.intelligence.RandomAI;
import logic.players.Player;
import progress.Progress;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PracticeScreen extends Screen {

    /*
    PracticeScreen is used to begin custom games.
     */

    private static Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.PLAIN, 3);

    private NormalDropdownMenu gameModeMenu;
    private NormalDropdownMenu intelligence1Menu;
    private NormalDropdownMenu intelligence2Menu;
    private PlayerDropdownMenu player1Menu;
    private PlayerDropdownMenu player2Menu;

    private boolean showDepthInput1 = false;
    private NumberFieldInput depthInput1;
    private boolean showDepthInput2 = false;
    private NumberFieldInput depthInput2;

    private DropdownMenu[] dropdownMenus;

    public PracticeScreen(MainFrame mainFrame) {
        // creates various dropdown menus - this is necessary due to them having somewhat complicated structure
        String[] games = new String[]{Games.TIC_TAC_TOE, Games.SUPER_TIC_TAC_TOE, Games.ULTIMATE_TIC_TAC_TOE};

        gameModeMenu = new NormalDropdownMenu(games, 3, new Dimension(40,8)) {
            @Override
            public void dropdown() {
                super.dropdown();
                closeOther(this);
            }
        };
        gameModeMenu.coordinates = new Coordinates(30, 16);
        gameModeMenu.displayPriority = 5;

        String[] allIntelligence = new String[]{Human.NAME, RandomAI.NAME, MiniMax.NAME};

        intelligence1Menu = new NormalDropdownMenu(allIntelligence, 3, new Dimension(30,8)) {
            @Override
            public void dropdown() {
                super.dropdown();
                closeOther(this);
            }

            @Override
            public void backup() {
                super.backup();
                showDepthInput1 = getValue().equals(MiniMax.NAME);
                if (active) {
                    if (showDepthInput1)
                        addDisplayComponent(depthInput1, mainFrame.panel);
                    else
                        removeDisplayComponent(depthInput1, mainFrame.panel);
                }
            }
        };
        intelligence1Menu.coordinates = new Coordinates(30, 36);
        intelligence1Menu.displayPriority = 3;

        intelligence2Menu = new NormalDropdownMenu(allIntelligence, 3, new Dimension(30,8)) {
            @Override
            public void dropdown() {
                super.dropdown();
                closeOther(this);
            }

            @Override
            public void backup() {
                super.backup();
                showDepthInput2 = getValue().equals(MiniMax.NAME);
                if (active) {
                    if (showDepthInput2)
                        addDisplayComponent(depthInput2, mainFrame.panel);
                    else
                        removeDisplayComponent(depthInput2, mainFrame.panel);
                }
            }
        };
        intelligence2Menu.coordinates = new Coordinates(30, 46);
        intelligence2Menu.displayPriority = 1;

        Player[] players = Progress.getAllPlayers();
        List<String> oldPlayers = new ArrayList<>();
        for (Player player : players)
            oldPlayers.add(player.toString());

        player1Menu = new PlayerDropdownMenu(players, false, oldPlayers, Align.LEFT, 3, new display.frame.misc.Dimension(8,8)) {
            @Override
            public void dropdown() {
                super.dropdown();
                closeOther(this);
            }
        };
        player1Menu.coordinates = new Coordinates(62,36);
        player1Menu.displayPriority = 4;

        player2Menu = new PlayerDropdownMenu(players, false, oldPlayers, Align.LEFT, 3, new display.frame.misc.Dimension(8,8)) {
            @Override
            public void dropdown() {
                super.dropdown();
                closeOther(this);
            }
        };
        player2Menu.coordinates = new Coordinates(62,46);
        player2Menu.displayPriority = 2;
        player2Menu.setIndex(1);

        dropdownMenus = new DropdownMenu[] {
                gameModeMenu, intelligence1Menu, intelligence2Menu, player1Menu, player2Menu
        };

        depthInput1 = new NumberFieldInput(2, "Depth", Align.LEFT, new Dimension(15, 8));
        depthInput1.coordinates = new Coordinates(75,36);

        depthInput2 = new NumberFieldInput(2, "Depth", Align.LEFT, new Dimension(15, 8));
        depthInput2.coordinates = new Coordinates(75,46);
    }

    // when another dropdown menu is opened, every other is closed
    private void closeOther(DropdownMenu menu) {
        for (DropdownMenu dropdownMenu : dropdownMenus)
            if (dropdownMenu != menu)
                dropdownMenu.backup();
    }

    @Override
    public void load(MainFrame mainFrame) {
        AudioPlayer.play(Music.PRACTICE);

        for (DropdownMenu menu : dropdownMenus)
            addDisplayComponent(menu, mainFrame.panel);

        if (showDepthInput1)
            addDisplayComponent(depthInput1, mainFrame.panel);

        if (showDepthInput2)
            addDisplayComponent(depthInput2, mainFrame.panel);

        Label gameModeLabel = new Label("Game Mode", font, Color.BLACK, new display.frame.misc.Dimension(25,8), Align.LEFT);
        gameModeLabel.coordinates = new Coordinates(5,16);
        addDisplayComponent(gameModeLabel, mainFrame.panel);

        Label player1Label = new Label("Player 1", font, Color.BLACK, new display.frame.misc.Dimension(25,8), Align.LEFT);
        player1Label.coordinates = new Coordinates(5,36);
        addDisplayComponent(player1Label, mainFrame.panel);

        Label player2Label = new Label("Player 2", font, Color.BLACK, new display.frame.misc.Dimension(25,8), Align.LEFT);
        player2Label.coordinates = new Coordinates(5,46);
        addDisplayComponent(player2Label, mainFrame.panel);

        NormalButton startButton = new NormalButton("Start", 5, new Dimension(20, 8)) {
            @Override
            public void clicked() {
                Intelligence intelligence1;
                if (showDepthInput1)
                    if (!depthInput1.isValid())
                        return;
                    else
                        intelligence1 = Intelligence.parseIntelligence(intelligence1Menu.getValue(), depthInput1.getNumber());
                else
                    intelligence1 = Intelligence.parseIntelligence(intelligence1Menu.getValue());

                Intelligence intelligence2;
                if (showDepthInput2)
                    if (!depthInput2.isValid())
                        return;
                    else
                        intelligence2 = Intelligence.parseIntelligence(intelligence2Menu.getValue(), depthInput2.getNumber());
                else
                    intelligence2 = Intelligence.parseIntelligence(intelligence2Menu.getValue());

                Player player1 = Player.parsePlayer(player1Menu.getValue(), intelligence1);
                Player player2 = Player.parsePlayer(player2Menu.getValue(), intelligence2);
                Player[] players = new Player[] {player1, player2};

                GameController gameController;
                Coordinates coordinates = new Coordinates(20, 20);
                Dimension dimension = new Dimension(60, 60);
                switch (gameModeMenu.getValue()) {
                    case Games.TIC_TAC_TOE:
                        gameController = new StandardGameController(players) {
                            @Override
                            public void onWin(Player player) {

                            }
                        };
                        Controller.switchScreen(new GameScreen(gameController) {
                            @Override
                            public void loadEnvironment(MainFrame mainFrame) {

                            }

                            @Override
                            public void loadGame(MainFrame mainFrame) {
                                Games.ticTacToe(3, coordinates, dimension, gameController, this, mainFrame);
                            }
                        });
                        break;
                    case Games.SUPER_TIC_TAC_TOE:
                        gameController = new StandardGameController(players) {
                            @Override
                            public void onWin(Player player) {

                            }
                        };
                        Controller.switchScreen(new GameScreen(gameController) {
                            @Override
                            public void loadEnvironment(MainFrame mainFrame) {

                            }

                            @Override
                            public void loadGame(MainFrame mainFrame) {
                                Games.ultimateTicTacToe(2, coordinates, dimension, gameController, this, mainFrame);
                            }
                        });
                        break;
                    case Games.ULTIMATE_TIC_TAC_TOE:
                        gameController = new StandardGameController(players) {
                            @Override
                            public void onWin(Player player) {

                            }
                        };
                        Controller.switchScreen(new GameScreen(gameController) {
                            @Override
                            public void loadEnvironment(MainFrame mainFrame) {

                            }

                            @Override
                            public void loadGame(MainFrame mainFrame) {
                                Games.ultimateTicTacToe(3, coordinates, dimension, gameController, this, mainFrame);
                            }
                        });
                        break;
                }
            }
        };
        startButton.coordinates = new Coordinates(78,90);
        addDisplayComponent(startButton, mainFrame.panel);

        NormalButton rulebookButton = new NormalButton("Rulebook", 5, new Dimension(30, 8)) {
            @Override
            public void clicked() {
                Controller.switchScreen(new RulebookScreen(gameModeMenu.getValue()));
            }
        };
        rulebookButton.coordinates = new Coordinates(2,90);
        addDisplayComponent(rulebookButton, mainFrame.panel);

        addDefaultBackButton(mainFrame);
    }

    @Override
    public void unload(MainFrame mainFrame) {
        super.unload(mainFrame);
        closeOther(null);
    }

}
