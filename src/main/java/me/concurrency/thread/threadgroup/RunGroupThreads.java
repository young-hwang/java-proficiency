package me.concurrency.thread.threadgroup;

public class RunGroupThreads {
    public static void main(String[] args) {
        RunGroupThreads runGroupThreads = new RunGroupThreads();
        runGroupThreads.groupThread();
    }

    public void groupThread() {
        try {
            SleepThread sleep1 = new SleepThread(5000);
            SleepThread sleep2 = new SleepThread(5000);

            ThreadGroup threadGroup = new ThreadGroup("Group1");
            Thread thread1 = new Thread(threadGroup, sleep1);
            Thread thread2 = new Thread(threadGroup, sleep2);

            thread1.start();
            thread2.start();
            Thread.sleep(1000);
            System.out.println("Group name = " + threadGroup.getName());    // 1번
            int activeCount = threadGroup.activeCount();
            System.out.println("Active count = " + activeCount); // 2번
            threadGroup.list(); // 3번

            Thread[] threads = new Thread[activeCount];
            int result = threadGroup.enumerate(threads);
            System.out.println("Enumerate result = " + result); // 4번
            for (Thread thread : threads) {
                System.out.println(thread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
Sleeping Thread-16
Sleeping Thread-15
Group name = Group1 // 1번 출력
Active count = 2    // 2번 출력
java.lang.ThreadGroup[name=Group1,maxpri=10]    // 3번 출력
    Thread[Thread-2,5,Group1]
    Thread[Thread-3,5,Group1]
Enumerate result = 2    // 4번 출력
Thread[Thread-2,5,Group1]
Thread[Thread-3,5,Group1]
 */

