package me.thread.control;

import static me.util.MyLogger.log;

public class ThreadInfoMain {
    public static void main(String[] args) {
        // main 스레드
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        log("mainThread.threadId() = " + mainThread.threadId());
        log("mainThread.getName() = " + mainThread.getName());
        log("mainThread.getPriority() = " + mainThread.getPriority());
        log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());
        log("mainThread.getState() = " + mainThread.getState());

        Thread myThread = new Thread (() -> { }, "myThread" );
        log("mainThread = " + myThread);
        log("mainThread.threadId() = " + myThread.threadId());
        log("mainThread.getName() = " + myThread.getName());
        log("mainThread.getPriority() = " + myThread.getPriority());
        log("mainThread.getThreadGroup() = " + myThread.getThreadGroup());
        log("mainThread.getState() = " + myThread.getState());
    }
}
