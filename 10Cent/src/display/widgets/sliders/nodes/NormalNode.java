package display.widgets.sliders.nodes;

import display.frame.misc.Dimension;
import display.images.Animation;
import display.images.Images;
import display.widgets.sliders.Slider;

import java.awt.*;
import java.nio.file.Paths;

public class NormalNode extends Node {

    private String directoryPath = Paths.get(Images.resourcesPath, "images", "sliders", "nodes", "normal").toString();

    public NormalNode(Slider slider, Dimension dimension) {
        super(slider);
        hitBoxes.add(new Rectangle(dimension.getAwtDimension()));

        String path = Paths.get(directoryPath, "default").toString();
        Animation animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("default", animation);
        animate("default");
    }

}
