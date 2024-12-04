package me.web.v3;

import static me.web.common.PrintUtil.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerV3 {

    private final int port;
    private final ExecutorService executorService;

    public HttpServerV3(int port) {
        this.port = port;
        executorService = Executors.newFixedThreadPool(10);
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("Start Server - Port: " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV3 runnable = new HttpRequestHandlerV3(socket);
            executorService.submit(runnable);
        }
    }

}
