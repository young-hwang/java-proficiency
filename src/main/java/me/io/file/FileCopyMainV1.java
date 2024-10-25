package me.io.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyMainV1 {
    private static final String FILE_NAME = "temp/copy.dat";
    private static final String NEW_FILE_NAME = "temp/copy_new.dat";

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(FILE_NAME);
        FileOutputStream fos = new FileOutputStream(NEW_FILE_NAME);

        byte[] bytes = fis.readAllBytes();
        fos.write(bytes);

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
