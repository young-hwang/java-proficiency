package me.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue{
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while(queue.size() >= max) {
            log("[put] 큐가 가득 참, 생산자 대기");
//            sleep(1000);
            try {
                this.wait();
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("[put] 생산자 데이터 저장, notify 호출");
        this.notify();
    }

    @Override
    public synchronized String take() {
        while(queue.isEmpty()) {
            log("[take] 큐가 비었음, 소비자 대기");
//            sleep(1000);
            try {
                this.wait();
                log("[take] 소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        log("[take] 소비자 데이터 가져감");
        String data =  queue.poll();
        this.notify();
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
