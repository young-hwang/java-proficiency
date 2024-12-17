package me.web.httpserver.servlet;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;
import me.web.httpserver.HttpServlet;

import java.io.IOException;

public class NotFoundServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1>404 Page Not Found</h1>");
    }
}
