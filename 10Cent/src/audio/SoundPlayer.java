package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public enum SoundPlayer {
    TEST("test.wav", 100);



    final double localVolume;
    MediaPlayer mediaPlayer;

    SoundPlayer(String fileName, double soundPercent){
        this.localVolume = soundPercent;
        Media media = new Media(new File(Paths.get(AudioPlayer.FILEPATH_SOUND ,fileName).toString()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(localVolume);
    }
}
