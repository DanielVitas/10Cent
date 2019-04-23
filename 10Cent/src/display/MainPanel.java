package display;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {

    private Dimension preferredSize;
    private List<DisplayComponent> components = new ArrayList<>();

    public MainPanel(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    @Override
    public Dimension getPreferredSize() {
        return preferredSize;
    }

    @Override
    public void repaint() {
        super.repaint();

        for (DisplayComponent component : components) {
            component.paint();
        }
    }

}
