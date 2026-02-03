package me.io.net.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import static me.util.MyLogger.log;

public class ClientV1 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("Start Client");
        Socket socket = new Socket("localhost", PORT);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            log("Connect Socket: " + socket);
            
            // 서버에 문자 보내기
            String toSend = "Hello";
            dataOutputStream.writeUTF(toSend);
            log("client -> server: " + toSend);
            
            // 서버로부터 문자 받기
            String received = dataInputStream.readUTF();
            log("client <- server: " + received);

            log("close connection" + socket);
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
    }
}
