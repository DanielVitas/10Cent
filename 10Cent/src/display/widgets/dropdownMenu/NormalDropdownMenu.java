package display.widgets.dropdownMenu;

import display.frame.DisplayComponent;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Images;
import display.widgets.buttons.DropdownButton;
import display.widgets.buttons.NormalButton;

import java.awt.*;
import java.nio.file.Paths;

import static display.frame.MainPanel.drawRectangle;

public class NormalDropdownMenu extends DropdownMenu {

    private Dimension dimension;

    public NormalDropdownMenu(String[] values, int fontSize, Dimension dimension) {
        super(values);
        this.dimension = dimension;

        otherObjects = new DropdownButton[values.length];
        for (int i = 0; i < values.length; i++) {
            int finalI = i;
            DropdownButton button = new DropdownButton(this, values[finalI], fontSize, dimension) {
                @Override
                public void clicked() {
                    index = finalI;
                    refresh();
                    backup();
                }
            };
            button.coordinates = new Coordinates(0, dimension.height * (i + 1));
            otherObjects[i] = button;
        }


        displayedObject = new DropdownButton(this, values[index], fontSize, dimension) {
            @Override
            public void clicked() {
                if (dropeddown) {
                    backup();
                } else {
                    dropdown();
                }
            }
        };
        addSubComponent(displayedObject);
    }

    @Override
    public void refresh() {
        ((DropdownButton) displayedObject).label.text = values[index];
    }

    @Override
    public void dropdown() {
        dropeddown = true;
        for (DisplayComponent otherObject : otherObjects)
            addSubComponent(otherObject);
    }

    @Override
    public void backup() {
        dropeddown = false;
        for (DisplayComponent otherObject : otherObjects)
            removeSubComponent(otherObject);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke((float) (0.3 * scale.average())));

        drawRectangle(coordinates, dimension.scale(scale), g);
    }

}
