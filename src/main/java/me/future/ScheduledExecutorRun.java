package me.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorRun {
    public static void main(String[] args) {
        // SchedulerExecutorService 생성
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // 5초 후에 작업을 예약
        ScheduledFuture<?> scheduledFuture = scheduler.schedule(() -> {
            System.out.println("Task executed after 5 seconds");
        }, 5, TimeUnit.SECONDS);
        System.out.println("Task started");
        // 작업의 상태를 확인
        try {
            // get()을 호출하여 작업이 완료될 때까지 기다림
            scheduledFuture.get();
            System.out.println("Task completed");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // scheduler 종료
            scheduler.shutdown();
        }
    }
}
