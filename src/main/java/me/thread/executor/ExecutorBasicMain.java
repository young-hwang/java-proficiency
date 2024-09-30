package me.thread.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.thread.executor.ExecutorUtils.printState;
import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        log("== 초기 상태 ==");
        printState(pool);
        pool.execute(new RunnableTask("taskA"));
        pool.execute(new RunnableTask("taskB"));
        pool.execute(new RunnableTask("taskC"));
        pool.execute(new RunnableTask("taskD"));
        log("== 작업 수행 중 ==");
        printState(pool);

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(pool);

        pool.close();
        log("== shutdown 완료 ==");
        printState(pool);
    }
}
