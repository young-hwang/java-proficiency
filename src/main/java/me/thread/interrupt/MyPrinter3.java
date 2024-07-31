package me.thread.interrupt;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static me.util.MyLogger.log;

/**
 * MyPrinter2을 refactoring 하기
 * thread 상태 확인하는 방법으로
 */
public class MyPrinter3 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "Printer Thread");
        printerThread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q): ");
            String input = scanner.nextLine();
            if ("q".equals(input)) {
                printerThread.interrupt();
                break;
            }
            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                if (jobQueue.isEmpty()) {
                    continue;
                }

                try {
                    String job = jobQueue.poll();
                    log("출력 시작: " + job + ", 대기 문서: " + jobQueue);
                    Thread.sleep(3000);
                    log("출력 완료");
                } catch (InterruptedException e) {
                    log("work thread interrupted=" + Thread.currentThread().isInterrupted());
                    log("interrupt message " + e.getMessage());
                    log("state=" + Thread.currentThread().getState());
                    break;
                }
            }
        }

        public void addJob(String job) {
            this.jobQueue.add(job);
        }
    }
}
