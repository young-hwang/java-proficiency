package me.web.v2;

import java.io.IOException;

public class WebServerMainV2 {

    public static void main(String[] args) throws IOException {
        new HttpServerV2(8080).start();
    }
}
