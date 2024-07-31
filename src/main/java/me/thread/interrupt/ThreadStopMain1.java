package me.thread.interrupt;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class ThreadStopMain1 {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);

        thread.start();

        sleep(4000);
        log("작업 중단 지시 runFlag=false");
        myTask.runFlag = false;
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("작업중");
                sleep(3000);
            }
            log("작업종료");
        }
    }
}
