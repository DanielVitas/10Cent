package display.widgets.buttons;

import audio.AudioPlayer;
import audio.Sound;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Images;
import display.widgets.dropdownMenu.DropdownMenu;
import display.widgets.label.Align;
import display.widgets.label.Label;
import fonts.CustomFonts;
import logic.players.Player;
import logic.players.Token;
import progress.Progress;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

public abstract class PlayerButton extends Button {

    /*
    PlayerButton is clickable image of token. It can also display "new" tag. It's part of PlayerDropdownMenu.
     */

    private static Font font = CustomFonts.getFont(CustomFonts.PAINTER, 3);

    private DropdownMenu dropdownMenu;
    public Player player;
    private Token token;
    public Label newLabel;
    private boolean changeable;
    public boolean suppressLabel = false;  // use is evident in PlayerDropdownMenu
    private boolean seeable;  // if hovering over it adds the player to seen players

    protected PlayerButton(DropdownMenu dropdownMenu, boolean seeable, boolean isNew, boolean changeable, Player player, Dimension dimension) {
        super(dimension, Paths.get(Images.RESOURCES_PATH,"images", "buttons", "player").toString());

        this.dropdownMenu = dropdownMenu;
        this.changeable = changeable;
        this.seeable = seeable;
        setPlayer(player);

        if (isNew) {
            Label label = new Label("New<", font, Color.RED, new Dimension(dimension.width * 0.5, dimension.height * 0.2), Align.RIGHT);
            label.coordinates = new Coordinates(dimension.width * 0.5, dimension.height * 0.8);
            newLabel = label;
        }

        hitBoxes.add(new Rectangle(dimension.getAwtDimension()));
    }

    public void seen() {
        newLabel = null;
    }

    public void setPlayer(Player player) {
        if (token != null)
            removeSubComponent(token);
        this.player = player;
        token = player.newToken(null, null, dimension.scale(new Scale(0.9, 0.9)));
        token.coordinates = new Coordinates(dimension.width * 0.05, dimension.height * 0.05);
        token.displayPriority = 1;
        addSubComponent(token);
        token.animateDefault();
    }

    @Override
    public void animateClicked() {
        if (dropdownMenu.hoveredObject == this)
            animateHovered();
        else
            animateDefault();
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        super.hover(coordinates, scale, mouseEvent);
        if (changeable) {
            seen();
            if (seeable)
            	Progress.addOldPlayer(player.toString());
        }
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        AudioPlayer.play(Sound.BUTTON);
        super.click(coordinates, scale, mouseEvent);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        drawBoarder(0.1, coordinates, dimension, scale, g);

        if (newLabel != null && !suppressLabel)
            newLabel.paint(coordinates.add(newLabel.coordinates.scale(scale)), scale, g);
    }

}
