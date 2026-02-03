package me.web.v5;

import me.web.httpserver.ServletManager;
import me.web.httpserver.servlet.DiscardServlet;
import me.web.v5.servlet.HomeServlet;
import me.web.v5.servlet.SearchServlet;
import me.web.v5.servlet.Site1Servlet;
import me.web.v5.servlet.Site2Servlet;

import java.io.IOException;

public class WebServerMainV5 {

    public static void main(String[] args) throws IOException {
        ServletManager servletManager = new ServletManager();
        servletManager.add("/", new HomeServlet());
        servletManager.add("/site1", new Site1Servlet());
        servletManager.add("/site2", new Site2Servlet());
        servletManager.add("/search", new SearchServlet());
        servletManager.add("/favicon.ico", new DiscardServlet());
        new HttpServerV5(8080, servletManager).start();
    }
}
