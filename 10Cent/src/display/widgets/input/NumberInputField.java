package display.widgets.input;

import display.frame.misc.Dimension;
import display.widgets.label.Align;

public class NumberInputField extends InputField {

    /*
    Used to input numbers.
     */

    public NumberInputField(int maxCharacters, String defaultText, Align align, Dimension dimension) {
        super(maxCharacters, defaultText, align, dimension);
    }

    public Integer getNumber() {
        String text = getText();
        if (text.equals(""))
            return null;
        return Integer.parseInt(text);
    }

    @Override
    public boolean isLegal(char keyChar) {
        return Character.isDigit(keyChar);
    }

    // only natural numbers are valid
    @Override
    public boolean isValid() {
        Integer number = getNumber();
        if (number == null)
            return false;
        return number > 0 && !isEmpty();
    }

}
