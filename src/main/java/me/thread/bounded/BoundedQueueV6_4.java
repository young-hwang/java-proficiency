package me.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static me.util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue {
    private final BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        this.queue.add(data); // java.lang.IllegalStateException: Queue full
    }

    @Override
    public String take() {
            return this.queue.remove(); // java.util.NoSuchElementException
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
