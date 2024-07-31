package me.thread.interrupt;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class ThreadStopMain4 {
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
            // isInterrupted: interrupted 상태 변경하지 않음
            // interrupted: interrupted 상태 이면 true 반환 후 false 전환
            // 스레드의 언터럽트 상태를 정상으로 돌리지 않으면 이후에도 계속 인터럽트가 발생
            while (!Thread.interrupted()) {
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
