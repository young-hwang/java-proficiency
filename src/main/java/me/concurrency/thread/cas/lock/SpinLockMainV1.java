package me.concurrency.thread.cas.lock;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class SpinLockMainV1 {
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = () -> {
            spinLock.lock();
            try {
                // critical section
                log("비즈니스 로직 실행");
                sleep(1);
            } finally {
                spinLock.unlock();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}
