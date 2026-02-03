package me.io.net.exception.close.reset;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static me.util.MyLogger.log;

public class ResetCloseServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();
        log("Connected Socket: " + socket);
        socket.close();
        serverSocket.close();
        log("Closed Socket: " + socket);
    }
}
