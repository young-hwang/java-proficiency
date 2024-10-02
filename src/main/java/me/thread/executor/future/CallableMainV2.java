package me.thread.executor.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class CallableMainV2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        MyCallable myCallable = new MyCallable();
        log("submit() 호출");
        Future<Integer> future = executorService.submit(myCallable);
        log("future 즉시 반환, future = " + future);
        sleep(3000);
        log("future.get() [블로킹] 메소드 호출 시작 -> main 스레드 WAITING ");
        Integer value = future.get();
        log("future.get() [블로킹] 메소드 호출 완료 -> main 스레드 RUNNABLE ");
        log("result value = " + value);
        log("future 완료, future = " + future);
        executorService.close();
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            log("Runnable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("create value = " + value);
            log("Runnable 완료");
            return value;
        }
    }
}
