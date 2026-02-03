package me.web.v7;

import me.web.httpserver.ServletManager;
import me.web.httpserver.servlet.DiscardServlet;
import me.web.httpserver.servlet.annotation.AnnotationServletV1;

import java.io.IOException;
import java.util.List;

public class WebServerMainV7 {

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteController(), new SearchController());
        ServletManager servletManager = new ServletManager();
        servletManager.add("/favicon.ico", new DiscardServlet());
        servletManager.setDefaultServlet(new AnnotationServletV1(controllers));
        new HttpServerV7(8080, servletManager).start();
    }
}
