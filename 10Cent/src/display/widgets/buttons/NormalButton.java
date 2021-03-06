package display.widgets.buttons;

import audio.AudioPlayer;
import audio.Sound;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Images;
import display.widgets.label.Align;
import display.widgets.label.Label;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

public abstract class NormalButton extends Button {

    /*
    Normal button is the most basic and common button.
     */

    public Label label;

    protected NormalButton(String text, int fontSize, Dimension dimension) {
        super(dimension, Paths.get(Images.RESOURCES_PATH,"images", "buttons", "normal").toString());

        Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.BOLD, fontSize);
        label = new Label(text, font, Color.BLACK, new Dimension(dimension.width - 2, dimension.height), Align.CENTER);
        label.coordinates = new Coordinates(1,0);
        label.displayPriority = 1;
        addSubComponent(label);

        hitBoxes.add(new Rectangle(dimension.getAwtDimension()));
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        AudioPlayer.play(Sound.BUTTON);
        super.click(coordinates, scale, mouseEvent);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        drawBoarder(0.3, coordinates, dimension, scale, g);
    }

}
