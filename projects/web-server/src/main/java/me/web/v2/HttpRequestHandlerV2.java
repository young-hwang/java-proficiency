package me.web.v2;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.web.common.PrintUtil.log;
import static me.web.common.ThreadUtil.sleep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpRequestHandlerV2 implements Runnable {
    private final Socket socket;

    public HttpRequestHandlerV2(Socket socket) {
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
            System.out.println(requestString);
            log("Creating HTTP request");
            sleep(5000);
            responseToClient(writer);
            log("Complete HTTP request");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void responseToClient(PrintWriter writer) {
        String body = "<h1>Hello World!</h1>";
        int length = body.getBytes(UTF_8).length;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 200 OK\r\n");
        stringBuilder.append("Content-Type: text/html\r\n");
        stringBuilder.append("Content-Length: ").append(length).append("\r\n");
        stringBuilder.append("\r\n");
        stringBuilder.append(body);
        writer.println(stringBuilder.toString());
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
