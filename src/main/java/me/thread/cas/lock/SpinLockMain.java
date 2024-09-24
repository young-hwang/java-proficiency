package me.thread.cas.lock;

import static me.util.MyLogger.log;

public class SpinLockMain {
    public static void main(String[] args) {
        SpinLockBad spinLockBad = new SpinLockBad();
        Runnable runnable = () -> {
            spinLockBad.lock();
            try {
                // critical section
                log("비즈니스 로직 실행");
            } finally {
                spinLockBad.unlock();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}
