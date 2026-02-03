package me.io.net.exception.connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SoTimeoutServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(45678);
        Socket socket = serverSocket.accept();
        Thread.sleep(1000000);
    }
}
