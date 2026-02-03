package me.io.net.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SoTimeoutClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 45678);
        InputStream input = socket.getInputStream();
        try {
            socket.setSoTimeout(3000);  // 타임아웃 시간 설정
            int read = input.read();    // 기본 무한 대기
            System.out.println("read = " + read);
        } catch (Exception e) {
            e.printStackTrace();
        }

        socket.close();
    }
}
