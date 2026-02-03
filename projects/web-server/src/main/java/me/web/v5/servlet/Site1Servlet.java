package me.web.v5.servlet;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;
import me.web.httpserver.HttpServlet;

public class Site1Servlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatusCode(200);
        response.writeBody("<h1>site1</h1>");
    }
}
