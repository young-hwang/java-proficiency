package me.web.v4;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.web.common.PrintUtil.log;

public class HttpRequestHandlerV4 implements Runnable {
    private final Socket socket;

    public HttpRequestHandlerV4(Socket socket) {
        this.socket = socket;
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
            if (httpRequest.getPath().equals("/favicon.ico")) {
                log("Request favicon");
                return;
            }
            log("Print HTTP request info");

            if (httpRequest.getPath().equals("/site1")) {
                site1(httpResponse);
            } else if (httpRequest.getPath().equals("/site2")) {
                site2(httpResponse);
            } else if (httpRequest.getPath().equals("/search")) {
                search(httpRequest, httpResponse);
            } else if (httpRequest.getPath().equals("/")) {
                home(httpResponse);
            } else {
                notFound(httpResponse);
            }
            log("Creating HTTP request");
            httpResponse.flush();
            log("Complete HTTP request");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void notFound(HttpResponse writer) {
        writer.setStatusCode(404);
        writer.writeBody("<h1>404 Page Not Found</h1>");
    }

    private void home(HttpResponse writer) {
        writer.setStatusCode(200);
        writer.writeBody("<h1>home</h1>");
        writer.writeBody("<ul>");
        writer.writeBody("<li><a href='/site1'>site1</a></li>");
        writer.writeBody("<li><a href='/site2'>site2</a></li>");
        writer.writeBody("<li><a href='/search?q=hello'>search</a></li>");
        writer.writeBody("</ul>");
    }

    private void search(HttpRequest request, HttpResponse writer) {
        writer.setStatusCode(200);
        writer.writeBody("<h1>search</h1>");
        writer.writeBody("<ul>");
        writer.writeBody("<li>query: " + request.getParameter("q") + "</li>");
        writer.writeBody("</ul>");
    }

    private void site2(HttpResponse writer) {
        writer.writeBody("<h1>site2</h1>");
    }

    private void site1(HttpResponse writer) {
        writer.writeBody("<h1>site1</h1>");
    }
}
