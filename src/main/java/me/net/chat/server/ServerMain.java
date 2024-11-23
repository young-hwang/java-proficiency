package me.net.chat.server;

import me.net.socket.SessionManagerV6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static me.net.socket.SocketCloseUtil.closeAll;
import static me.util.MyLogger.log;

public class ServerMain {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException, InterruptedException {
        log("Start Server");
        final SessionManager sessionManager = new SessionManager();
        final ServerSocket serverSocket = new ServerSocket(PORT);
        log("Server Socket Listening Port: " + PORT);

        ShutDownHook shutDownHook = new ShutDownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutDownHook, "shutdown"));

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                log("Connect Socket " + socket);
                Session session = new Session(socket, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("Close Server Socket: " + e);
        }
    }

    public static class Session implements Runnable {
        private final Socket socket;
        private final DataInputStream dataInputStream;
        private final DataOutputStream dataOutputStream;
        private final SessionManagerV6 sessionManager;
        private boolean closed = false;

        public Session(Socket socket, SessionManagerV6 sessionManager) throws IOException {
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.sessionManager = sessionManager;
            sessionManager.add(this);
        }

        @Override
        public void run() {
            try {
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
            } catch (IOException e) {
                log(e);
            } finally {
                sessionManager.remove(this);
                close();
            }
        }

        public synchronized void close() {
            if (closed) {
                return;
            }
            closeAll(socket, dataInputStream, dataOutputStream);
            closed = true;
            log("close connection: " + socket);
        }
    }

    private static class ShutDownHook implements Runnable{
        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;

        public ShutDownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("start to close server socket and session manager");
            try {
                sessionManager.closeAll();
                serverSocket.close();
                Thread.sleep(1000);
            } catch (Exception e) {
                log("close server socket: " + e);
            }
            log("close server socket and session manager");
        }
    }
}
