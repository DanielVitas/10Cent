package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

public class MainPanel extends JPanel  implements MouseListener, MouseMotionListener, KeyListener {

    /*
    Displayed on MainFrame. It takes care of basic display functionality.
     */

    // stores all currently displayed objects
    private List<DisplayComponent> displayComponents = new ArrayList<>();

    // MainPanel will NOT set selectedInputComponent automatically
    public static InputComponent selectedInputComponent;

    public MainPanel(Dimension preferredSize) {
        setPreferredSize(preferredSize.getAwtDimension());

        // this is actually default background color
        setBackground(Color.decode("#EEEEEE"));

        // adds all listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    // adds DisplayComponent to displayComponents
    public void addDisplayComponent(DisplayComponent displayComponent) {
        if (displayComponents.contains(displayComponent))
            return;
        displayComponents.add(displayComponent);
        displayComponents.sort(DisplayComponent.COMPARATOR);
    }

    public void removeDisplayComponent(DisplayComponent displayComponent) {
        displayComponents.remove(displayComponent);
    }

    // is usually called from the newly selected input component
    public static void setInputComponent(InputComponent inputComponent) {
        if (selectedInputComponent != null)
            selectedInputComponent.deselect();
        selectedInputComponent = inputComponent;
    }

    /*
    The following 3 functions are by functionality identical to their respective built in functions - their job is to
    convert objects (coordinates, dimension) used in this program to ones required by builtin functions.
     */

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

    // paints DisplayComponents stored in displayedComponents
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            for (DisplayComponent displayComponent : displayComponents)
                displayComponent.paint(displayComponent.getCoordinates().scale(MainFrame.getScale()), MainFrame.getScale(), g);
        } catch (ConcurrentModificationException e) {
            // occasionally a single frame is lost due to this - it was deemed irrelevant (therefore ignored)
        }
    }

    /*
    Following few functions implement MouseListener. Their functionality is evident and will not be specifically
    explained.
     */

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
            if (Mouse.hovered.contains(coordinates, Scale.noScale, mouseEvent)) {
                setInputComponent(null);
                Mouse.hovered.click(coordinates, Scale.noScale, mouseEvent);
            } else {
                Mouse.hovered.release(coordinates, Scale.noScale, mouseEvent);
            }
        } else {
            setInputComponent(null);
        }
        Mouse.pressed = false;
        onMove(mouseEvent); // so it hovers hovered object if any
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

        if (onMove(mouseEvent))
            return;

        // mouse isn't hovering any object
        if (Mouse.hovered != null)
            Mouse.hovered.unhover(mouseCoordinates, Scale.noScale, mouseEvent);
        Mouse.hovered = null;
    }

    // returns true, if mouse is hovering any object
    private boolean onMove(MouseEvent mouseEvent) {
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
                return true;
            }
        }
        return false;
    }

    /*
    Following three functions implement KeyListener - functionality evident.
     */

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (selectedInputComponent != null)
            selectedInputComponent.typeKey(keyEvent);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (selectedInputComponent != null)
            selectedInputComponent.releaseKey(keyEvent);
    }

    // returns new list - reversed list given in argument
    private static<T> List<T> reverse(List<T> list) {
        List<T> reverseList = new ArrayList<>(list);
        Collections.reverse(reverseList);
        return reverseList;
    }

}
