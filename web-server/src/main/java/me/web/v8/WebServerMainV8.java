package me.web.v8;

import me.web.httpserver.ServletManager;
import me.web.httpserver.servlet.DiscardServlet;
import me.web.httpserver.servlet.annotation.AnnotationServletV2;
import me.web.v7.HttpServerV7;

import java.io.IOException;
import java.util.List;

public class WebServerMainV8 {

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteController(), new SearchController());
        ServletManager servletManager = new ServletManager();
        servletManager.add("/favicon.ico", new DiscardServlet());
        servletManager.setDefaultServlet(new AnnotationServletV2(controllers));
        new HttpServerV8(8080, servletManager).start();
    }
}
