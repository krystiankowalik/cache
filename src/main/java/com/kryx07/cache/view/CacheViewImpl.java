package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;
import org.apache.commons.collections4.map.ListOrderedMap;

final public class CacheViewImpl implements CacheView {


    private final ListOrderedMap<String, CacheItem> cachedItems;

    public CacheViewImpl(ListOrderedMap<String, CacheItem> cachedItems) {
        this.cachedItems = cachedItems;
    }

    @Override
    public synchronized int size() {
        return cachedItems.size();
    }

    @Override
    public synchronized CacheItem getItem(int index) {
        return cachedItems.getValue(index);
    }

    @Override
    public synchronized CacheItem getItem(String key) {
        return cachedItems.get(key);
    }
}
