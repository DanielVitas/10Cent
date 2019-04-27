package display.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainPanel extends JPanel  implements MouseListener {

    /*
    Displayed on MainFrame.
     */

    private List<DisplayComponent> displayComponents = new ArrayList<>();

    public MainPanel(Dimension preferredSize) {
        setPreferredSize(preferredSize);

        addMouseListener(this);
    }

    public void addDisplayComponent(DisplayComponent displayComponent) {
        displayComponents.add(displayComponent);
        Collections.sort(displayComponents, DisplayComponent.comparator);
    }

    public void removeDisplayComponent(DisplayComponent displayComponent) {
        displayComponents.remove(displayComponent);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DisplayComponent displayComponent : displayComponents)
            displayComponent.paint(displayComponent.getCoordinates(), g);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        for (DisplayComponent displayComponent : displayComponents) {
            Coordinates coordinates = displayComponent.getCoordinates().flip().add(mouseEvent.getX(), mouseEvent.getY());
            if (displayComponent.contains(coordinates)) {
                displayComponent.interact(coordinates, mouseEvent);
                return;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
