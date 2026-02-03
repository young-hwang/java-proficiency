package me.concurrency.thread.executor.reject;

import me.concurrency.thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.util.MyLogger.log;

public class RejectMainV2 {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());
        executorService.submit(new RunnableTask("task1"));
        try {
            executorService.submit(new RunnableTask("task2"));
        } catch (RejectedExecutionException e) {
            log("요청 초과");
            // 포기, 다시 시도 등 다양한 고민 필요
            log(e.getMessage());
        }
        executorService.close();
    }
}
