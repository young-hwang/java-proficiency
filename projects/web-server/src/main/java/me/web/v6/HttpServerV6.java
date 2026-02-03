package me.web.v6;

import me.web.httpserver.ServletManager;
import me.web.v5.HttpRequestHandlerV5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static me.web.common.PrintUtil.log;

public class HttpServerV6 {

    private final int port;
    private final ExecutorService executorService;
    private final ServletManager servletManager;

    public HttpServerV6(int port, ServletManager servletManager) {
        this.port = port;
        executorService = Executors.newFixedThreadPool(10);
        this.servletManager = servletManager;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("Start Server - Port: " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV5 runnable = new HttpRequestHandlerV5(socket, servletManager);
            executorService.submit(runnable);
        }
    }

}
