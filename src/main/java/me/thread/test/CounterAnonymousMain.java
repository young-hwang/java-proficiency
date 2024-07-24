package me.thread.test;

import static me.util.MyLogger.log;

public class CounterAnonymousMain {
    public static void main(String[] args) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for (int i = 1; i <= 5; i++) {
//                        log("value: " + i);
//                        Thread.sleep(1000);
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }

        Thread thread = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    log("value: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
}
