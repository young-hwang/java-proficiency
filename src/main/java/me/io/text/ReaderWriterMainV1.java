package me.io.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ReaderWriterMainV1 {
    public static void main(String[] args) throws IOException {
        System.out.println(Charset.defaultCharset());

        String writeString = "ABC";
        byte[] bytes = writeString.getBytes(StandardCharsets.UTF_8);
        System.out.println("write string: " + writeString);
        System.out.println("write byte: " + Arrays.toString(bytes));
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(Integer.toBinaryString(bytes[i] & 0xFF));
        }

        // 파일 쓰기
        FileOutputStream fileOutputStream = new FileOutputStream(TextConst.FILE_NAME);
        fileOutputStream.write(bytes);
        fileOutputStream.close();

        // 파일 읽기
        FileInputStream fileInputStream = new FileInputStream(TextConst.FILE_NAME);
        byte[] readBytes = fileInputStream.readAllBytes();
        fileInputStream.close();

        // byte -> String UTF-8 인코딩
        String readString = new String(readBytes, StandardCharsets.UTF_8);
        System.out.println("readString = " + readString);
        System.out.println("readBytes = " + Arrays.toString(readBytes));
    }
}
