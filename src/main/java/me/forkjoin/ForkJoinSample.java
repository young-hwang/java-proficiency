package me.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinSample {
    static final ForkJoinPool mainPool = new ForkJoinPool();

    public static void main(String[] args) {
        ForkJoinSample forkJoinSample = new ForkJoinSample();
        forkJoinSample.calculateLogging();
    }

    private void calculate() {
        long from = 0;
        long to = 10;

        GetSum sum = new GetSum(from, to);
        Long result = mainPool.invoke(sum);
        System.out.println("Fork Join: Total sum of " + from + " ~ " + to + "=" + result);
    }

    private void calculateLogging() {
        long from = 0;
        long to = 10;

        GetSumLogging sum = new GetSumLogging(from, to);
        Long result = mainPool.invoke(sum);
        System.out.println("Fork Join: Total sum of " + from + " ~ " + to + "=" + result);
    }
}
