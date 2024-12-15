package me.web.v4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static me.web.common.PrintUtil.log;

public class HttpServerV4 {

    private final int port;
    private final ExecutorService executorService;

    public HttpServerV4(int port) {
        this.port = port;
        executorService = Executors.newFixedThreadPool(10);
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("Start Server - Port: " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV4 runnable = new HttpRequestHandlerV4(socket);
            executorService.submit(runnable);
        }
    }

}
