package me.web.v2;

import static me.web.common.PrintUtil.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerV2 {

    private final int port;
    private final ExecutorService executorService;

    public HttpServerV2(int port) {
        this.port = port;
        executorService = Executors.newFixedThreadPool(10);
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("Start Server - Port: " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV2 runnable = new HttpRequestHandlerV2(socket);
            executorService.submit(runnable);
        }
    }

}
