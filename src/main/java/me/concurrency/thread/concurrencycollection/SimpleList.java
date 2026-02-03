package me.concurrency.thread.concurrencycollection;

public interface SimpleList {
    int size();

    void add(Object o);

    Object get(int index);
}
