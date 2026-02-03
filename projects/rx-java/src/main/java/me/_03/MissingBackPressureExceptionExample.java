package me._03;

import com.sun.tools.javac.Main;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MissingBackPressureExceptionExample {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> logger.info("doOnNext" + data))
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    logger.info("소비자 처리 대기");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    logger.info("subscribe");
                },
                        error -> logger.info("error", error.getMessage()),
                        () -> logger.info(""));
        Thread.sleep(2000L);
    }
}
