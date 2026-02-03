package me.web.v6;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;

public class SiteController {
    public void site1(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>site1</h1>");
    }

    public void site2(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>site2</h1>");
    }
}
