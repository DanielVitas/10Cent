package display.widgets.sliders;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Animation;
import display.images.Images;
import display.widgets.sliders.nodes.NormalNode;

import java.nio.file.Paths;

public class NormalSlider extends HorizontalSlider{

    private String directoryPath = Paths.get(Images.RESOURCES_PATH, "images", "sliders", "normal").toString();

    public NormalSlider(Dimension dimension) {
        super(dimension);

        NormalNode node = new NormalNode(this, dimension.scale(new Scale(0.1, 1)));
        node.displayPriority = 1;
        setNode(node);

        node.coordinates = new Coordinates(getValue() * dimension.width, 0);

        String path = Paths.get(directoryPath, "default").toString();
        Animation animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension.scale(new Scale(1, 0.1));
        animation.coordinates = new Coordinates(0, dimension.height * 0.45);
        addAnimation("default", animation);
        animate("default");
    }

}
