package me.concurrency.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static me.util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue{
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while(queue.size() >= max) {
                log("[put] 큐가 가득 참, 생산자 대기");
                try {
                    producerCondition.await();
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, consumer signal 호출");
            consumerCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while(queue.isEmpty()) {
                log("[take] 큐가 비었음, 소비자 대기");
                try {
                    consumerCondition.await();
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log("[take] 소비자 데이터 가져감");
            String data =  queue.poll();
            log("[take] producer signal 호출");
            producerCondition.signal();
            return data;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
