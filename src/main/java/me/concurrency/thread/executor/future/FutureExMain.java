package me.concurrency.thread.executor.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static me.util.MyLogger.log;
import static me.util.ThreadUtils.sleep;

public class FutureExMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log("요청 스레드 시작");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new ExCallable());
        log("Future.state: " + future.state());

        // 일정 시간 후 취소 시도
        sleep(1000);

        try {
            log("Future.state: " + future.state());

            // 결과 확인
            Integer result = future.get();

            executorService.close();
            log("요청 스레드 종료");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log("e = " + e);
            Throwable cause = e.getCause();
            log("cause = " + cause);
            Throwable cause1 = cause.getCause();
            log("cause1 = " + cause1);
        }
    }

    static class ExCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            try {
                throw new IllegalStateException("ex!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
