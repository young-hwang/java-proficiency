package me.thread.atomicoperation;

public class SynchronizedInteger implements IncrementInteger {
    private int value;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
