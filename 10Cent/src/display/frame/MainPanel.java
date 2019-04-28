package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;

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
        setPreferredSize(preferredSize.getAwtDimension());

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
            displayComponent.paint(displayComponent.getCoordinates(), MainFrame.getScale(), g);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Scale scale = MainFrame.getScale();
        double x = mouseEvent.getX() / scale.horizontal;
        double y = mouseEvent.getY() / scale.vertical;
        System.out.println("Clicked at " + "(" + x + ", " + y + ")");
        for (DisplayComponent displayComponent : displayComponents) {
            Coordinates coordinates = displayComponent.getCoordinates().flip().add(x, y);
            if (displayComponent.contains(coordinates)) {
                displayComponent.clicked(coordinates, mouseEvent);
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
