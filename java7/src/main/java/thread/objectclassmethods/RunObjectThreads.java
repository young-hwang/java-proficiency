package thread.objectclassmethods;

public class RunObjectThreads {
    public static void main(String[] args) {
        RunObjectThreads sample = new RunObjectThreads();
        sample.checkThreadState();
    }

    private void checkThreadState() {
        Object monitor = new Object();
        StateThread stateThread = new StateThread(monitor);
        StateThread stateThread2 = new StateThread(monitor);

        try {
            System.out.println("thread state = " + stateThread.getState());
            stateThread.start();
            stateThread2.start();
            System.out.println("thread state(after start) = " + stateThread.getState());

            Thread.sleep(100);
            System.out.println("thread state(after 0.1 sec) = " + stateThread.getState());

            synchronized (monitor) {
                monitor.notify(); // interrupt() 호출 시도 대기 상태에서 풀리나 InterruptException 발생
                // monitor.notifyAll(); notifyAll() 호출 시 모두 wait() 상태에서 풀림
            }
            Thread.sleep(100);
            System.out.println("thread state(after notify) = " + stateThread.getState());

            stateThread.join();
            System.out.println("thread state(after join) = " + stateThread.getState());
            stateThread2.join();
            System.out.println("thread2 state(after join) = " + stateThread2.getState());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        /*
        thread state = NEW
        thread state(after start) = RUNNABLE
        thread state(after 0.1 sec) = WAITING -- wait() 메소드에 의해 WAITING 상태
        Thread-0 is notified
        thread state(after notify) = TIMED_WAITING
        thread state(after join) = TERMINATED
        // thread2 state 출력 안됨, 무한 대기 - notify() 호출 시 먼저 대기 중인 것 부터 해제되어 발생
         */
    }
}
