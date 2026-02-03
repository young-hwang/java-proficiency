package me.web.v6;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;

public class SearchController {
    public void search(HttpRequest request, HttpResponse response) {
        response.setStatusCode(200);
        response.writeBody("<h1>search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + request.getParameter("q") + "</li>");
        response.writeBody("</ul>");
    }
}
