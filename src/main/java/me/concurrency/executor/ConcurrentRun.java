package me.concurrency.executor;

public class ConcurrentRun {
    public static void main(String[] args) throws InterruptedException {
        // Thread Class 상속
        MyThread myThread = new MyThread();
        myThread.start();

        // Runnable Interface 구현
        Thread myThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable " + Thread.currentThread().getName());
            }
        });
        myThread1.start();

        // Runnable Lambda 구현
        Thread myThread2 = new Thread(() -> {
            System.out.println("Runnable Lambda " + Thread.currentThread().getName());
        });
        myThread2.start();

        // Thread Sleep
        Thread myThread3 = new Thread(() -> {
            try {
                System.out.println("Runnable Sleep" + Thread.currentThread().getName());
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        myThread3.start();

        // Interrupt
        Thread myThread4 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Runnable Interrupt " + Thread.currentThread().getName());
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                System.out.println("Exit");
                return;
            }
        });
        myThread4.start();

        Thread.sleep(3000L);
        myThread4.interrupt();

        // Thread join : 다른 쓰레드 대기
        Thread myThread5 = new Thread(() -> {
            try {
                System.out.println("Runnable Join " + Thread.currentThread().getName());
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                new IllegalArgumentException();
            }
        });
        myThread5.start();

        System.out.println("World" + Thread.currentThread().getName());
        myThread5.join();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName());
        }
    }
}
