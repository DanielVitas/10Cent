package display;

import java.util.ArrayList;
import java.util.List;

public interface DisplayComponent {

    int priority = 0;

    void paint(double x, double y);  // origin

}
