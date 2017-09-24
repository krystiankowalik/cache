package com.kryx07.cache.item;

import java.util.Objects;

final public class CacheItemImpl implements CacheItem {

    private String key;
    private Object value;

    public CacheItemImpl(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheItemImpl cacheItem = (CacheItemImpl) o;
        return Objects.equals(key, cacheItem.key) &&
                Objects.equals(value, cacheItem.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
