package me.web.v4;

import java.io.IOException;

public class WebServerMainV4 {

    public static void main(String[] args) throws IOException {
        new HttpServerV4(8080).start();
    }
}
