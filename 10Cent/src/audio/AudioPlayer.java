package audio;

public class AudioPlayer {
    // This is a "static" object providing sound and music playing functions

    private static double globalSound;
    private static double soundsSound;
    private static double musicsSound;
    private static MusicPlayer[] musicPlayers = MusicPlayer.values();
    private static SoundPlayer[] soundPlayers = SoundPlayer.values();

    public static void setup(){

    }

    public static void setGlobalSound(double percent){
        globalSound = percent;}

    public static double getGlobalSound(){return globalSound;}

    public static void setSoundsSound(double percent){
        soundsSound = percent;
        updateSound();
    }

    public static double getSoundsSound(){return soundsSound;}

    public static void setMusicsSound(double percent){
        musicsSound = percent;
        updateSound();
    }

    public static double getMusicsSound(){return musicsSound;}



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
            player.mediaPlayer.setVolume(globalSound*player.localSound*musicsSound);
        }
        for(SoundPlayer player : soundPlayers){
            player.mediaPlayer.setVolume(globalSound*player.localSound*soundsSound);
        }
    }

}
