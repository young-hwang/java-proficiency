package me.concurrency.thread.cas.atomicoperation;

import java.util.ArrayList;
import java.util.List;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class IncrementThreadMain {
    public static final int THREAD_COUNT = 10000;

    public static void main(String[] args) throws InterruptedException {
        IncrementInteger basicInteger = new BasicInteger();
        IncrementInteger volatileInteger = new VolatileInteger();
        IncrementInteger synchronizedInteger = new SynchronizedInteger();
        IncrementInteger atomicInteger = new MyAtomicInteger();

        test(basicInteger);
        test(volatileInteger);
        test(synchronizedInteger);
        test(atomicInteger);
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sleep(10); // 다른 스레드와 동시 실행을 위해 추가
                incrementInteger.increment();
            }
        };

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        log(incrementInteger.getClass().getName() + " result " + incrementInteger.get());
    }
}
