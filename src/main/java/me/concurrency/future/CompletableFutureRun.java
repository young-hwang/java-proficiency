package me.concurrency.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CompletableFutureRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Future<String> submit = executorService.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "Hello";
            }
        });
        System.out.println(submit.get());

        Future<String> submit1 = executorService.submit((Callable) () -> "Hello");
        System.out.println(submit1.get());


        // CompletableFuture 사용 시 Executors 사용하여 쓰레드 생성 필요 없음
        // ForkJoin을 사용하여 가능
        // 리턴이 있는 Future 일 시 completedFuture 사용
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("young");
        System.out.println(completableFuture.get());

        // 리턴이 없는 Future 일 시 runAsync 사용
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println("young " + Thread.currentThread().getName());
        });
        System.out.println(completableFuture1.get());

        // 리턴이 있는 Future 일 시 supplierAsync 사용
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            return "young " + Thread.currentThread().getName();
        });
        System.out.println(completableFuture2.get());

        // CompletableFuture 사용 시 asynchronous 하게 callback 처리 가능
        // thenApply : 리턴 값을 받아 다른 값으로 변환
        CompletableFuture<Integer> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello thenApply";
        }).thenApply(s -> {
            return s.length();
        });
        System.out.println(stringCompletableFuture.get());

        // thenAccept : 리턴값을 받아 다른 작업을 처리(리턴X)
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello thenAccept";
        }).thenAccept(s -> {
            System.out.println(s);
        });

        // thenRun : 리턴값을 받거나 전달 없이 실행만 하는 경우
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            return "test";
        }).thenRun(() -> {
            System.out.println("hello theReturn");
        });

        // CompletableFuture 를 사용하여도 thread pool 생성하여 사용 가능
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture async " + Thread.currentThread().getName());
            return "young";
        }, executorService1).thenRunAsync(() -> {
            System.out.println("Thread Pool " + Thread.currentThread().getName());
        });

        voidCompletableFuture2.get();

        // future 가 순차적으로 실행이 필요한 경우
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello");
            return "Hello";
        });

        CompletableFuture<String> helloWorld = hello.thenCompose(CompletableFutureRun::getWorld);

        System.out.println(helloWorld.get());


        // future 가 연관 없이 각각 실행이 되고 그 후 결합이 필요한 경우
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World");
            return "World";
        });

        CompletableFuture<String> helloWorld1 = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(helloWorld1.get());

        // 2개 이상의 future 연결 하기
        List<CompletableFuture> completableFutures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = completableFutures.toArray(new CompletableFuture[completableFutures.size()]);

        CompletableFuture<List<Object>> listCompletableFuture = CompletableFuture.allOf(futuresArray)
                .thenApply(v ->
                    completableFutures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()));

        listCompletableFuture.get().forEach(System.out::println);

        // 둘중 아무거나 빠른 결과 호출
        System.out.println("==============");
        CompletableFuture<Void> voidCompletableFuture3 = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        voidCompletableFuture3.get();

        // exception 처리
        boolean throwError = true;

        CompletableFuture<String> helloException = CompletableFuture.supplyAsync(() -> {
            if (throwError)
                throw new IllegalArgumentException();
            System.out.println("Hello");
            return "Hello";
        });

        CompletableFuture<String> exceptionally = helloException.exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(exceptionally.get());

        executorService.shutdown();
        executorService1.shutdown();
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + " World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
