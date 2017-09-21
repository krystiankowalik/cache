package com.kryx07.cache.item;

public class CacheItemImpl implements CacheItem {

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
}
