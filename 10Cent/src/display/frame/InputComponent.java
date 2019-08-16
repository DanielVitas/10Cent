package display.frame;

import java.awt.event.KeyEvent;

public interface InputComponent {

    /*
    Input from KeyListener is passed to currently active InputComponent
     */

    // called when key is typed - usually upon release, except when holding it down
    void typeKey(KeyEvent keyEvent);

    // called only when key is released - key event contains more detailed information than one passed to typeKey
    void releaseKey(KeyEvent keyEvent);

    // called when input component is no longer selected
    void deselect();

}
