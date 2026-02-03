package me.concurrency.thread.interrupt;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class ThreadStopMain2 {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);

        thread.start();

        sleep(4000);
        log("작업 중단 지시 interrupt");
        thread.interrupt();
        log("thread state=" + thread.isInterrupted());
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true;

        @Override
        public void run() {
            try {
                // interrupt 체크하지 않음
                while (true) {
                    log("작업중");
                    Thread.sleep(3000); // 여기서만 인터럽트 발생
                }
            } catch (InterruptedException e) {
                log("work thread interrupted" + Thread.currentThread().isInterrupted());
                log("interrupt message" + e.getMessage());
                log("state=" + Thread.currentThread().getState());
            }
            log("작업종료");
        }
    }
}
