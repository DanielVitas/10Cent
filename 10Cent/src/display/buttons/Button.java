package display.buttons;

import display.frame.misc.Coordinates;
import display.frame.DisplayObject;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Button extends DisplayObject {

    private Dimension dimension;

    public Button(Dimension dimension) {
        this.dimension = dimension;
        hitBoxes.add(new Rectangle(dimension));
    }

    @Override
    public void clicked(Coordinates coordinates, MouseEvent mouseEvent) {

    }

}
