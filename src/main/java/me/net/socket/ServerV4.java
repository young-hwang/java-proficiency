package me.net.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static me.util.MyLogger.log;

public class ServerV4 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException, InterruptedException {
        log("Start Server");
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            log("Server Socket Listening Port: " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                log("Connect Socket " + socket);
                Session session = new Session(socket);
                Thread thread = new Thread(session);
                thread.start();
            }
        }
    }

    public static class Session implements Runnable {
        private final Socket socket;

        public Session(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            DataInputStream dataInputStream = null;
            DataOutputStream dataOutputStream = null;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                SocketCloseUtil.closeAll(socket, dataInputStream, dataOutputStream);
                log("End Connection");
            }
        }
    }
}
