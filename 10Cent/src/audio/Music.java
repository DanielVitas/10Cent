package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public enum Music {

    /*
    Music differs from sound in approach. Music files are usually larger and of different format than sound files.
     They are also not required to be played ASAP and usually don't overlap.
     */

    // Chopin :)
    MAIN_MENU("chopin-nocturne-bflatminor.mp3", 1),
    BATTLE1("chopin-polonaise-aflatmajor.mp3", 0.6),
    BATTLE2("chopin-polonaise-csharpminor.mp3", 1),
    BATTLE3("chopin-nocturne-csharpminor.mp3", 1),
    BATTLE4("chopin-scherzo-bflatminor.mp3", 1),
    DEFEAT("chopin-funeralmarch-bflatminor.mp3", 1),
    PRACTICE("chopin-prelude-dflatmajor.mp3", 1);

    public double volume;  // volume is set for each audio file separately
    public MediaPlayer mediaPlayer;

    Music(String fileName, double volume) {
        this.volume = volume;

        File musicFile = new File(Paths.get(AudioPlayer.MUSIC_PATH, fileName).toString());
        Media media = new Media(musicFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);  // music repeats after ending
    }

    // package private - always play music with AudioPlayer.play
    void play() {
        mediaPlayer.play();
    }

}
