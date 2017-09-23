package com.kryx07.cache;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.view.CacheView;

public interface Cache {


    /**
     * Saves an Object into the Cache under the specified key.
     *
     * @param item any object to be saved into the Cache
     * @param key  a String that the object will be associated with
     * @return returns the saved cacheItem
     */
    CacheItem cacheItem(Object item, String key);

    /**
     * Clears the cache
     */
    void invalidateCache();

    /**
     * @return CacheView
     */
    CacheView getView();
}
