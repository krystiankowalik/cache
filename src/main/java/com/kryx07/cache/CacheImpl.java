package com.kryx07.cache;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import com.kryx07.cache.view.CacheView;
import com.kryx07.cache.view.CacheViewImpl;

import java.util.LinkedHashMap;
import java.util.Map;

final public class CacheImpl implements Cache {

    private Map<String, CacheItem> cachedItems;
    private String[] cacheKeys;

    private int cacheKeysCount;
    private final int maxCacheSize;

    public CacheImpl(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        this.cacheKeysCount = 0;
        this.cachedItems = generateMapOfInitCapacity(maxCacheSize);
        this.cacheKeys = generateArrayOfLength(maxCacheSize);
    }

    private Map<String, CacheItem> generateMapOfInitCapacity(int initCapacity) {
        return new LinkedHashMap<String, CacheItem>(initCapacity) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return cachedItems.size() > maxCacheSize;
            }
        };
    }

    private String[] generateArrayOfLength(int length) {
        return new String[length];
    }

    private String[] removeEldestEntryInArray(String[] arr) {
        --cacheKeysCount;
        String[] newArr = new String[arr.length];
        System.arraycopy(arr, 1, newArr, 0, arr.length - 1);
        return newArr;
    }

    private synchronized CacheItem put(CacheItem cacheItem, String key) {
        CacheItem existingCacheItem = cachedItems.put(key, cacheItem);
        if (existingCacheItem == null) {
            if (cacheKeysCount == maxCacheSize) {
                cacheKeys = removeEldestEntryInArray(cacheKeys);
            }
            cacheKeys[cacheKeysCount] = key;
            ++cacheKeysCount;
        }
        return existingCacheItem;
    }

    @Override
    public CacheItem cacheItem(Object item, String key) {
        CacheItem newCacheItem = new CacheItemImpl(key, item);
        CacheItem existingCacheItem = put(newCacheItem, key);
        return existingCacheItem != null ? existingCacheItem : newCacheItem;
    }

    @Override
    public synchronized void invalidateCache() {
        cachedItems = generateMapOfInitCapacity(maxCacheSize);
        cacheKeys = generateArrayOfLength(maxCacheSize);
    }

    @Override
    public synchronized CacheView getView() {
        return new CacheViewImpl(cachedItems, cacheKeys);
    }
}
