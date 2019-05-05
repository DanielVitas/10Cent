package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.widgets.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

public class MainPanel extends JPanel  implements MouseListener, MouseMotionListener {

    /*
    Displayed on MainFrame.
     */

    private List<DisplayComponent> displayComponents = new ArrayList<>();

    public MainPanel(Dimension preferredSize) {
        setPreferredSize(preferredSize.getAwtDimension());

        setBackground(Color.decode("#EEEEEE"));

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void addDisplayComponent(DisplayComponent displayComponent) {
        displayComponents.add(displayComponent);
        displayComponents.sort(DisplayComponent.COMPARATOR);
    }

    public void removeDisplayComponent(DisplayComponent displayComponent) {
        displayComponents.remove(displayComponent);
    }

    public static void drawLine(Coordinates startCoordinates, Coordinates endCoordinates, Graphics g) {
        g.drawLine(startCoordinates.getIntegerX(), startCoordinates.getIntegerY(),
                endCoordinates.getIntegerX(), endCoordinates.getIntegerY());
    }

    public static void drawRectangle(Coordinates coordinates, Dimension dimension, Graphics g) {
        g.drawRect(coordinates.getIntegerX(), coordinates.getIntegerY(),
                dimension.getIntegerWidth(), dimension.getIntegerHeight());
    }

    public static void drawEllipse(Coordinates coordinates, Dimension dimension, Graphics g) {
        g.drawRoundRect(coordinates.getIntegerX(), coordinates.getIntegerY(),
                dimension.getIntegerWidth(), dimension.getIntegerHeight(),
                dimension.getIntegerWidth(), dimension.getIntegerHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            for (DisplayComponent displayComponent : displayComponents)
                displayComponent.paint(displayComponent.getCoordinates().scale(MainFrame.getScale()), MainFrame.getScale(), g);
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();  // zgodi se redko in tisti frame, ki zaradi tega ne prikaze je nepomemben
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (Mouse.hovered != null) {
            Coordinates mouseCoordinates = Mouse.getCoordinates(mouseEvent);
            Coordinates coordinates = Mouse.hovered.getCoordinates().flip().add(mouseCoordinates);
            Mouse.hovered.press(coordinates, Scale.noScale, mouseEvent);
        }
        Mouse.pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (Mouse.hovered != null) {
            Coordinates mouseCoordinates = Mouse.getCoordinates(mouseEvent);
            Coordinates coordinates = Mouse.hovered.getCoordinates().flip().add(mouseCoordinates);
            if (Mouse.hovered.contains(coordinates, Scale.noScale, mouseEvent))
                Mouse.hovered.click(coordinates, Scale.noScale, mouseEvent);
            else
                Mouse.hovered.release(coordinates, Scale.noScale, mouseEvent);
        }
        Mouse.pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (Mouse.hovered == null)
            return;
        Coordinates mouseCoordinates = Mouse.getCoordinates(mouseEvent);
        Coordinates coordinates = Mouse.hovered.getCoordinates().flip().add(mouseCoordinates);
        Mouse.hovered.drag(coordinates, Scale.noScale, mouseEvent);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (Mouse.pressed)
            return;
        Coordinates mouseCoordinates = Mouse.getCoordinates(mouseEvent);
        for (DisplayComponent displayComponent : reverse(displayComponents)) {
            Coordinates coordinates = displayComponent.getCoordinates().flip().add(mouseCoordinates);
            if (displayComponent.contains(coordinates, Scale.noScale, mouseEvent)) {
                if (Mouse.hovered != displayComponent) {
                    if (Mouse.hovered != null)
                        Mouse.hovered.unhover(mouseCoordinates, Scale.noScale, mouseEvent);
                    displayComponent.hover(coordinates, Scale.noScale, mouseEvent);
                    Mouse.hovered = displayComponent;
                }
                return;
            }
        }
        if (Mouse.hovered != null)
            Mouse.hovered.unhover(mouseCoordinates, Scale.noScale, mouseEvent);
        Mouse.hovered = null;
    }

    private static<T> List<T> reverse(List<T> list) {
        List<T> reverseList = new ArrayList<>(list);
        Collections.reverse(reverseList);
        return reverseList;
    }

}
