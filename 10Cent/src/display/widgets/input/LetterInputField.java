package display.widgets.input;

import display.frame.misc.Dimension;
import display.widgets.label.Align;

public class LetterInputField extends InputField {

    /*
    Used to input alphabetic characters and spaces.
     */

    public LetterInputField(int maxCharacters, String defaultText, Align align, Dimension dimension) {
        super(maxCharacters, defaultText, align, dimension);
    }

    @Override
    public boolean isLegal(char keyChar) {
        return Character.isLetter(keyChar) || Character.isSpaceChar(keyChar);
    }

    // blank input is also acceptable (" ")
    @Override
    public boolean isValid() {
        return !isEmpty();
}

}
