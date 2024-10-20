package me.io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.io.text.TextConst.FILE_NAME;

public class ReaderWriterMainV3 {
    public static void main(String[] args) throws IOException {
        String writeString = "abc";
        System.out.println("write String = " + writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        fw.write(writeString);
        fw.close();

        // 파일에 읽기
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        int ch;
        while( (ch = fr.read()) != -1) {
            sb.append((char)ch);
        }
        fr.close();
        System.out.println("read String = " + sb);
    }
}
