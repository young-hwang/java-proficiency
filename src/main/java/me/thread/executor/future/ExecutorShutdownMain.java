package me.thread.executor.future;

import me.thread.executor.ExecutorUtils;
import me.thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static me.thread.executor.ExecutorUtils.printState;
import static me.util.MyLogger.log;

public class ExecutorShutdownMain {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new RunnableTask("taskA"));
        executor.execute(new RunnableTask("taskB"));
        executor.execute(new RunnableTask("taskC"));
        executor.execute(new RunnableTask("longTask", 100_000)); // 100초 대기
        printState(executor);
        log("executor shutdown 시작");
        shutdownAndAwaitTermination(executor);
        log("executor shutdown 종료");
        printState(executor);
    }

    private static void shutdownAndAwaitTermination(ExecutorService executor) {
        executor.shutdown(); // non-blocking, 새로운 작업 받지 않음, 처리중이거나 큐에 이미 대기중인 작업은 처리
        try {
            // 이미 대기중인 작업들을 모두 완료할 때 까지 10초 기다림
            if (!executor.awaitTermination(10, TimeUnit. SECONDS)) {
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                executor.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(10, TimeUnit. SECONDS))
                    System. err. println("서비스가 종료 되지 않았습니다.");
            }
        } catch (InterruptedException ex) {
            // (Re-) Cancel if current thread also interrupted
            executor.shutdownNow();     // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
