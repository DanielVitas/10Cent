package display.images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public final class Images {

    /*
    Images is "static class" for accessing images. All images should be loaded at the start of the program and
    accessed afterwards by their paths.
     */

    public static String RESOURCES_PATH = Paths.get("10Cent","src", "resources").toString();
    private final static String IMAGE_PATH = Paths.get(RESOURCES_PATH, "images").toString();
    private static Map<String, Image> images = new HashMap<>();

    public static Image get(String name) {
        return images.get(name);
    }

    // called once at the start of the program
    public static void load() {
        List<String> paths = getAllPaths(IMAGE_PATH);
        for (String path : paths) {
            String suffix = path.substring(path.lastIndexOf(".") + 1);
            if (suffix.equals("png"))
                add(path);
        }
    }

    // should be used to add images
    public static void add(String imagePath) {
        try {
            Image image = ImageIO.read(new File(imagePath));
            images.put(imagePath, image);
        } catch (IOException e) {
            System.out.println("Image not found on: " + imagePath);
        }
    }

    // gets all file paths from specified directory and all sub-directories
    private static List<String> getAllPaths(String directoryPath){
        List<File> directories = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        for (File file: Objects.requireNonNull(new File(directoryPath).listFiles())) {
            if (file.isDirectory())
                directories.add(file);
            else {
                paths.add(file.getPath());
            }
        }
        List<File> tempDirectories = new ArrayList<>();
        while (!directories.isEmpty()){
            for (File dir: directories){
                for (File file: Objects.requireNonNull(dir.listFiles())){
                    if (file.isDirectory())
                        tempDirectories.add(file);
                    else
                        paths.add(file.getPath());
                }
            }
            directories.clear();
            directories.addAll(tempDirectories);
            tempDirectories.clear();
        }
        return paths;
    }

    // get all file names from specified directory
    private static String[] getFileNames(String directoryPath) {
        File[] fileList = new File(directoryPath).listFiles();
        String[] fileNames = new String[Objects.requireNonNull(fileList).length];
        for (int i = 0; i < fileNames.length; i++)
            fileNames[i] = fileList[i].getName();
        return fileNames;
    }

    // get all file paths from specified directory
    static String[] getFilePaths(String directoryPath) {
        String[] filePaths = getFileNames(directoryPath);
        for (int i = 0; i < filePaths.length; i++)
            filePaths[i] = Paths.get(directoryPath, filePaths[i]).toString();
        return filePaths;
    }

}
