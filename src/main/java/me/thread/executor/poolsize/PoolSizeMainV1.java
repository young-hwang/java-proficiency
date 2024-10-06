package me.thread.executor.poolsize;

import me.thread.executor.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.thread.executor.ExecutorUtils.printState;
import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        // maximumPoolSize는 workQueue에 더이상 task가 들어갈 수 없을 때 증가하게 됨
        // pool을 증가시키는 task가 먼저 실행 됨 (task3, task4는 workQueue에서 대기, task5는 스레드풀을 증가 시키며 실행)
        ExecutorService executor = new ThreadPoolExecutor(2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);
        printState(executor);

        executor.execute(new RunnableTask("task1"));
        printState(executor, "task1");

        executor.execute(new RunnableTask("task2"));
        printState(executor, "task2");

        executor.execute(new RunnableTask("task3"));
        printState(executor, "task3");

        executor.execute(new RunnableTask("task4"));
        printState(executor, "task4");

        executor.execute(new RunnableTask("task5"));
        printState(executor, "task5");

        executor.execute(new RunnableTask("task6"));
        printState(executor, "task6");

        try {
            executor.execute(new RunnableTask("task7"));
            printState(executor, "task7");
        } catch (RejectedExecutionException e) {
            log("task7 실행 거절 예외 발생 " + e);
        }

        sleep(3000);
        log("작업 수행 완료");
        printState(executor);

        sleep(3000);
        log("maximumPoolSize 대기 시간 초과");
        printState(executor);

        executor.close();
        log("shutdown 완료");
        printState(executor);
    }
}
