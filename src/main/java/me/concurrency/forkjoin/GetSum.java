package me.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class GetSum extends RecursiveTask<Long> {
    long from, to;

    public GetSum(long from, long to) { // 작업을 수행할 값을 할당
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        long gap = to - from; // 작업의 단위가 충분히 작은지 확인
        if (gap <= 3) {
            long tempSum = 0;
            for (long loop = from; loop <= to; loop++) {
                tempSum += loop;
            }
            return tempSum;
        }
        long middle = (from + to) / 2;
        GetSum sumPre = new GetSum(from, middle);
        sumPre.fork();
        GetSum sumPost = new GetSum(middle + 1, to);
        return sumPost.compute() + sumPre.join();
    }
}
