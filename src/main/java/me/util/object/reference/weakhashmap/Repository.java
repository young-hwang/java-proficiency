package me.util.object.reference.weakhashmap;

import java.util.Map;

public interface Repository {
    String get(CacheKey key);
    void put(CacheKey key, String value);
    Map<CacheKey, String> getCache();
}
