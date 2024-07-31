package me.thread.interrupt;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class ThreadStopMain3 {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);

        thread.start();

        sleep(100);
        log("작업 중단 지시 interrupt");
        thread.interrupt();
        log("thread state=" + thread.isInterrupted());
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            // interrupt 체크하지 않음
            while (!Thread.currentThread().isInterrupted()) {
                log("작업중");
            }
            log("work thread interrupted=" + Thread.currentThread().isInterrupted());
            try {
                // interrupt 체크하지 않음
                log("자원 정리 중");
                Thread.sleep(3000);
                log("자원 정리 완료");
                log("work thread interrupted1=" + Thread.currentThread().isInterrupted());
            } catch (InterruptedException e) {
                log("work thread interrupted2=" + Thread.currentThread().isInterrupted());
                log("interrupt message " + e.getMessage());
                log("state=" + Thread.currentThread().getState());
            }
            log("작업종료");
        }
    }
}
