package me.thread.yield;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class YieldMain {
    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for(int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                log(Thread.currentThread().getName() + " - " + i);
//                sleep(1); // sleep 추가 테스트
                Thread.yield(); // yield 추가 테스트
            }
        }
    }
}
