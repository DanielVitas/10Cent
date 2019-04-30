package settings;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;

final class DefaultSettings {

    private final static double defaultGlobalVolume = 100;
    private final static double defaultSoundVolume = 100;
    private final static double defaultMusicVolume = 100;

    static void writeDefault(){
        try{
            PrintWriter out = new PrintWriter(new FileWriter(Settings.SETTINGS_FILE));
            writeDefaultHead(out);
            out.println("#Audio");
            out.println("globalVolume = " + defaultGlobalVolume);
            out.println("soundVolume = " + defaultSoundVolume);
            out.println("musicVolume = " + defaultMusicVolume);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        Settings.setup();
    }

    static void writeDefaultHead(PrintWriter printWriter){
        printWriter.println("# Corrupting settings will restore them to their defaults");
        printWriter.println();
    }
}
