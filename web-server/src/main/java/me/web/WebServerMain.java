package me.web;

import java.io.IOException;
import me.web.v1.HttpServerV1;

public class WebServerMain {

    public static void main(String[] args) throws IOException {
        new HttpServerV1(8080).start();
    }
}
