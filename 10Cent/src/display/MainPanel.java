package display;

import logic.boards.finalBoard.FinalBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainPanel extends JPanel {

    private List<DisplayComponent> displayComponents = new ArrayList<>();

    public MainPanel(Dimension preferredSize) {
        setPreferredSize(preferredSize);
    }

    public void addDisplayComponent(DisplayComponent displayComponent) {
        displayComponents.add(displayComponent);
        Collections.sort(displayComponents, DisplayComponent.comparator);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DisplayComponent displayComponent : displayComponents)
            displayComponent.paint(0, 0, g);
    }

}
