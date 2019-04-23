package display;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface DisplayComponent {

    int priority = 0;

    Comparator<DisplayComponent> comparator = Comparator.comparingInt(DisplayComponent::getPriority);

    void paint(double x, double y, Graphics g);  // origin

    default int getPriority() {
        return priority;
    }
}
