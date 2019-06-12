package com.johnfreier.mail.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileUtils {

    /**
     * Get a list of files that are within the directory that is passed in. 
     * 
     * @param path The path to the folder of files.
     * @param extension File files by extension.
     * @return
     * @throws Exception
     */
    public static List<Path> getFiles(String path, String extension) throws Exception {

        List<Path> files = Files.list(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(FileUtils.isFileType(extension))
                .sorted()
                .collect(Collectors.toList());

        return files;

    }

    /**
     * The the number of files within a directory.
     * 
     * @param path Path to the directory to count.
     * @param extension Filter files by extension.
     * @return
     * @throws Exception
     */
    public static long getFileCount(String path, String extension) throws Exception {

        long count = Files.list(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(FileUtils.isFileType(extension))
                .count();

        return count;

    }

    /**
     * Write to a file.
     * 
     * @param file Path to the file.
     * @param data The data to write to the file.  This will overwrite any existing data.
     * @return
     */
    public static boolean writeFile(Path file, String data) {

        boolean result = true;

        try {

            Files.write(file, data.getBytes());

        } catch (IOException e) {

            e.printStackTrace();
            result = false;

        }

        return result;
    }

    /**
     * Filter files by their extension.
     * 
     * @param extension
     * @return
     */
    protected static Predicate<Path> isFileType(String extension) {
        return p -> p.getFileName().toString().endsWith(extension);
    }

}
