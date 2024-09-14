package me.thread.deadlock;

public class DeadLockMain {
    // 두 개의 리소스 객체를 생성
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public static void main(String[] args) {
        DeadLockMain deadLockMain = new DeadLockMain();
        deadLockMain.runTest();
    }

    private void runTest() {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");
                try {
                    // 100ms 동안 대기하여 다른 스레드가 lock을 획득할 시간을 줌
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread 1: waiting for resource 2");
                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: locked resource 2");
                try {
                    // 100ms 동안 대기하여 다른 스레드가 lock을 획득할 시간을 줌
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread 2: waiting for resource 1");
                synchronized (resource1) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}