package me.concurrency.thread.runnable;

import static me.util.MyLogger.log;

public class InnerRunnableMain {
    public static void main(String[] args) {
        log("main() start");
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        log("main() ene");
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log("run()");
        }
    }
}
