package me.concurrency.thread.concurrencycollection;

import static me.util.MyLogger.log;

public class SimpleListMainV3 {
    public static void main(String[] args) throws InterruptedException {
        SimpleList list = new SyncList();
        test(list);
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list);

        Runnable runnableA = () -> {
            list.add("A");
            log("list.add(A)");
        };

        Runnable runnableB = () -> {
            list.add("B");
            log("list.add(B)");
        };

        Thread threadA = new Thread(runnableA);
        Thread threadB = new Thread(runnableB);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        log(list);
    }
}