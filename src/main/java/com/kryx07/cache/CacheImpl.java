package com.kryx07.cache;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import com.kryx07.cache.view.CacheView;
import com.kryx07.cache.view.CacheViewImpl;
import org.apache.commons.collections4.map.ListOrderedMap;

public class CacheImpl implements Cache {

    private ListOrderedMap<String, CacheItem> cachedItems;

    private int maxCacheSize;

    public CacheImpl(int maxCacheSize) {
        this.cachedItems = new ListOrderedMap<>();
        this.maxCacheSize = maxCacheSize;
    }

    private void checkSize() {
        if (cachedItems.size() > maxCacheSize) {
            cachedItems.remove(0);
        }
    }

    private synchronized CacheItem putIfAbsent(CacheItem cacheItem, String key) {
        CacheItem existingCacheItem = cachedItems.putIfAbsent(key, cacheItem);
        checkSize();
        return existingCacheItem;
    }

    @Override
    public CacheItem cacheItem(Object item, String key) {
        CacheItem newCacheItem = new CacheItemImpl(key, item);
        CacheItem existingCacheItem = putIfAbsent(newCacheItem, key);
        return existingCacheItem == null ? newCacheItem : existingCacheItem;
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
