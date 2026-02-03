package me.web.v3;

import java.io.IOException;

public class WebServerMainV3 {

    public static void main(String[] args) throws IOException {
        new HttpServerV3(8080).start();
    }
}
