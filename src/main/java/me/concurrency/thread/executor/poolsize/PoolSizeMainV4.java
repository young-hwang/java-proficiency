package me.concurrency.thread.executor.poolsize;

import me.concurrency.thread.executor.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.concurrency.thread.executor.ExecutorUtils.printState;
import static me.util.MyLogger.log;

public class PoolSizeMainV4 {
    static final int TASK_SIZE = 1100;  // 일반 상황
//    static final int TASK_SIZE = 1200;  // 긴급 상황
//    static final int TASK_SIZE = 1201;  // 거절 상황

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(100,
                200,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000)
        );

        log("pool 생성");
        printState(executorService);

        long startMs = System.currentTimeMillis();

        for (int i = 0; i < TASK_SIZE; i++) {
            String name = "task " + i;
            try {
                executorService.execute(new RunnableTask(name));
                printState(executorService, name);
            } catch (RejectedExecutionException e) {
                log(name + e.getMessage());
            }
        }

        executorService.close();
        log("shutdown 완료");

        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs));
    }

}
