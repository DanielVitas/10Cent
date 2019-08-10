package display.widgets.buttons;

import display.frame.misc.Dimension;
import display.images.Animation;
import display.images.Images;

import java.nio.file.Paths;

public class ToggleButton extends NormalButton {

    /*
    This button can be toggled (turned on and off).
     */

    public ToggleButton(String text, int fontSize, Dimension dimension) {
        super(text, fontSize, dimension);
    }

    @Override
    protected void installAnimations() {
        directoryPath = Paths.get(Images.RESOURCES_PATH,"images", "buttons", "toggle").toString();

        super.installAnimations();

        Animation animation;
        String path;

        path = Paths.get(directoryPath, "clicked").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("clicked", animation);
    }

    @Override
    public void clicked() {

    }

    @Override
    public void animateClicked() {
        animate("clicked");
    }

}
