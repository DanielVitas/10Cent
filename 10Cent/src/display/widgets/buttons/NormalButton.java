package display.widgets.buttons;

import audio.AudioPlayer;
import audio.SoundPlayer;
import display.frame.DisplayComponent;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Images;
import display.widgets.Label;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

import static display.frame.MainPanel.drawLine;
import static display.frame.MainPanel.drawRectangle;

public abstract class NormalButton extends Button {

    public Label label;

    public NormalButton(String text, int fontSize, Dimension dimension) {
        super(dimension, Paths.get(Images.RESOURCES_PATH,"images", "buttons", "normal").toString());
        Font font = new Font(Label.DEFAUZLT_FONT_STYLE, Font.PLAIN, fontSize);
        label = new Label(text, font, Color.BLACK, dimension);
        label.displayPriority = 1;
        addSubComponent(label);
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        AudioPlayer.play(SoundPlayer.BUTTON);
        super.click(coordinates, scale, mouseEvent);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);
        drawRectangle(coordinates, dimension, Color.black, 0.3, scale, g);
    }

}
