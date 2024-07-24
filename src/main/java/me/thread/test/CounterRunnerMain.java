package me.thread.test;

import static me.util.MyLogger.log;

public class CounterRunnerMain {
    public static void main(String[] args) {
        Thread thread = new Thread(new CounterRunnable(), "worker");
        thread.start();
    }

    private static class CounterRunnable implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    log("value: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
