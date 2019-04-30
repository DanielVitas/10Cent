package display.widgets;

import display.frame.DisplayComponent;
import display.frame.DisplayObject;
import display.frame.misc.Coordinates;
import display.frame.misc.Scale;

import java.awt.*;

public class Label extends DisplayObject {

    public final static double FONT_SIZE = 10;

    public String text;
    public Font font;
    public Color color;

    public Label(String text, Font font, Color color) {
        this.text = text;
        this.font = font;
        this.color = color;
    }

    public double getHeight(Graphics g) {
        return g.getFontMetrics(font).getHeight();
    }

    public double getWidth(Graphics g) {
        return g.getFontMetrics(font).stringWidth(text);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        g.setFont(font);
        g.setColor(color);
        g.drawString(text, coordinates.getIntegerX(), coordinates.getIntegerY() + (int) (getHeight(g) * 0.7));
    }

}
