package me.webservice;

import com.sun.net.httpserver.HttpServer;
import me.web.httpserver.ServletManager;
import me.web.httpserver.servlet.DiscardServlet;
import me.web.httpserver.servlet.annotation.AnnotationServletV3;
import me.web.v9.HttpServerV9;

import java.io.IOException;
import java.util.List;

public class MemberServiceMain {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        MemberController memberController = new MemberController(new FileMemberRepository());
        AnnotationServletV3 servlet = new AnnotationServletV3(List.of(memberController));
        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(servlet);
        servletManager.add("/favicon.ico", new DiscardServlet());

        HttpServerV9 httpServerV9 = new HttpServerV9(PORT, servletManager);
        httpServerV9.start();
    }

}
