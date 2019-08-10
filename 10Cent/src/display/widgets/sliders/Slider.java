package display.widgets.sliders;

import display.frame.DisplayObject;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.widgets.sliders.nodes.Node;

import java.awt.event.MouseEvent;

public abstract class Slider extends DisplayObject {

    /*
    Sliders are abstract objects which have a node and respond to it's dragging.
     */

    protected Dimension dimension;
    protected Node node;

    public Slider(Dimension dimension) {
        this.dimension = dimension;
    }

    // node MUST be set, before slider is added to panel
    public void setNode(Node node) {
        if (this.node != null)
            removeSubComponent(this.node);
        this.node = node;
        addSubComponent(node);
    }

    // is called from node when it's dragged
    public abstract Coordinates slide(Coordinates deltaCoordinates);

    @Override
    public boolean contains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        return node.contains(coordinates.add(node.coordinates.scale(scale).flip()), scale, mouseEvent);
    }

    @Override
    public void drag(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        node.drag(coordinates, scale, mouseEvent);
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        node.press(coordinates, scale, mouseEvent);
    }

}
