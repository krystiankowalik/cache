package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;

public interface CacheView {

    int size();

    CacheItem getItem(int index);

    CacheItem getItem(String key);
}
