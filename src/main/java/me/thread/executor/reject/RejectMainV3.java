package me.thread.executor.reject;

import me.thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.util.MyLogger.log;

public class RejectMainV3 {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.submit(new RunnableTask("task1"));
        executorService.submit(new RunnableTask("task2"));
        executorService.submit(new RunnableTask("task3"));
        executorService.close();
    }
}
