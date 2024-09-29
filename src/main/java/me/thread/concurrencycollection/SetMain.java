package me.thread.concurrencycollection;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        System.out.println("set = " + set);

        Set<String> skipSet = new ConcurrentSkipListSet<>();
        skipSet.add("c");
        skipSet.add("b");
        skipSet.add("a");
        System.out.println("skipSet = " + skipSet); // comparator를 이용한 순서 유지
    }
}
