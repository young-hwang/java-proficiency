package me.concurrency.thread.runnable;

import static me.util.MyLogger.log;

public class ManyThreadMain2 {
    public static void main(String[] args) {
        log("main start");
        HelloRunnable helloRunnable = new HelloRunnable();
        for(int i = 0; i < 100; i++) {
            new Thread(helloRunnable).start();
        }
        log("main end");
    }
}
