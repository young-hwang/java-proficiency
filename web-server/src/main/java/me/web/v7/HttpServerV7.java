package me.web.v7;

import me.web.httpserver.ServletManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static me.web.common.PrintUtil.log;

public class HttpServerV7 {
    private final int port;
    private final ExecutorService executor;
    private final ServletManager servletManager;

    public HttpServerV7(int port, ServletManager servletManager) {
        this.port = port;
        this.servletManager = servletManager;
        this.executor = Executors.newFixedThreadPool(10);
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("Start Server - Port: " + port);
        while(true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV7 httpRequestHandler = new HttpRequestHandlerV7(socket, servletManager);
            executor.submit(httpRequestHandler);
        }
    }
}
