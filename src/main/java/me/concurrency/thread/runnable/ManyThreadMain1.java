package me.concurrency.thread.runnable;

import static me.util.MyLogger.log;

public class ManyThreadMain1 {
    public static void main(String[] args) {
        log("main start");
        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thread1 = new Thread(helloRunnable);
        thread1.start();
        Thread thread2 = new Thread(helloRunnable);
        thread2.start();
        Thread thread3 = new Thread(helloRunnable);
        thread3.start();

        log("main end");
    }
}
