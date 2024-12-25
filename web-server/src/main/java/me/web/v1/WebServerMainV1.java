package me.web.v1;

import java.io.IOException;

public class WebServerMainV1 {

    public static void main(String[] args) throws IOException {
        new HttpServerV1(8080).start();
    }
}
