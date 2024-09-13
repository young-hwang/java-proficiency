package me.thread.bounded;

import static me.util.MyLogger.log;

public class ConsumerTask implements Runnable {
    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도]     ? <- " + this.queue);
        String data = queue.take();
        log("[소비 완료] " + data + " <- " + this.queue);
    }
}
