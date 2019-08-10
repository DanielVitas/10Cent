package display.frame;

import java.awt.event.KeyEvent;

public interface InputComponent {

    /*
    Input from KeyListener is passed to currently active InputComponent
     */

    // not necessary, since everything can be done with releaseKey, but convenient
    void typeKey(KeyEvent keyEvent);

    void releaseKey(KeyEvent keyEvent);

    //
    void deselect();

}
