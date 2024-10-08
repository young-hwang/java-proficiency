package me.thread.executor.poolsize;

import me.thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.thread.executor.ExecutorUtils.printState;
import static me.util.MyLogger.log;

public class PoolSizeMainV2 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        동일한 executor service
//        ExecutorService executorService = new ThreadPoolExecutor(2,
//                2,
//                0L,
//                TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<>());

        log("pool 생성");
        printState(executorService);

        for (int i = 0; i < 6; i++) {
            String name = "task" + i;
            executorService.execute(new RunnableTask(name));
            printState(executorService);
        }

        executorService.close();

        log("shutdown 완료");
        printState(executorService);
    }

}
