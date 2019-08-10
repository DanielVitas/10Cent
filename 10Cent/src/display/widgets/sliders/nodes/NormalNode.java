package display.widgets.sliders.nodes;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Animation;
import display.images.Images;
import display.widgets.sliders.Slider;

import java.awt.*;
import java.nio.file.Paths;

import static display.frame.MainPanel.drawRectangle;

public class NormalNode extends Node {

    /*
    Shaped like
     */

    private String directoryPath = Paths.get(Images.RESOURCES_PATH, "images", "sliders", "nodes", "normal").toString();

    public NormalNode(Slider slider, Dimension dimension) {
        super(slider);

        this.dimension = dimension;
        hitBoxes.add(new Rectangle(dimension.getAwtDimension()));

        String path = Paths.get(directoryPath, "default").toString();
        Animation animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("default", animation);
        animate("default");
    }


    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke((float) (0.2 * scale.average())));

        drawRectangle(coordinates, dimension.scale(scale), g);
    }

}
