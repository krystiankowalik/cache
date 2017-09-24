package com.kryx07.cache;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import com.kryx07.cache.view.CacheView;
import com.kryx07.cache.view.CacheViewImpl;

import java.util.*;

public class CacheImpl implements Cache {

    private final Map<String, CacheItem> cachedItems;
    private List<String> cacheKeys;
    private final int maxCacheSize;

    public CacheImpl(int maxCacheSize) {
        cachedItems = Collections.synchronizedMap(new HashMap<>(maxCacheSize));
        cacheKeys = Collections.synchronizedList(new ArrayList<>(maxCacheSize));
        this.maxCacheSize = maxCacheSize;
    }

    @Override
    public CacheItem cacheItem(Object item, String key) {
        CacheItem cacheItem = new CacheItemImpl(key, item);
        synchronized (this) {
            if (cachedItems.putIfAbsent(key, cacheItem) == null) {
                cacheKeys.add(key);
            }
            if (cacheKeys.size() > maxCacheSize && cacheKeys.size() > 0) {
                String keyToRemove = cacheKeys.get(0);
                cachedItems.remove(keyToRemove);
                cacheKeys = cacheKeys.subList(1, cacheKeys.size() - 1);
            }

        }
        return cacheItem;
    }

    @Override
    public synchronized void invalidateCache() {
        cachedItems.clear();
    }

    @Override
    public synchronized CacheView getView() {
        return new CacheViewImpl(cachedItems, cacheKeys);
    }
}
