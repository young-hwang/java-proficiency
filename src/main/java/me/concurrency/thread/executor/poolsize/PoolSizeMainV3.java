package me.concurrency.thread.executor.poolsize;

import me.concurrency.thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.concurrency.thread.executor.ExecutorUtils.printState;
import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class PoolSizeMainV3 {
    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        동일한 executor service
//        캐시 스레드 풀 전략은 초과 스레드가 60초의 생존 주기를 가지지만 확인을 위해 3초로 조절
        ExecutorService executorService = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                3L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        log("pool 생성");
        printState(executorService);

        for (int i = 0; i < 6; i++) {
            String name = "task" + i;
            executorService.execute(new RunnableTask(name));
            printState(executorService);
        }

        sleep(3000);
        log("작업 수행 완료");
        printState(executorService);

        sleep(3000);
        log("최대 대기 시간 초과");
        printState(executorService);

        executorService.close();

        log("shutdown 완료");
        printState(executorService);
    }

}
