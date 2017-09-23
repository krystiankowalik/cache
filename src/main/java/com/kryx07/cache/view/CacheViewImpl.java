package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;

import java.util.ArrayList;
import java.util.Map;

public class CacheViewImpl implements CacheView {


    private Map<String, CacheItem> cachedItems;

    public CacheViewImpl(Map<String, CacheItem> cachedItems) {
        this.cachedItems = cachedItems;
    }

    @Override
    public synchronized int size() {
        return cachedItems.size();
    }

    @Override
    public synchronized CacheItem getItem(int index) {
        return new ArrayList<>(cachedItems.values()).get(index);
    }

    @Override
    public synchronized CacheItem getItem(String key) {
        return cachedItems.get(key);
    }
}
