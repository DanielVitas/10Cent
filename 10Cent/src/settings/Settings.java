package settings;

import audio.AudioPlayer;
import display.frame.MainFrame;
import display.images.Images;

import java.io.*;
import java.nio.file.Paths;

public final class Settings {

    public static final String SETTINGS_FILE = Paths.get(Images.RESOURCES_PATH, "settings", "settings.txt").toString();

    public static double globalVolume;
    public static double soundVolume;
    public static double musicVolume;
    public static boolean windowedMode;
    public static int[] windowSize;
    public static int[] windowLocation;

    public static void defaults() {
        globalVolume = 0.5;
        soundVolume = 0.5;
        musicVolume = 0.5;
        windowedMode = true;
        windowSize = new int[]{800, 800};
        windowLocation = new int[]{20, 20};
    }

    // called only once at the start of the program
    public static void load() {
        defaults();
        read();
        AudioPlayer.applyVolume();
    }

    public static void save() {
        apply();
        write();
    }

    private static void read() {
        try{
            BufferedReader in = new BufferedReader(new FileReader(SETTINGS_FILE));

            while (in.ready()) {
                String line = in.readLine().trim().replaceAll("\\s", "");

                if (line.equals(""))
                    continue;
                if (line.charAt(0) == '#')
                    continue;

                String[] splitLine = line.split("=");

                if (splitLine.length != 2)
                    continue;

                switch (splitLine[0]){
                    case ("globalVolume"):
                        globalVolume = getVolume(Double.valueOf(splitLine[1]));
                        break;
                    case ("soundVolume"):
                        soundVolume = getVolume(Double.valueOf(splitLine[1]));
                        break;
                    case ("musicVolume"):
                        musicVolume = getVolume(Double.valueOf(splitLine[1]));
                        break;
                    case ("windowedMode"):
                        windowedMode = Boolean.valueOf(splitLine[1]);
                        break;
                    case ("windowSize"):
                        String[] windowSizeXY = splitLine[1].split(",");
                        windowSize = new int[]{Integer.valueOf(windowSizeXY[0]), Integer.valueOf(windowSizeXY[1])};
                        break;
                    case ("windowLocation"):
                        String[] windowLocationXY = splitLine[1].split(",");
                        windowLocation = new int[]{Integer.valueOf(windowLocationXY[0]), Integer.valueOf(windowLocationXY[1])};
                        break;
                }
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace(); // exceptions can arise from user messing with settings file
        }
    }

    // changes double to a valid one (0 - 1)
    private static double getVolume(double volume) {
        if (volume < 0)
            return 0;
        else if (volume > 1)
            return 1;
        return volume;
    }

    private static void apply() {
        MainFrame.switchToWindowed();
        AudioPlayer.applyVolume();
    }

    private static void write() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(SETTINGS_FILE));

            out.println("# audio");
            out.println("globalVolume = " + globalVolume);
            out.println("soundVolume = " + soundVolume);
            out.println("musicVolume = " + musicVolume);
            out.println();

            out.println("# screen");
            out.println("windowedMode = " + windowedMode);
            out.println(String.format("windowSize = %d, %d", windowSize[0], windowSize[1]));
            out.println(String.format("windowLocation = %d, %d", windowLocation[0], windowLocation[1]));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
