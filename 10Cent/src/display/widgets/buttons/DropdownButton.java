package display.widgets.buttons;

import audio.AudioPlayer;
import audio.SoundPlayer;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Images;
import display.widgets.label.Align;
import display.widgets.label.Label;
import display.widgets.dropdownMenu.DropdownMenu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

import static display.frame.MainPanel.drawRectangle;

public abstract class DropdownButton extends Button {

    public Label label;
    private DropdownMenu dropdownMenu;

    public DropdownButton(DropdownMenu dropdownMenu, String text, int fontSize, Dimension dimension) {
        super(dimension, Paths.get(Images.RESOURCES_PATH,"images", "buttons", "dropdown").toString());

        this.dropdownMenu = dropdownMenu;

        Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.BOLD, fontSize);
        label = new Label(text, font, Color.BLACK, new Dimension(dimension.width - 2, dimension.height), Align.LEFT);
        label.coordinates = new Coordinates(1,0);
        label.displayPriority = 1;
        addSubComponent(label);

        hitBoxes.add(new Rectangle(dimension.getAwtDimension()));
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
