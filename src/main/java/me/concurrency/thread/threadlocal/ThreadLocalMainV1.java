package me.concurrency.thread.threadlocal;

public class ThreadLocalMainV1 {
    // ThreadLocal 변수를 선언합니다.
    private static ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {

        // 스레드 1 생성
        Thread thread1 = new Thread(() -> {
            threadLocalValue.set(100);
            System.out.println("Thread 1 Value: " + threadLocalValue.get());
        });

        // 스레드 2 생성
        Thread thread2 = new Thread(() -> {
            threadLocalValue.set(200);
            System.out.println("Thread 2 Value: " + threadLocalValue.get());
        });

        // 스레드 실행
        thread1.start();
        thread2.start();
    }
}
