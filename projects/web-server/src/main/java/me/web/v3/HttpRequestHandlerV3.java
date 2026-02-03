package me.web.v3;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.web.common.PrintUtil.log;
import static me.web.common.ThreadUtil.sleep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;

public class HttpRequestHandlerV3 implements Runnable {
    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        process();
    }

    private void process() {
        try (socket;
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), UTF_8));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8);
        ) {
            String requestString = requestToString(reader);
            log("Http request: " + requestString);
            if (requestString.contains("/favicon.ico")) {
                log("Request favicon");
                return;
            }
            log("Print HTTP request info");
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET /")) {
                home(writer);
            } else {
                notFound(writer);
            }
            log("Creating HTTP request");
            responseToClient(writer);
            log("Complete HTTP request");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void notFound(PrintWriter writer) {
        writer.println("HTTP/1.1 404 Not Found");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>404 Page Not Found</h1>");
    }

    private void home(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>home</h1>");
        writer.println("<ul>");
        writer.println("<li><a href='/site1'>site1</a></li>");
        writer.println("<li><a href='/site2'>site2</a></li>");
        writer.println("<li><a href='/search?q=hello'>search</a></li>");
        writer.println("</ul>");
    }

    private void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex);
        String query = requestString.substring(startIndex + 2, endIndex);
        String decode = URLDecoder.decode(query, UTF_8);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>search</h1>");
        writer.println("<ul>");
        writer.println("<li>query: " + query + "</li>");
        writer.println("<li>decode: " + decode + "</li>");
        writer.println("</ul>");
    }

    private void site2(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>site2</h1>");
    }

    private void site1(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>site1</h1>");
    }

    private void responseToClient(PrintWriter writer) {
        writer.flush();
    }

    private String requestToString(BufferedReader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
