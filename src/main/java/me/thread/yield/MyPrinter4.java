package me.thread.yield;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static me.util.MyLogger.log;

/**
 * MyPrinter에 yield를 적용하자
 */
public class MyPrinter4 {
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
            // yield 적용을 위한 최적의 장소
            // interrupted 상태를 확인 및 jobQueue의 상태를 확인한다.
            // interrupt도 걸리지 않고 jobQueue도 비었다면 이 체크 로직에 CPU 자원을 많이 사용하게 된다.
            // 따라서 yield를 호출하여 다른 스레드에 작업을 양보하는게 전체 관점에서 보면 더 효율적이다.
            while (!Thread.interrupted()) {
//                if (jobQueue.isEmpty()) {
//                    continue;
//                }
                if (jobQueue.isEmpty()) {
                    Thread.yield();
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
