package me.io.net.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static me.util.MyLogger.log;

public class ServerV2 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("Start Server");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("Server Socket Listening Port: " + PORT);
        Socket socket = serverSocket.accept();
        log("Connect Socket " + socket);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        String received = "";
        while (true) {
            // 클라이언트로부터 문자 받기
            received = dataInputStream.readUTF();
            log("server <- client: " + received);

            // 클라이언트에게 문자 보내기
            String toSend = received + " World";
            dataOutputStream.writeUTF(toSend);
            log("server -> client: " + toSend);

            if (received.equals("bye")) {
                break;
            }
        }

        log("close connection: " + socket);
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
        serverSocket.close();
    }
}
