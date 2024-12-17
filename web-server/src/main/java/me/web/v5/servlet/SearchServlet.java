package me.web.v5.servlet;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;
import me.web.httpserver.HttpServlet;

import java.io.IOException;

public class SearchServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatusCode(200);
        response.writeBody("<h1>search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + request.getParameter("q") + "</li>");
        response.writeBody("</ul>");
    }
}
