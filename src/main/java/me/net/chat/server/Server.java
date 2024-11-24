package me.net.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static me.util.MyLogger.log;

public class Server {
    private final int port;
    private final SessionManager sessionManager;
    private ServerSocket serverSocket;

    public Server(int port, SessionManager sessionManager) {
        this.port = port;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(port);
        while(true) {
            Socket socket = serverSocket.accept();
            log("Connect Socket " + socket);
            Session session = new Session(socket, sessionManager);
            Thread thread = new Thread(session);
            thread.start();
        }
    }

    public synchronized void close() throws IOException {
        this.serverSocket.close();
    }
}
