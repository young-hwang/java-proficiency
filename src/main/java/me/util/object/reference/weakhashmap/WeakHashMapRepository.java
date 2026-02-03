package me.util.object.reference.weakhashmap;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapRepository implements Repository {
    private Map<CacheKey, String> cache;

    public WeakHashMapRepository() {
        this.cache = new WeakHashMap<>();
    }

    @Override
    public String get(CacheKey key) {
        return cache.get(key);
    }

    @Override
    public void put(CacheKey key, String value) {
        this.cache.put(key, value);
    }

    @Override
    public Map<CacheKey, String> getCache() {
        return this.cache;
    }
}
