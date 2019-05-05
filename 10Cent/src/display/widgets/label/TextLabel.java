package display.widgets.label;

import display.frame.DisplayObject;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextLabel extends DisplayObject {

    /*
    Used for multiple lines of text.
     */

    public String text;
    public Font font;
    public Color color;
    private display.frame.misc.Dimension dimension;
    private Align align;
    private List<Label> labels = new ArrayList<>();

    public TextLabel(String text, Font font, Color color, Dimension dimension, Align align) {
        this.text = text;
        this.font = font;
        this.color = color;
        this.dimension = dimension;
        this.align = align;
    }

    private List<String> sliceUp(String[] words, List<String> lines, Font font, Dimension dimension, Graphics g) {
        if (words.length == 0)
            return lines;
        String line = words[0];
        for (int i = 1; i < words.length; i++) {
            double a = getWidth(line + " " + words[i], font, g);
            if (getWidth(line + " " + words[i], font, g) > dimension.width) {
                lines.add(line);
                return sliceUp(Arrays.copyOfRange(words, i, words.length), lines, font, dimension, g);
            } else {
                line += " " + words[i];
            }
        }
        lines.add(line);
        return sliceUp(new String[]{}, lines, font, dimension, g);
    }

    public static double getHeight(Font font, Graphics g) {
        return g.getFontMetrics(font).getHeight();
    }

    public static double getWidth(String text, Font font, Graphics g) {
        return g.getFontMetrics(font).stringWidth(text);
//        AffineTransform affinetransform = new AffineTransform();
//        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
//        return (font.getStringBounds(text, frc).getWidth());
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        for (Label label : labels)
            removeSubComponent(label);
        labels = new ArrayList<>();
        String[] potentialLines = text.split("\\n");
        double y = 0;
        for (int j = 0; j < potentialLines.length; j++) {
            List<String> lines = sliceUp(potentialLines[j].split("\\s"), new ArrayList<>(), Label.scaleFont(font, scale), dimension.scale(scale), g);
            for (int i = 0; i < lines.size(); i++) {
                Label label = new Label(lines.get(i), font, color, dimension, align);
                label.coordinates = new Coordinates(0, y);
                y += getHeight(font, g);
                addSubComponent(label);
                labels.add(label);
            }
        }
        super.paint(coordinates, scale, g);
    }

}
