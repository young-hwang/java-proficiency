package me.thread.test;

import static me.util.MyLogger.log;

public class MultiThreadMain {
    public static void main(String[] args) {
        PrintWorker printWorkerA = new PrintWorker("Worker A", 500);
        PrintWorker printWorkerB = new PrintWorker("Worker B", 1000);
        new Thread(printWorkerA).start();
        new Thread(printWorkerB).start();
    }

    private static class PrintWorker implements Runnable {
        private String content;
        private int term;


        public PrintWorker(String content, int term) {
            this.content = content;
            this.term = term;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    log(content);
                    Thread.sleep(term);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
