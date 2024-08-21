package me._01;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ToDoSample {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(100, 200, 300, 400, 500)
                .doOnNext(data -> System.out.println(getThreadName() + " : " + "#doOnNext() : " + data))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .filter(number -> number > 300)
                .subscribe(num -> System.out.println(getThreadName() + " : result : " + num));

        Thread.sleep(500);
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
