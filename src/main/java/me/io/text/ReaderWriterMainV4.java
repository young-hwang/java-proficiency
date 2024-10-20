package me.io.text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.io.text.TextConst.BUFFER_SIZE;
import static me.io.text.TextConst.FILE_NAME;

public class ReaderWriterMainV4 {
    public static void main(String[] args) throws IOException {
        String writeString = "abc\n가나다";
        System.out.println("write String = " + writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
        bw.write(writeString);
        bw.close();

        // 파일에 읽기
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        System.out.println("read String = " + sb);
    }
}
