package me.concurrency.thread.threadgroup;

public class SleepThread extends Thread {
    private final int sleep;

    public SleepThread(int sleep) {
        this.sleep = sleep;
    }

    @Override
    public void run() {
        try {
            System.out.println("Sleeping Thread-" +Thread.currentThread().getId());
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
