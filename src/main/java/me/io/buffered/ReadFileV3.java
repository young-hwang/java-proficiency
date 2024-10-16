package me.io.buffered;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static me.io.buffered.BufferedConst.DEFAULT_BUFFER_SIZE;
import static me.io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        BufferedInputStream bis = new BufferedInputStream(fis, DEFAULT_BUFFER_SIZE);
        long startTime = System.currentTimeMillis();
        int fileSize = 0;
        int data;
        while((data = bis.read()) != -1) {
            fileSize++;
        }
        bis.close();
        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time take: " + (endTime - startTime) + "ms") ;

        int a = ~0;
        String binaryString = Integer.toBinaryString(a);
        System.out.println("binaryString = " + binaryString);
        int b = a + 1;
        String binaryString1 = Integer.toBinaryString(b);
        System.out.println("binaryString1 = " + binaryString1);
    }
}
