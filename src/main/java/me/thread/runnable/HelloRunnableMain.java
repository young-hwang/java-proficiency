package me.thread.runnable;

public class HelloRunnableMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "Main Start");
        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thread = new Thread(helloRunnable);
        thread.start();
        System.out.println(Thread.currentThread().getName() + "Main End");
    }
}
