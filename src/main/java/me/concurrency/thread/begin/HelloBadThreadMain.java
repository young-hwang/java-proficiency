package me.concurrency.thread.begin;

public class HelloBadThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "Main Start");
        System.out.println(Thread.currentThread().getName() + "Start Call");
        // run 수행시 main thread가 실행
        new HelloThread().run();
        System.out.println(Thread.currentThread().getName() + "End Call");
        System.out.println(Thread.currentThread().getName() + "Main End");
    }
}
