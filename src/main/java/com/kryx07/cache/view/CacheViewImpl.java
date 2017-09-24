package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;

import java.util.List;
import java.util.Map;

public class CacheViewImpl implements CacheView {


    private final Map<String, CacheItem> cachedItems;
    private final List<String> cacheKeys;

    public CacheViewImpl(Map<String, CacheItem> cachedItems, List<String> cacheKeys) {
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
