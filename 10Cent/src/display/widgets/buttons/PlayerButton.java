package display.widgets.buttons;

import audio.AudioPlayer;
import audio.SoundPlayer;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Images;
import display.widgets.dropdownMenu.DropdownMenu;
import logic.players.Player;
import logic.players.Token;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

import static display.frame.MainPanel.drawRectangle;

public abstract class PlayerButton extends Button {

    private DropdownMenu dropdownMenu;
    private Player player;
    private Token token;

    public PlayerButton(DropdownMenu dropdownMenu, Player player, Dimension dimension) {
        super(dimension, Paths.get(Images.RESOURCES_PATH,"images", "buttons", "player").toString());

        this.dropdownMenu = dropdownMenu;
        setPlayer(player);

        hitBoxes.add(new Rectangle(dimension.getAwtDimension()));
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
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        AudioPlayer.play(SoundPlayer.BUTTON);
        super.click(coordinates, scale, mouseEvent);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke((float) (0.1 * scale.average())));

        drawRectangle(coordinates, dimension.scale(scale), g);
    }

}
