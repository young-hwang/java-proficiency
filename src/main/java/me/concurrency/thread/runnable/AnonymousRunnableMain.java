package me.concurrency.thread.runnable;

import static me.util.MyLogger.log;

public class AnonymousRunnableMain {
    public static void main(String[] args) {
        log("main() start");
        new Thread(new Runnable() {
            @Override
            public void run() {
                log("run() start");
            }
        }).start();

        // lambda 로 전달
        // new Thread(() -> log("run() start")).start();
        log("main() ene");
    }
}
