package display.widgets.label;

import display.frame.DisplayObject;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;

import java.awt.*;

public class Label extends DisplayObject {

    /*
    Labels are the most basic methods of displaying text - used only for single lines.
     */

    public final static String DEFAULT_FONT_STYLE = Font.SANS_SERIF;

    public String text;
    public Font font;
    public Color color;
    private Dimension dimension;
    private Align align;

    public Label(String text, Font font, Color color, Dimension dimension, Align align) {
        this.text = text;
        this.font = font;
        this.color = color;
        this.dimension = dimension;
        this.align = align;
    }

    private static double getHeight(Font font, Graphics g) {
        return g.getFontMetrics(font).getHeight();
    }

    private static double getWidth(String text, Font font, Graphics g) {
        return g.getFontMetrics(font).stringWidth(text);
    }

    static Font scaleFont(Font font, Scale scale) {
        double newFontSize = font.getSize() * Math.min(scale.horizontal, scale.vertical);
        return font.deriveFont((float) newFontSize);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        Font newFont = scaleFont(font, scale);
        g.setFont(newFont);
        g.setColor(color);

        double width = dimension.width * scale.horizontal;
        double height = dimension.height * scale.vertical;
        double x = coordinates.getX();
        switch (align) {
            case LEFT:
                x = coordinates.getX();
                break;
            case CENTER:
                x = coordinates.getX() + width / 2 - getWidth(text, newFont, g) / 2;
                break;
            case RIGHT:
                x = coordinates.getX() + width - getWidth(text, newFont, g);
                break;
        }
        double y = coordinates.getY() + height / 2 + getHeight(newFont, g) * 0.2;
        Coordinates newCoordinates = new Coordinates(x, y);
        g.drawString(text, newCoordinates.getIntegerX(), newCoordinates.getIntegerY());
    }

}
