package me.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static me.chat.common.MyLogger.log;

public class Server {
    private final int port;
    private final SessionManager sessionManager;
    private final CommandManager commandManager;
    private ServerSocket serverSocket;

    public Server(int port, SessionManager sessionManager, CommandManager commandManager) {
        this.port = port;
        this.sessionManager = sessionManager;
        this.commandManager = commandManager;
    }

    public void start() throws IOException {
        log("Start Server");
        this.serverSocket = new ServerSocket(port);
        log("Start Listening Port: " + port);

        addShutdownHook();
        running();
    }

    private void addShutdownHook() {
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook));
    }

    private void running() throws IOException {
        while(true) {
            Socket socket = serverSocket.accept();
            log("Connect Socket " + socket);
            Session session = new Session(socket, sessionManager, commandManager);
            Thread thread = new Thread(session);
            thread.start();
        }
    }

    public synchronized void close() throws IOException {
        this.serverSocket.close();
    }

    static class ShutdownHook implements Runnable  {
        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("Running shutdown hook");
            try {
                sessionManager.closeAll();
                serverSocket.close();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}
