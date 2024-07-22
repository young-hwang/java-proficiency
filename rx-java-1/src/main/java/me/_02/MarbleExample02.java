package me._02;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class MarbleExample02 {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 25, 9, 15, 7, 30);
        observable.subscribe(System.out::println);
    }
}
