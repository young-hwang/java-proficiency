package me.concurrency.thread.cas.atomicoperation;

public class VolatileInteger implements IncrementInteger {
    private volatile int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
