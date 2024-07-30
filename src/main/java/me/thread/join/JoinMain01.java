package me.thread.join;

import me.util.ThreadUtils;

import javax.xml.stream.events.StartDocument;

import static me.util.MyLogger.log;

public class JoinMain01 {
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
            ThreadUtils.sleep(2000);
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
