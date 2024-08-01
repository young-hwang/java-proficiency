package me.thread.volatile1;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        log("runFlag = " + myTask.runFlag);
        thread.start();

        sleep(1000);
        myTask.runFlag = false;
        log("runFlag = " + myTask.runFlag);
    }

    private static class MyTask implements Runnable {
        // volatile 일 경우 쓰레드가 종료되지만 없을 경우 종료 되지 않음
        // 메모리 가시성(Memory Visibility) 문제
        // 멀티스레드 환경에서 한 스레드가 변경한 값이 다른 스레드에서 언제 보이는지에 대한 문제
        // 이름 그대로 메모리에 변경한 값이 보이는가? 보이지 않는가? 의 문제
        public boolean runFlag = true;
//        public volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");
            while(runFlag) {

            }
            log("task 종료");
        }
    }
}
