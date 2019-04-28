package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.widgets.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainPanel extends JPanel  implements MouseListener, MouseMotionListener {

    /*
    Displayed on MainFrame.
     */

    private List<DisplayComponent> displayComponents = new ArrayList<>();

    public MainPanel(Dimension preferredSize) {
        setPreferredSize(preferredSize.getAwtDimension());

        addMouseListener(this);

        Button button = new Button("Button text", new Dimension(100, 100));
        addDisplayComponent(button);
        JButton button2 = new JButton("Button text");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Clicked");
                button.doClick();
            }
        });
        button2.setBounds(200, 0, 100, 100);
        add(button2);
        Component[] a = getComponents();
        a = null;

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
            displayComponent.paint(displayComponent.getCoordinates().scale(MainFrame.getScale()), MainFrame.getScale(), g);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Scale scale = MainFrame.getScale();
        double x = mouseEvent.getX() / scale.horizontal;
        double y = mouseEvent.getY() / scale.vertical;
        System.out.println("Clicked at " + "(" + x + ", " + y + ")");
        for (DisplayComponent displayComponent : displayComponents) {
            Coordinates coordinates = displayComponent.getCoordinates().flip().add(x, y);
            if (displayComponent.contains(coordinates, new Scale(1, 1))) {
                displayComponent.clicked(coordinates, new Scale(1, 1), mouseEvent);
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

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
