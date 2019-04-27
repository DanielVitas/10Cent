package audio;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;



public class Test{
    static final String LIBRARYPATH = "10Cent/src/resources/audio/";

    public Test(String fileName, float adjust) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        String soundName = LIBRARYPATH + fileName;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(adjust); // Reduce volume by 10 decibels.
        clip.start();
    }
}

