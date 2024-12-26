package me.web.v9;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;
import me.web.httpserver.servlet.annotation.Mapping;

public class SearchController {
    @Mapping("/search")
    public void search(HttpRequest request, HttpResponse response) {
        response.setStatusCode(200);
        response.writeBody("<h1>search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + request.getParameter("q") + "</li>");
        response.writeBody("</ul>");
    }
}
