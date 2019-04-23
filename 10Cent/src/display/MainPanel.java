package display;

import logic.boards.finalBoard.FinalBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {

    private Dimension preferredSize;
    private List<DisplayComponent> displayComponents = new ArrayList<>();

    public MainPanel(Dimension preferredSize) {
        this.preferredSize = preferredSize;
        displayComponents.add(new FinalBoard());
    }

    @Override
    public Dimension getPreferredSize() {
        return preferredSize;
    }

    public void manualRepaint() {
        for (DisplayComponent displayComponent : displayComponents)
            displayComponent.paint(0, 0);
        repaint();
    }

}
