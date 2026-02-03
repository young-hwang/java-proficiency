package me.concurrency.thread.cas.atomicoperation;

import static me.util.MyLogger.log;

public class IncrementThreadPerformanceMain {
    public static final Long COUNT = 100_000_000L;

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
        long startMs = System.currentTimeMillis();

        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long endMs = System.currentTimeMillis();
        log(incrementInteger.getClass().getName() + " : " + (endMs - startMs) + "ms");
    }
}
