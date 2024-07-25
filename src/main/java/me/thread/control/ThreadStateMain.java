package me.thread.control;

import static me.util.MyLogger.log;

public class ThreadStateMain {
    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new MyRunner(), "myThread");
            thread.start();
            log("main");
            log("MyRunner.getState()" + thread.getState());
            Thread.sleep(1000);
            log("MyRunner.getState()" + thread.getState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyRunner implements Runnable {
        @Override
        public void run() {
            try {
                log("start");
                log("myRunner.state2" + Thread.currentThread().getState());
                log("sleep() start");
                Thread.sleep(3000);
                log("sleep() end");
                log("myRunner.state2" + Thread.currentThread().getState());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
