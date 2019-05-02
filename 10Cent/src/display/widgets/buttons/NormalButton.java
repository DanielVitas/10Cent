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

public abstract class NormalButton extends Button {

    public Label label;

    public NormalButton(String text, Dimension dimension) {
        super(dimension, Paths.get(Images.RESOURCES_PATH,"images", "buttons", "normal").toString());
        Font font = new Font("Courier", Font.PLAIN,  (int) (dimension.width * Label.FONT_SIZE / text.length()));
        label = new Label(text, font, Color.BLACK);
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

        // border
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.black);
        g2.setStroke(new BasicStroke((float) (0.2 * scale.average())));

        Coordinates startCoordinates = coordinates;
        Coordinates endCoordinates = coordinates.add(new Coordinates(dimension.width, 0).scale(scale));
        drawLine(startCoordinates, endCoordinates, g);

        endCoordinates = coordinates.add(new Coordinates(0, dimension.height).scale(scale));
        drawLine(startCoordinates, endCoordinates, g);

        startCoordinates = coordinates.add(new Coordinates(dimension.width, dimension.height).scale(scale));
        drawLine(startCoordinates, endCoordinates, g);

        endCoordinates = coordinates.add(new Coordinates(dimension.width, 0).scale(scale));
        drawLine(startCoordinates, endCoordinates, g);

    }

}
