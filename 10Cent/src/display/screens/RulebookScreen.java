package display.screens;

import display.frame.DisplayComponent;
import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.label.Align;
import display.widgets.label.Label;
import display.widgets.label.TextLabel;
import fonts.CustomFonts;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RulebookScreen extends Screen {

    /*
    Screen for displaying rules of games.
     */

    public static Font titleFont = CustomFonts.getFont(CustomFonts.CALLIGRAPHY, 15);
    public static Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.PLAIN, 3);

    private String game;
    private List<DisplayComponent> temporaryComponents = new ArrayList<>();

    RulebookScreen(String game) {
        this.game = game;
    }

    @Override
    public void load(MainFrame mainFrame) {
        change(game, mainFrame);
    }

    private void change(String game, MainFrame mainFrame) {
        for (DisplayComponent displayComponent : temporaryComponents)
            removeDisplayComponent(displayComponent, mainFrame.panel);

        Label label = new Label(game, titleFont, Color.BLACK, new Dimension(80, 8), Align.CENTER);
        label.coordinates = new Coordinates(10, 10);
        temporaryComponents.add(label);
        addDisplayComponent(label, mainFrame.panel);

        temporaryComponents.addAll(rules(game, new Coordinates(10, 25), new Dimension(80, 8), this, mainFrame));

        addDefaultBackButton(mainFrame);
    }

    public static List<DisplayComponent> rules(String game, Coordinates coordinates, Dimension dimension, Screen screen, MainFrame mainFrame) {
        List<DisplayComponent> temporaryComponents = new ArrayList<>();

        String text = null;
        switch (game) {
            case Games.TIC_TAC_TOE:
                text = "Two players exchange turns playing their tokens on the 3x3 board. You cannot play token " +
                        "in a slot any token is already at. First player to place 3 tokens in any row (either horizontally, " +
                        "vertically or diagonally) wins the game.";
                break;
            case Games.SUPER_TIC_TAC_TOE:
                text = "Two players exchange turns placing their tokens on the " +
                        "4x4 board. This board consists of 4 smaller 2x2 boards. Fist player to win any 2 of the smaller " +
                        "boards wins the game. Players can win each of the smaller " +
                        "boards by placing 2 tokens anywhere on them. When one player makes a move in " +
                        "a smaller board that determines where in the larger board other player must play. If that " +
                        "board is already won player can play anywhere.\n\n" +
                        "For example, if the first player plays in the most top-right slot, he has played on top-right " +
                        "smaller board on its top-right slot. The next player must also play on top-right smaller board, but " +
                        "he can choose the slot on it freely. Let's say he played the bottom-right, then first player " +
                        "must now make a move on bottom-right smaller board. Et cetera.";
                break;
            case Games.ULTIMATE_TIC_TAC_TOE:
                text = Games.ULTIMATE_TIC_TAC_TOE + " is the combination between ordinary tic-tac-toe and " + Games.SUPER_TIC_TAC_TOE + ". " +
                        "Players exchange turns placing their tokens on the 9x9 board. This board consists of 9 smaller 3x3 " +
                        "boards. Player can win each of the smaller boards by placing 3 tokens in a row (either horizontally, " +
                        "vertically or diagonally). First payer to win 3 smaller boards in any row (either horizontally, " +
                        "vertically or diagonally) wins the game. (When one player makes a move in a smaller board that determines where in " +
                        "the larger board other player must play. If that smaller board is either won or full (undecided) " +
                        "player can move anywhere.\n\n" +
                        "For example, if the first player plays in the most top-right slot, he has played on top-right " +
                        "smaller board on its top-right slot. The next player must also play on top-right smaller board, but " +
                        "he can choose the slot on it freely. Let's say he played the center, then first player " +
                        "must now make a move on center smaller board. Et cetera.";
                break;
        }
        TextLabel textLabel = new TextLabel(text, font, Color.BLACK, new Dimension(dimension.width, 0.08 * dimension.height), Align.LEFT);
        textLabel.coordinates = coordinates;
        temporaryComponents.add(textLabel);
        screen.addDisplayComponent(textLabel, mainFrame.panel);

        return temporaryComponents;
    }

}
