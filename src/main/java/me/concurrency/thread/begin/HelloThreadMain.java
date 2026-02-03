package me.concurrency.thread.begin;

public class HelloThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "Main Start");
        System.out.println(Thread.currentThread().getName() + "Start Call");
        new HelloThread().start();
        System.out.println(Thread.currentThread().getName() + "End Call");
        System.out.println(Thread.currentThread().getName() + "Main End");
    }
}
