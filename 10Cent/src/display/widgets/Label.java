package display.widgets;

import display.frame.DisplayComponent;
import display.frame.DisplayObject;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;

import java.awt.*;

public class Label extends DisplayObject {

    // public final static double FONT_SIZE = 8;
    public final static String DEFAUZLT_FONT_STYLE = Font.MONOSPACED;

    public String text;
    public Font font;
    public Color color;
    private Dimension dimension;

    public Label(String text, Font font, Color color, Dimension dimension) {
        this.text = text;
        this.font = font;
        this.color = color;
        this.dimension = dimension;
    }

    public static double getHeight(Font font, Graphics g) {
        return g.getFontMetrics(font).getHeight();
    }

    public static double getWidth(String text, Font font, Graphics g) {
        return g.getFontMetrics(font).stringWidth(text);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        double newFontSize = font.getSize() * Math.min(scale.horizontal, scale.vertical);
        Font newFont = new Font(font.getFontName(), font.getStyle(), (int) newFontSize);
        g.setFont(newFont);
        g.setColor(color);

        double width = dimension.width * scale.horizontal;
        double height = dimension.height * scale.vertical;
        double x = coordinates.getX() + width / 2 - getWidth(text, newFont, g) / 2;
        double y = coordinates.getY() + height / 2 + getHeight(newFont, g) * 0.3;
        g.drawString(text, (int) x, (int) y);
    }

}
