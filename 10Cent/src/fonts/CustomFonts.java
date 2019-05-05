package fonts;

import display.images.Images;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class CustomFonts {

    private final static String FONT_PATH = Paths.get(Images.RESOURCES_PATH, "fonts").toString();
    private static GraphicsEnvironment ge;
    private static Map<String, Font> fonts = new HashMap<>();

    public final static String CALLIGRAPHY = "calligraphy";
    public final static String PAINTER = "painter";
    public final static String BIRDS_OF_PARADISE = "birds-of-paradise";
    public final static String YOUTH_TOUCH = "youth-touch";
    public final static String QUICK_KISS = "quick-kiss";
    public final static String QUITE_MAGICAL = "quite-magical";
    public final static String HUMBLE_CAFE = "humble-cafe";

    public static void load() {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        addFont(CALLIGRAPHY);
        addFont(PAINTER);
        addFont(BIRDS_OF_PARADISE);
        addFont(YOUTH_TOUCH);
        addFont(QUICK_KISS);
        addFont(QUITE_MAGICAL);
        addFont(HUMBLE_CAFE);
    }

    // adds ttf file to fonts
    private static void addFont(String fontName) {
        addFont(fontName, "ttf");
    }

    private static void addFont(String fontName, String type) {
        File file = new File(Paths.get(FONT_PATH, fontName + "." + type).toString());
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ge.registerFont(font);
        fonts.put(fontName, font);
    }

    public static Font getFont(String fontName, float size) {
        return fonts.get(fontName).deriveFont(size);
    }

}
