package display.images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Images {

    /*
    Images is "static class" for accessing images. All images should be loaded at the start of the program and
    accessed afterwards by their paths.
     */

    public static String resourcesPath = Paths.get("10Cent", "src", "resources").toString();
    private static Map<String, Image> images = new HashMap<>();

    public static Image get(String name) {
        return images.get(name);
    }

    // called once at the start of the program
    public static void loadImages(String directoryPath) {
        List<String> paths = getAllPaths(directoryPath);
        for (String path : paths) {
            String suffix = path.substring(path.lastIndexOf(".") + 1);
            if (suffix.equals("png"))
                add(path);
        }
    }

    public static void add(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            images.put(imagePath, image);
        } catch (IOException e) {
            System.out.println("Image not found on: " + imagePath);
        }
    }

    // gets all file paths from specified directory and all sub-directories
    public static List<String> getAllPaths(String directoryPath){
        List<File> directories = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        for (File file: new File(directoryPath).listFiles()) {
            if (file.isDirectory())
                directories.add(file);
            else {
                paths.add(file.getPath());
            }
        }
        List<File> tempDirectories = new ArrayList<>();
        while (!directories.isEmpty()){
            for (File dir: directories){
                for (File file: dir.listFiles()){
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

    public static String[] getFileNames(String directoryPath) {
        File[] fileList = new File(directoryPath).listFiles();
        String[] fileNames = new String[fileList.length];
        for (int i = 0; i < fileNames.length; i++)
            fileNames[i] = fileList[i].getName();
        return fileNames;
    }

    public static String[] getFilePaths(String directoryPath) {
        String[] filePaths = getFileNames(directoryPath);
        for (int i = 0; i < filePaths.length; i++)
            filePaths[i] = Paths.get(directoryPath, filePaths[i]).toString();
        return filePaths;
    }

}
