package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;

public interface CacheView {

    /**
     * @return the number of non-null elements within cache
     **/
    int size();

    /**
     * @param index integer, sequential number of the element of the cache based on the order of addition
     * @return the cacheItem by index
     **/
    CacheItem getItem(int index);

    /**
     * @param key String unique identifier of the element
     * @return the cacheItem by its key.
     */
    CacheItem getItem(String key);
}
