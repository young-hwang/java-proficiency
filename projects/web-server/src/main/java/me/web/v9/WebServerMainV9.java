package me.web.v9;

import me.web.httpserver.ServletManager;
import me.web.httpserver.servlet.DiscardServlet;
import me.web.httpserver.servlet.annotation.AnnotationServletV3;

import java.io.IOException;
import java.util.List;

public class WebServerMainV9 {

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteController(), new SearchController());
        ServletManager servletManager = new ServletManager();
        servletManager.add("/favicon.ico", new DiscardServlet());
        servletManager.setDefaultServlet(new AnnotationServletV3(controllers));
        new HttpServerV9(8080, servletManager).start();
    }
}
