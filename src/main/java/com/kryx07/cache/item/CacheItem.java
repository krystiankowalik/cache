package com.kryx07.cache.item;

public interface CacheItem {
    /**
     * @return the key of the cache item - a unique identifier of the item within the underlying cache's collection.
     */
    String getKey();

    /**
     * @return the value of the cached item - the underlying Object
     */
    Object getValue();
}
