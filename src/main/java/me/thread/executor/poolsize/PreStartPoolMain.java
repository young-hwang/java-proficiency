package me.thread.executor.poolsize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static me.thread.executor.ExecutorUtils.printState;

public class PreStartPoolMain {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1000);
        printState(executor);
        ThreadPoolExecutor es = (ThreadPoolExecutor) executor;
        es.prestartAllCoreThreads();
        printState(executor);
    }
}
