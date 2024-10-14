package me.io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

import static me.io.buffered.BufferedConst.DEFAULT_BUFFER_SIZE;
import static me.io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();
        int fileSize = 0;
        int size;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while((size = fis.read(buffer)) != -1) {
            fileSize++;
        }
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time take: " + (endTime - startTime) + "ms") ;
    }
}
