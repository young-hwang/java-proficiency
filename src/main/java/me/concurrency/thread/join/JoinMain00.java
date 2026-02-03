package me.concurrency.thread.join;

import me.util.ThreadUtils;

import static me.util.MyLogger.log;

public class JoinMain00 {
    public static void main(String[] args) {
        log("start");
        Job job = new Job();
        Thread thread1 = new Thread(job, "thread-1");
        Thread thread2 = new Thread(job, "thread-2");
        thread1.start();
        thread2.start();
        log("end");
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            log("작업 시작");
            ThreadUtils.sleep(2000);
            log("작업 종료");
        }
    }
}
