package me.concurrency.thread.join;

import me.util.ThreadUtils;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class JoinMain02 {
    public static void main(String[] args) {
        log("start");
        SumJob sumJob1 = new SumJob(1, 50);
        SumJob sumJob2 = new SumJob(51, 100);
        Thread thread1 = new Thread(sumJob1, "thread-1");
        Thread thread2 = new Thread(sumJob2, "thread-2");
        thread1.start();
        thread2.start();

        log("task1.result " + sumJob1.result);
        log("task1.result " + sumJob2.result);

        // thread1, thread2 동작이 끝날때 까지 대기
        // 무작정 기다리면 정확한 타이밍을 알기 힘들고 시간 손해가 존재
        // sleep(3000);

        // 반복문을 이용한 terminated 확인
        // thread가 종료시 TERMINATED 상태가 된다.
        // 반복문은 cpu 연산을 필요로 한다.
        while(thread1.getState() != Thread.State.TERMINATED || thread2.getState() != Thread.State.TERMINATED) {
            sleep(100);
        }

        int sumAll = sumJob1.result + sumJob2.result;
        log("sum all " + sumAll);
        log("End");
    }

    static class SumJob implements Runnable {
        private int startValue;
        private int endValue;
        public int result;

        public SumJob(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            log("작업 종료");
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log("작업 완료 = " + result);
        }
    }
}
