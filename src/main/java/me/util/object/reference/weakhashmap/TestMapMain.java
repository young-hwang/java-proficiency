package me.util.object.reference.weakhashmap;

import java.util.Map;

public class TestMapMain {
    public static void main(String[] args) throws InterruptedException {
        Repository hashMapRepository = new HashMapRepository();
        CacheKey key1 = new CacheKey(1);
        String value1 = "Hello, World!";
        hashMapRepository.put(key1, value1);
        Map<CacheKey, String> hashMapCache = hashMapRepository.getCache();
        System.out.println("Before GC HashMap size: " + hashMapCache.size());
        key1 = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("After GC HashMap size: " + hashMapCache.size());

        System.out.println("==========================");

        Repository weakHashMapRepository = new WeakHashMapRepository();
        CacheKey key2 = new CacheKey(1);
        String value2 = "Hello, World!";
        weakHashMapRepository.put(key2, value2);
        Map<CacheKey, String> weakHashMapCache = weakHashMapRepository.getCache();
        System.out.println("Before GC HashMap size: " + weakHashMapCache.size());
        key2 = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("After GC HashMap size: " + weakHashMapCache.size());
    }
}
