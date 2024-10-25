package me.io.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class CreateCopyFile {
    private static final int FILE_SIZE = 1024 * 1024 * 200; // 200MB
    private static final String FILE_NAME = "temp/copy.dat";

    public static void main(String[] args) throws IOException {
        Path path = Path.of(FILE_NAME);
        long startTime = System.currentTimeMillis();

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        byte[] buffers = new byte[FILE_SIZE];
        fos.write(buffers);
        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
