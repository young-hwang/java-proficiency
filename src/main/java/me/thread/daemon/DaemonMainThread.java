package me.thread.daemon;

public class DaemonMainThread {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class DaemonThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ": run() start");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName() + ": run() end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
