package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Map;

public class CacheViewImpl implements CacheView {


    private final Map<String, CacheItem> cachedItems;
    private final CircularFifoQueue<String> cacheKeys;

    public CacheViewImpl(Map<String, CacheItem> cachedItems, CircularFifoQueue<String> cacheKeys) {
        this.cachedItems = cachedItems;
        this.cacheKeys = cacheKeys;
    }

    @Override
    public synchronized int size() {
        return cachedItems.size();
    }

    @Override
    public synchronized CacheItem getItem(int index) {
        return cachedItems.get(cacheKeys.get(index));
    }

    @Override
    public synchronized CacheItem getItem(String key) {
        return cachedItems.get(key);
    }
}
