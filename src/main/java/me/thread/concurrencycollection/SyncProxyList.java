package me.thread.concurrencycollection;

import java.util.Arrays;

import static me.util.ThreadUtils.sleep;

public class SyncProxyList implements SimpleList {

    private SimpleList simpleList;

    public SyncProxyList(SimpleList simpleList) {
        this.simpleList = simpleList;
    }

    @Override
    public int size() {
        return this.simpleList.size();
    }

    @Override
    public synchronized void add(Object o) {
        this.simpleList.add(o);
    }

    @Override
    public synchronized Object get(int index) {
        return this.simpleList.get(index);
    }

    @Override
    public String toString() {
        return this.simpleList.toString();
    }
}
