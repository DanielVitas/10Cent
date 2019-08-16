package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Scale;

import java.awt.event.MouseEvent;

public final class Mouse {

    /*
    Used to store hovered DisplayComponent and whether the mouse is pressed.
     */

    public static boolean pressed = false;
    public static DisplayComponent hovered;

    static Coordinates getCoordinates(MouseEvent mouseEvent) {
        Scale scale = MainFrame.getScale();
        double x = mouseEvent.getX() / scale.horizontal;
        double y = mouseEvent.getY() / scale.vertical;
        return new Coordinates(x, y);
    }

}
