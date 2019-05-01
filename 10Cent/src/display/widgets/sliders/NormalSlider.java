package display.widgets.sliders;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Animation;
import display.images.Images;
import display.widgets.sliders.nodes.NormalNode;

import java.nio.file.Paths;

public class NormalSlider extends HorizontalSlider{

    private String directoryPath = Paths.get(Images.resourcesPath, "images", "sliders", "normal").toString();

    public NormalSlider(Dimension dimension) {
        super(dimension);

        NormalNode node = new NormalNode(this, dimension.scale(new Scale(0.1, 10)));
        setNode(node);

        node.coordinates = new Coordinates(getValue() * dimension.width, 0);

        String path = Paths.get(directoryPath, "default").toString();
        Animation animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("default", animation);
        animate("default");
    }

}
