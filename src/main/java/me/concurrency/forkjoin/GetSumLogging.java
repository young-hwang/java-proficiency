package me.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class GetSumLogging extends RecursiveTask<Long> {
    long from, to;

    public GetSumLogging(long from, long to) { // 작업을 수행할 값을 할당
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        long gap = to - from;
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log("From=" + from + " To=" + to);
        if (gap <= 3) {
            long tempSum = 0;
            for (long loop = from; loop <= to; loop++) {
                tempSum += loop;
            }
            log("Return !! " + from + " ~ " + to + " = " + tempSum);
            return tempSum;
        }
        long middle = (from + to) / 2;
        GetSumLogging sumPre = new GetSumLogging(from, middle);
        log("Pre     From=" + from + " To=" + middle);
        sumPre.fork();
        GetSumLogging sumPost = new GetSumLogging(middle + 1, to);
        log("Post    From=" + (middle + 1) + " To=" + to);
        return sumPost.compute() + sumPre.join();
    }

    public void log(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "]" + message);
    }
}