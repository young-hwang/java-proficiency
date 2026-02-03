package me.concurrency.thread.concurrencycollection;

import java.util.Arrays;

import static me.util.ThreadUtils.sleep;

public class BasicList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(Object o) {
        elements[size] = o;
        sleep(100);
        size++;
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size)) + ", size = " + size + ", capacity = " + elements.length;
    }
}
