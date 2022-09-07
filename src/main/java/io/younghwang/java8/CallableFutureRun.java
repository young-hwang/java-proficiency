package io.younghwang.java8;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable
        // Thread 에서 실행 되어질 인스턴스를 만들기 위한 interface
        // Runnable 과 달리 리턴 값을 가짐
        Callable<String> callable = new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "Hello";
            }
        };

        System.out.println("started " + LocalDateTime.now());
        Future<String> submit = executorService.submit(callable);
        System.out.println(submit.isDone());
        // submit.cancel(true); // true: 취소
        // submit.cancel(false); // false: 값을 받을 수 없이 작업 완료

        System.out.println(submit.get()); // blocking

        System.out.println(submit.isDone());
        System.out.println("ended " + LocalDateTime.now());

        System.out.println("================");

        Callable<String> callable2 = new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Hwang";
            }
        };

        Callable<String> callable3 = new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Young";
            }
        };

        // invokeAll : 실행되는 모든 callable 의 값을 기다린 후 전달
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(callable, callable2, callable3));
        for (Future<String> future: futures) {
            System.out.println(future.get());
        }

        System.out.println("================");

        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        // invokeAny : 실행되는 가장 빠른 결과를 전달
        String s = executorService1.invokeAny(Arrays.asList(callable, callable2, callable3));
        System.out.println(s);

        executorService.shutdown();
        executorService1.shutdown();
    }
}
