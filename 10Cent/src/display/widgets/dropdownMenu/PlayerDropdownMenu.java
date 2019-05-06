package display.widgets.dropdownMenu;

import display.frame.DisplayComponent;
import display.frame.DisplayObject;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.widgets.label.Align;
import display.widgets.buttons.PlayerButton;
import logic.players.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

import static display.frame.MainPanel.drawRectangle;

public class PlayerDropdownMenu extends DropdownMenu {

    private Dimension dimension;
    private Player[] players;
    private Align align;

    public PlayerDropdownMenu(Player[] players, List<String> oldPlayers, Align align, int row, Dimension dimension) {
        super(getNames(players));
        this.players = players;
        this.align = align;
        this.dimension = dimension;

        boolean hasAnyNew = false;

        otherObjects = new PlayerButton[values.length];
        for (int i = 0; i < values.length; i++) {
            int finalI = i;
            boolean isNew = !oldPlayers.contains(players[i].toString());
            if (isNew)
                hasAnyNew = true;
            PlayerButton button = new PlayerButton(this, isNew, true, players[i], dimension) {
                @Override
                public void clicked() {
                    index = finalI;
                    refresh();
                    backup();
                }
            };
            switch (align) {
                case LEFT:
                    button.coordinates = new Coordinates(dimension.width * (i % row), dimension.height * (i / row + 1));
                    break;
                case CENTER:
                case RIGHT:
                    button.coordinates = new Coordinates(-dimension.width * (i % row), dimension.height * (i / row + 1));
                    break;
            }
            otherObjects[i] = button;
        }

        displayedObject = new PlayerButton(this, hasAnyNew, false, players[index], dimension) {
            @Override
            public void clicked() {
                if (dropeddown) {
                    backup();
                } else {
                    dropdown();
                }
            }
        };
        addSubComponent(displayedObject);
    }

    private static String[] getNames(Player[] players) {
        String[] names = new String[players.length];
        for (int i = 0; i < players.length; i++)
            names[i] = players[i].toString();
        return names;
    }

    @Override
    public void refresh() {
        ((PlayerButton) displayedObject).setPlayer(players[index]);
    }

    @Override
    public void dropdown() {
        dropeddown = true;
        ((PlayerButton) displayedObject).suppressLabel = true;
        for (DisplayComponent otherObject : otherObjects)
            addSubComponent(otherObject);
    }

    @Override
    public void backup() {
        dropeddown = false;
        ((PlayerButton) displayedObject).suppressLabel = false;
        for (DisplayComponent otherObject : otherObjects)
            removeSubComponent(otherObject);
    }

    @Override
    public boolean contains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        boolean contains = super.contains(coordinates, scale, mouseEvent);
        boolean hasAnyNew = false;
        for (DisplayObject playerButton : otherObjects)
            if (((PlayerButton) playerButton).newLabel != null)
                hasAnyNew = true;
        if (!hasAnyNew)
            ((PlayerButton) displayedObject).seen();
        return contains;
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke((float) (0.3 * scale.average())));

        drawRectangle(coordinates, dimension.scale(scale), g);
    }


}
