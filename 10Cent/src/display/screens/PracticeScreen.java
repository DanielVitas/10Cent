package display.screens;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
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

public class PracticeScreen extends Screen {

    private static Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.PLAIN, 3);
    private static Font largeFont = new Font(Label.DEFAULT_FONT_STYLE, Font.BOLD, 5);
    private NormalDropdownMenu gameModeMenu;
    private NormalDropdownMenu intelligence1Menu;
    private NormalDropdownMenu intelligence2Menu;
    private PlayerDropdownMenu player1Menu;
    private PlayerDropdownMenu player2Menu;

    private DropdownMenu[] dropdownMenus;

    public PracticeScreen() {
        gameModeMenu = new NormalDropdownMenu(new String[]{"Tic-tac-toe", "Ultimate tic-tac-toe"}, 3, new Dimension(40,8)) {
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
        };
        intelligence1Menu.coordinates = new Coordinates(30, 36);
        intelligence1Menu.displayPriority = 3;

        intelligence2Menu = new NormalDropdownMenu(allIntelligence, 3, new Dimension(30,8)) {
            @Override
            public void dropdown() {
                super.dropdown();
                closeOther(this);
            }
        };
        intelligence2Menu.coordinates = new Coordinates(30, 46);
        intelligence2Menu.displayPriority = 1;

        Player[] players = Progress.getAllPlayers();

        player1Menu = new PlayerDropdownMenu(players, Align.LEFT, 3, new display.frame.misc.Dimension(8,8)) {
            @Override
            public void dropdown() {
                super.dropdown();
                closeOther(this);
            }
        };
        player1Menu.coordinates = new Coordinates(62,36);
        player1Menu.displayPriority = 4;

        player2Menu = new PlayerDropdownMenu(players, Align.LEFT, 3, new display.frame.misc.Dimension(8,8)) {
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
    }

    public void closeOther(DropdownMenu menu) {
        for (DropdownMenu dropdownMenu : dropdownMenus)
            if (dropdownMenu != menu)
                dropdownMenu.backup();
    }

    @Override
    public void load(MainFrame mainFrame) {
        Label gameModeLabel = new Label("Game Mode", font, Color.BLACK, new display.frame.misc.Dimension(25,8), Align.LEFT);
        gameModeLabel.coordinates = new Coordinates(5,16);
        addDisplayComponent(gameModeLabel, mainFrame.panel);

        addDisplayComponent(gameModeMenu, mainFrame.panel);

        Label player1Label = new Label("Player 1", font, Color.BLACK, new display.frame.misc.Dimension(25,8), Align.LEFT);
        player1Label.coordinates = new Coordinates(5,36);
        addDisplayComponent(player1Label, mainFrame.panel);

        addDisplayComponent(player1Menu, mainFrame.panel);
        addDisplayComponent(intelligence1Menu, mainFrame.panel);

        Label player2Label = new Label("Player 2", font, Color.BLACK, new display.frame.misc.Dimension(25,8), Align.LEFT);
        player2Label.coordinates = new Coordinates(5,46);
        addDisplayComponent(player2Label, mainFrame.panel);

        addDisplayComponent(player2Menu, mainFrame.panel);
        addDisplayComponent(intelligence2Menu, mainFrame.panel);

        NormalButton startButton = new NormalButton("Start", 5, new Dimension(20, 8)) {
            @Override
            public void clicked() {
                Intelligence intelligence1 = Intelligence.parseString(intelligence1Menu.getValue());
                Intelligence intelligence2 = Intelligence.parseString(intelligence2Menu.getValue());
                Player player1 = Player.parseString(player1Menu.getValue(), intelligence1);
                Player player2 = Player.parseString(player2Menu.getValue(), intelligence2);
                Player[] players = new Player[] {player1, player2};
                GameController gameController;
                switch (gameModeMenu.getValue()) {
                    case "Tic-tac-toe":
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
                                Games.ticTacToe(3, new Coordinates(20, 20),
                                        new Dimension(60, 60), gameController,
                                        this, mainFrame);
                            }
                        });
                        break;
                    case "Ultimate tic-tac-toe":
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
                                Games.ultimateTicTacToe(3, new Coordinates(20, 20),
                                        new Dimension(60, 60), gameController,
                                        this, mainFrame);
                            }
                        });
                        break;
                }
            }
        };
        startButton.coordinates = new Coordinates(78,90);
        addDisplayComponent(startButton, mainFrame.panel);

        addDefaultBackButton(mainFrame);
    }

    @Override
    public void unload(MainFrame mainFrame) {
        super.unload(mainFrame);
        closeOther(null);
    }

}
