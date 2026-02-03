package me.concurrency.thread.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalMainV2 {
    // ThreadLocal 변수를 선언합니다.
    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            final AtomicInteger atomicInteger = new AtomicInteger(0);
            final ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    threadLocalValue.set(atomicInteger.incrementAndGet());
                    System.out.println(Thread.currentThread().getName() + " : " + threadLocalValue.get());
                }
            }
        };

        // 스레드 1 생성
        Thread thread1 = new Thread(runnable);

        // 스레드 2 생성
        Thread thread2 = new Thread(runnable);

        // 스레드 실행
        thread1.start();
        thread2.start();
    }
}
