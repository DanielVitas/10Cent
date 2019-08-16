package display.widgets.input;

import display.frame.DisplayObject;
import display.frame.InputComponent;
import display.frame.MainPanel;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Animation;
import display.images.Images;
import display.widgets.buttons.Button;
import display.widgets.label.Align;
import display.widgets.label.Label;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

public abstract class InputField extends DisplayObject implements InputComponent {

    /*
    Box used to input text with keyboard.
     */

    private static String directoryPath = Paths.get(Images.RESOURCES_PATH,"images", "input", "field").toString();
    private static Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.PLAIN, 3);

    private int maxCharacters;
    private Dimension dimension;

    private Label defaultLabel;
    private Label inputLabel;

    InputField(int maxCharacters, String defaultText, Align align, Dimension dimension) {
        this.maxCharacters = maxCharacters;
        this.dimension = dimension;

        defaultLabel = new Label(defaultText, font, Color.BLACK, new Dimension(dimension.width - 2, dimension.height), align);
        defaultLabel.coordinates = new Coordinates(1,0);

        inputLabel = new Label("", font, Color.BLACK, new Dimension(dimension.width - 2, dimension.height), align);
        inputLabel.coordinates = new Coordinates(1,0);

        String path;
        Animation animation;

        path = Paths.get(directoryPath, "default").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("default", animation);

        path = Paths.get(directoryPath, "hovered").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("hovered", animation);

        path = Paths.get(directoryPath, "pressed").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("pressed", animation);

        animate("default");

        hitBoxes.add(new Rectangle(dimension.getAwtDimension()));
    }

    public String getDefaultText() {
        return defaultLabel.text;
    }

    private void animateDefault() {
        if (MainPanel.selectedInputComponent == this)
            animateClicked();
        else
            animate("default");
    }

    private void animateHovered() {
        if (MainPanel.selectedInputComponent != this)
            animate("hovered");
    }

    private void animatePressed() {
        animate("pressed");
    }

    private void animateClicked() {
        animatePressed();
    }

    public boolean isEmpty() {
        return getText().length() == 0;
    }

    public String getText() {
        return inputLabel.text;
    }

    // not every char should be legal - sometimes you want only numbers, ...
    public abstract boolean isLegal(char keyChar);

    // validates input as whole (example: 0 is not a valid natural number, while 01 or 10 are)
    public abstract  boolean isValid();

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        // if no text is entered it should display what kind of input it expects
        if (inputLabel.text.length() == 0 && MainPanel.selectedInputComponent != this)
            defaultLabel.paint(coordinates.add(defaultLabel.coordinates.scale(scale)), scale, g);
        else
            inputLabel.paint(coordinates.add(inputLabel.coordinates.scale(scale)), scale, g);

        Button.drawBoarder(0.3, coordinates, dimension, scale, g);
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateHovered();
    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateDefault();
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animatePressed();
    }

    @Override
    public void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateDefault();
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateClicked();
        MainPanel.setInputComponent(this);
    }

    @Override
    public void typeKey(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == '\b')
            if (inputLabel.text.length() > 0)
                inputLabel.text = inputLabel.text.substring(0, inputLabel.text.length() - 1);
        if (inputLabel.text.length() >= maxCharacters || keyEvent.getKeyChar() == '\b' || keyEvent.getKeyChar() == '\n')
            return;
        char c = keyEvent.getKeyChar();
        if (isLegal(c))
            inputLabel.text += c;
    }

    @Override
    public void releaseKey(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
            MainPanel.setInputComponent(null);
    }

    @Override
    public void deselect() {
        animate("default");
    }

}
