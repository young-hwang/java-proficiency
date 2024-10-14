package me.io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static me.io.buffered.BufferedConst.DEFAULT_BUFFER_SIZE;
import static me.io.buffered.BufferedConst.FILE_NAME;
import static me.io.buffered.BufferedConst.FILE_SIZE;

public class CreateFileV2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int bufferIndex = 0;

        for (int i = 0; i < FILE_SIZE; i++) {
            buffer[bufferIndex++] = 1;

            if(bufferIndex == DEFAULT_BUFFER_SIZE) {
                fos.write(buffer);
                bufferIndex = 0;
            }
        }

        // 버퍼가 가득차지 않고 끝나는 경우
        if (bufferIndex > 0) {
            fos.write(buffer, 0, bufferIndex);
        }

        fos.close();
        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time take: " + (endTime - startTime) + "ms") ;
    }
}
