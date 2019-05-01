package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public enum MusicPlayer {
    TEST("test.mp3", 100);

    final double localVolume;
    protected MediaPlayer mediaPlayer;

    MusicPlayer(String fileName, double soundPercent) {
        this.localVolume = soundPercent;
        Media media = new Media(new File(Paths.get(AudioPlayer.FILEPATH_MUSIC, fileName).toString()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(localVolume);
    }
}
