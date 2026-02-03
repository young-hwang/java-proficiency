package me.concurrency.thread.runnable;

import static me.util.MyLogger.log;

public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        log("Hello Runnable");
    }
}
