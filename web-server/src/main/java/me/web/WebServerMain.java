package me.web;

import java.io.IOException;

import me.web.httpserver.ServletManager;
import me.web.httpserver.servlet.DiscardServlet;
import me.web.v1.HttpServerV1;
import me.web.v2.HttpServerV2;
import me.web.v3.HttpServerV3;
import me.web.v4.HttpServerV4;
import me.web.v5.HttpServerV5;
import me.web.v5.servlet.HomeServlet;
import me.web.v5.servlet.SearchServlet;
import me.web.v5.servlet.Site1Servlet;
import me.web.v5.servlet.Site2Servlet;

public class WebServerMain {

    public static void main(String[] args) throws IOException {
//        new HttpServerV1(8080).start();
//        new HttpServerV2(8080).start();
//        new HttpServerV3(8080).start();
//        new HttpServerV4(8080).start();
        ServletManager servletManager = new ServletManager();
        servletManager.add("/", new HomeServlet());
        servletManager.add("/site1", new Site1Servlet());
        servletManager.add("/site2", new Site2Servlet());
        servletManager.add("/search", new SearchServlet());
        servletManager.add("/favicon.ico", new DiscardServlet());
        new HttpServerV5(8080, servletManager).start();
    }
}
