package me.thread.executor.future;

import static me.util.MyLogger.log;

public class SumTaskMainV1 {
    public static void main(String[] args) throws InterruptedException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // 스레드가 종료될 때 까지 대기
        log("join() - main 스레드가 thread-1, thread-2 종료까지 대기");
        thread1.join();
        thread2.join();
        log("main 스레드 대기 완료");

        log("task1.sum = " + task1.sum);
        log("task2.sum = " + task2.sum);
        log("task1 + task2 = " + (task1.sum + task2.sum));
        log("enc");
    }

    private static class SumTask implements Runnable {
        private int first;
        private int last;
        private int sum = 0;

        public SumTask(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        public void run() {
            log("작업 시작");
            for (int i = first; i <= last; i++) {
                sum += i;
            }
            log("작업 완료 = " + sum);
        }
    }
}
