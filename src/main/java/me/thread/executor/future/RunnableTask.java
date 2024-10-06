package me.thread.executor.future;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class RunnableTask implements Runnable {
    private final String name;
    private int sleepMs = 1000;

    public RunnableTask(String name) {
        this.name = name;
    }

    public RunnableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public void run() {
        log(name + " 실행");
        sleep(this.sleepMs);
        log(name + " 완료");
    }
}
