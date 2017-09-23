package com.kryx07.cache;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import com.kryx07.cache.view.CacheView;
import com.kryx07.cache.view.CacheViewImpl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheImpl implements Cache {

    private Map<String, CacheItem> cachedItems;

    public CacheImpl(int maxCacheSize) {
        this.cachedItems = Collections.synchronizedMap(new LinkedHashMap(maxCacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maxCacheSize;
            }
        });
    }

    @Override
    public CacheItem cacheItem(Object item, String key) {
        CacheItem cacheItem = new CacheItemImpl(key, item);
        synchronized (this) {
            cachedItems.putIfAbsent(key, cacheItem);
        }
        return cacheItem;
    }

    @Override
    public synchronized void invalidateCache() {
        cachedItems.clear();
    }

    @Override
    public synchronized CacheView getView() {
        return new CacheViewImpl(cachedItems);
    }
}
