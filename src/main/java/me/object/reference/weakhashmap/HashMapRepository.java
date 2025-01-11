package me.object.reference.weakhashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapRepository implements Repository {
    private Map<CacheKey, String> cache;

    public HashMapRepository() {
        cache = new HashMap<>();
    }

    @Override
    public String get(CacheKey key) {
        return cache.get(key);
    }

    @Override
    public void put(CacheKey key, String value) {
        this.cache.put(key, value);
    }

    public Map<CacheKey, String> getCache() {
        return cache;
    }
}
