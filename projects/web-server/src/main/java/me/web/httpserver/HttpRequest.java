package me.web.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpRequest {
    private String method;
    private String path;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(reader);
        parseBody(reader);
    }

    private void parseBody(BufferedReader reader) throws IOException {
        if (!headers.containsKey("Content-Length")) {
            return;
        }

        int contentLength = Integer.parseInt(headers.get("Content-Length"));
        char[] buffer = new char[contentLength];
        int read = reader.read(buffer);
        if (read != contentLength) {
            throw new IOException("Failed to read entire body. Expected " + contentLength + " bytes but got " + read);
        }
        String body = new String(buffer);
        System.out.println("HTTP Message body = " + body);
        String contentType = headers.get("Content-Type");
        if ("application/x-www-form-urlencoded".equals(contentType)) {
            parseQueryParameters(body);
        }

    }

    private void parseHeaders(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            String[] headerParts = line.split(":");
            headers.put(headerParts[0].trim(), headerParts[1].trim());
        }
    }

    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        if(requestLine == null) {
            throw new IOException("Request line is empty");
        }
        String[] parts = requestLine.split(" ");
        if(parts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }
        method = parts[1];

        String[] pathParts = parts[1].split("\\?");
        path = pathParts[0];

        if (pathParts.length > 1) {
            parseQueryParameters(pathParts[1]);
        }

    }

    private void parseQueryParameters(String pathPart) {
        String[] parts = pathPart.split("&");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            String key = URLDecoder.decode(keyValue[0], UTF_8);
            String value = URLDecoder.decode(keyValue[1], UTF_8);
            queryParameters.put(key, value);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String key) {
        return queryParameters.get(key);
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", queryParameters=" + queryParameters +
                ", headers=" + headers +
                '}';
    }
}
