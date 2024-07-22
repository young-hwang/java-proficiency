package me._03;

import com.sun.tools.javac.Main;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


public class MissingBackPressureExceptionExample {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> log.info("doOnNext" + data))
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    log.info("소비자 처리 대기");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    log.info("subscribe");
                },
                        error -> log.info("error", error.getMessage()),
                        () -> log.info(""));
        Thread.sleep(2000L);
    }
}
