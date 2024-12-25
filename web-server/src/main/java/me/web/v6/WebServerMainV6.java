package me.web.v6;

import me.web.httpserver.ServletManager;
import me.web.httpserver.servlet.DiscardServlet;
import me.web.httpserver.servlet.reflection.ReflectionServlet;
import me.web.v5.servlet.HomeServlet;

import java.io.IOException;
import java.util.List;

public class WebServerMainV6 {

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteController(), new SearchController());
        ServletManager servletManager = new ServletManager();
        servletManager.add("/", new HomeServlet());
        servletManager.add("/favicon.ico", new DiscardServlet());
        servletManager.setDefaultServlet(new ReflectionServlet(controllers));
        new HttpServerV6(8080, servletManager).start();
    }
}
