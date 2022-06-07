package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        V value = null;
        SoftReference<V> cachingValue = cache.get(key);
        if (cachingValue != null && cachingValue.get() != null) {
            value = cachingValue.get();
            System.out.println("File is gotten from cache:");
        } else {
            value = load(key);
            put(key, value);
            System.out.println("File is gotten from load:");
        }
        return value;
    }

    protected abstract V load(K key);
}
