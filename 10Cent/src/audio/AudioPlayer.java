package audio;

import java.nio.file.Paths;

public class AudioPlayer {
    // This is a "static" object providing sound and music playing functions

    public static final String FILEPATH_MUSIC = Paths.get("10Cent","src","resources","audio","songs","").toString();
    public static final String FILEPATH_SOUND = Paths.get("10Cent","src","resources","audio","sounds","").toString();

    private static double globalVolume;
    private static double soundVolume;
    private static double musicVolume;
    private static MusicPlayer[] musicPlayers = MusicPlayer.values();
    private static SoundPlayer[] soundPlayers = SoundPlayer.values();

    public static void setup(){

    }

    public static void setGlobalSound(double percent){
        globalVolume = percent;}

    public static double getGlobalSound(){return globalVolume;}

    public static void setSoundsSound(double percent){
        soundVolume = percent;
        updateSound();
    }

    public static double getSoundsSound(){return soundVolume;}

    public static void setMusicsSound(double percent){
        musicVolume = percent;
        updateSound();
    }

    public static double getMusicsSound(){return musicVolume;}



    // MusicPlayer methods
    public static void play(MusicPlayer musicPlayer){musicPlayer.mediaPlayer.play();}

    public static void pause(MusicPlayer musicPlayer) {musicPlayer.mediaPlayer.pause();}

    public static void stop(MusicPlayer musicPlayer) {musicPlayer.mediaPlayer.stop();}


    // SoundPlayer methods
    public static void play(SoundPlayer soundPlayer) {
        soundPlayer.mediaPlayer.stop();
        soundPlayer.mediaPlayer.play();
    }

    public static void pause(SoundPlayer soundPlayer) {soundPlayer.mediaPlayer.pause();}

    public static void stop(SoundPlayer soundPlayer) {soundPlayer.mediaPlayer.stop();}

    // Private methods
    private static void updateSound(){
        for(MusicPlayer player : musicPlayers){
            player.mediaPlayer.setVolume(globalVolume*player.localVolume*musicVolume);
        }
        for(SoundPlayer player : soundPlayers){
            player.mediaPlayer.setVolume(globalVolume*player.localVolume*soundVolume);
        }
    }

}
