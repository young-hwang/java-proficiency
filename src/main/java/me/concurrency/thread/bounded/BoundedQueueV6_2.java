package me.concurrency.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static me.util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {
    private final BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = this.queue.offer(data);
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return this.queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
