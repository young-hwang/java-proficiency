package me.web.httpserver;

import java.io.IOException;

public interface HttpServlet {
    void service(HttpRequest request, HttpResponse response);
}
