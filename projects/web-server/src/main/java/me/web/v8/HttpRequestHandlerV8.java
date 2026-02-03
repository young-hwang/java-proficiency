package me.web.v8;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;
import me.web.httpserver.ServletManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.web.common.PrintUtil.log;

public class HttpRequestHandlerV8 implements Runnable {
    private final Socket socket;
    private final ServletManager servletManager;

    public HttpRequestHandlerV8(Socket socket, ServletManager servletManager) {
        this.socket = socket;
        this.servletManager = servletManager;
    }

    @Override
    public void run() {
        process();
    }

    private void process() {
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)) {
            HttpRequest httpRequest = new HttpRequest(reader);
            HttpResponse httpResponse = new HttpResponse(writer);

            log("Http request: " + httpRequest.getPath());
            servletManager.execute(httpRequest, httpResponse);
            httpResponse.flush();
            log("Complete HTTP request");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
