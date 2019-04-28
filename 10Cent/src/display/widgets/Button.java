package display.widgets;

import display.frame.DisplayComponent;
import display.frame.misc.Coordinates;
import display.frame.DisplayObject;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Button extends JButton implements DisplayComponent {

    private Dimension dimension;

    public Button(String text, Dimension dimension) {
        super();
        setText(text);
        setPreferredSize(dimension.getAwtDimension());
        setBounds(0, 0, (int) dimension.width, (int) dimension.height);
        enable(true);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(g);
    }

    @Override
    public int getDisplayPriority() {
        return 0;
    }

    @Override
    public Coordinates getCoordinates() {
        return new Coordinates(0, 0);
    }
}
