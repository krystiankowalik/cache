package com.kryx07.cache;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import com.kryx07.cache.view.CacheView;
import com.kryx07.cache.view.CacheViewImpl;
import org.apache.commons.collections4.queue.CircularFifoQueue;

public class CacheImpl implements Cache {

    private CircularFifoQueue<CacheItem> cachedItems;


    public CacheImpl(int maxCacheSize) {
        this.cachedItems = new CircularFifoQueue<>(maxCacheSize);
    }

    @Override
    public CacheItem cacheItem(Object item, String key) {
        CacheItem cacheItem = new CacheItemImpl(key, item);
        synchronized (this) {
            cachedItems.add(cacheItem);
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
