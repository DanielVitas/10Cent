package display.widgets.buttons;

import display.frame.misc.Dimension;
import display.images.Images;
import display.widgets.Label;

import java.awt.*;
import java.nio.file.Paths;

public abstract class NormalButton extends Button {

    public Label label;

    public NormalButton(String text, Dimension dimension) {
        super(dimension, Paths.get(Images.resourcesPath,"images", "buttons", "normal").toString());
        Font font = new Font("Courier", Font.PLAIN,  (int) (dimension.width * Label.FONT_SIZE / text.length()));
        label = new Label(text, font, Color.BLACK);
        label.displayPriority = 1;
        addSubComponent(label);
    }

}
