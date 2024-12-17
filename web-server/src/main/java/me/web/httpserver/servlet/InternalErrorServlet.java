package me.web.httpserver.servlet;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;
import me.web.httpserver.HttpServlet;

import java.io.IOException;

public class InternalErrorServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatusCode(500);
        response.writeBody("<h1>Internal error</h1>");
    }
}
