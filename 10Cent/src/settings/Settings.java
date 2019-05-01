package settings;

import audio.AudioPlayer;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.org.glassfish.gmbal.Description;
import display.frame.MainFrame;
import display.images.Images;

import java.io.*;
import java.nio.file.Paths;

public final class Settings {

    public static final String SETTINGS_FILE = Paths.get(Images.RESOURCES_PATH, "settings", "settings").toString();

    public static double globalVolume;
    public static double soundVolume;
    public static double musicVolume;
    public static boolean windowedMode;
    public static int windowSizeX;
    public static int windowSizeY;
    public static int windowLocationX;
    public static int windowLocationY;

    // settingCount is the number of settings
    private static final int settingCount = 8;


    public static void setup() {
        read();
        apply();
    }

    public static void save() {
        apply();
        write();
    }

    private static void read() {
        int counter = 0;
        try{
            BufferedReader in = new BufferedReader(new FileReader(SETTINGS_FILE));

            while (in.ready()) {
                String line = in.readLine().trim().replaceAll("\\s", "");

                if (line.equals("")) continue;
                if (line.charAt(0) == '#') continue;

                String[] splitLine = line.split("=");

                if (splitLine.length != 2) {
                    System.out.println("Corrupted file, writing default settings.");
                    DefaultSettings.setDefaultSettings();
                    break;
                }

                switch (splitLine[0]){
                    case("globalVolume"):
                        globalVolume = Double.valueOf(splitLine[1]);
                        counter++;
                        break;

                    case("soundVolume"):
                        soundVolume = Double.valueOf(splitLine[1]);
                        counter++;
                        break;

                    case("musicVolume"):
                        musicVolume = Double.valueOf(splitLine[1]);
                        counter++;
                        break;

                    case("windowedMode"):
                        windowedMode = Boolean.valueOf(splitLine[1]);
                        counter++;
                        break;

                    case("windowSizeX"):
                        windowSizeX = Integer.valueOf(splitLine[1]);
                        counter++;
                        break;

                    case("windowSizeY"):
                        windowSizeY = Integer.valueOf(splitLine[1]);
                        counter++;
                        break;

                    case("windowLocationX"):
                        windowLocationX = Integer.valueOf(splitLine[1]);
                        counter++;
                        break;

                    case("windowLocationY"):
                        windowLocationY = Integer.valueOf(splitLine[1]);
                        counter++;
                        break;

                }

            }
            in.close();
            if(counter!=settingCount){
                System.out.println("Settings file was corrupted, writing new settings.");
                DefaultSettings.setDefaultSettings();
            }
        } catch (Exception e) {
            System.out.println("Settings file does not exist or is corrupted. Writing default settings.");
            DefaultSettings.setDefaultSettings();
        }
    }

    private static void apply() {
        MainFrame.switchToWindowed();
        AudioPlayer.updateSound();
    }

    protected static void write() {
        try{
            PrintWriter out = new PrintWriter(new FileWriter(SETTINGS_FILE));
            DefaultSettings.writeDefaultHead(out);
            out.println("# Audio");
            out.println("globalVolume = " + globalVolume);
            out.println("soundVolume = " + soundVolume);
            out.println("musicVolume = " + musicVolume);
            out.println();
            out.println("# Screen");
            out.println("windowedMode = " + windowedMode);
            out.println("windowSizeX = " + windowSizeX);
            out.println("windowSizeY = " + windowSizeY);
            out.println("windowLocationX = " + windowLocationX);
            out.println("windowLocationY = " + windowLocationY);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
