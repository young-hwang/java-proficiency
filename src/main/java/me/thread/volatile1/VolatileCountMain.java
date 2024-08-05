package me.thread.volatile1;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class VolatileCountMain {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);
        thread.start();

        sleep(1000);
        myTask.flag = false;
        log("flag = " + myTask.flag + ", count = " + myTask.count + " in while()");
    }

    static class MyTask implements Runnable {
        // volatile 없을 시 시차가 발생
        // 23:09:29.853 [     main] flag = false, count = 1008027899 in while()
        // 23:09:29.941 [ Thread-0] flag = true, count = 1100000000 in while()
        // boolean flag = true;
        // long count;

        // volatile 사용 시 변경 값을 정확히 확인
        // volatile을 적용 시 캐시 메모리가 아닌 메인 메모리에 항상 접근하므로 성능이 상대적으로 떨어짐
        // 23:17:11.928 [ Thread-0] flag = true, count = 100000000 in while()
        // 23:17:12.082 [     main] flag = false, count = 118949614 in while()
        // 23:17:12.082 [ Thread-0] flag = false, count = 118949614 in while() end
        volatile boolean flag = true;
        volatile long count;

        @Override
        public void run() {
            while(flag) {
                count++;
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + flag + ", count = " + count + " in while() end");
        }
    }
}
