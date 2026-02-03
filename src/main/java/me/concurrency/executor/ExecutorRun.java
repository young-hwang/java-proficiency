package me.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorRun {
    public static void main(String[] args) {
        // single thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName());
            }
        });

        // multi thread
        executorService.execute(() -> System.out.println("Thread " + Thread.currentThread().getName()));

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.execute(getRunnable("Hello"));
        executorService1.execute(getRunnable("World"));
        executorService1.execute(getRunnable("Young"));
        executorService1.execute(getRunnable("Hwang"));
        executorService1.execute(getRunnable("Park"));

        // schedule
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello"), 5, 2, TimeUnit.SECONDS);

        // graceful shutdown
        executorService.shutdown();
        // executorService.shutdownNow();
    }

    private static Runnable getRunnable(String text) {
        return () -> System.out.println("Thread " + text + " " + Thread.currentThread().getName());
    }
}
