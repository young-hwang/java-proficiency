package me.io.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyMainV3 {
    private static final String FILE_NAME = "temp/copy.dat";
    private static final String NEW_FILE_NAME = "temp/copy_new.dat";

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        Path path = Path.of(FILE_NAME);
        Path newPath = Path.of(NEW_FILE_NAME);

        Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
