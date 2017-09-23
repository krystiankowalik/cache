package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;

import java.util.Iterator;
import java.util.Map;

public class CacheViewImpl implements CacheView {


    private Map<String, CacheItem> cachedItems;

    public CacheViewImpl(Map<String, CacheItem> cachedItems) {
        this.cachedItems = cachedItems;
    }

    @Override
    public synchronized int size() {
        return cachedItems.size();
    }

    @Override
    public synchronized CacheItem getItem(int index) {
        /*
         * getting element by index using list
        */
        //return new ArrayList<>(cachedItems.values()).get(index);

        /*
         * getting element by index using array
         **/
        //return (CacheItem) cachedItems.values().toArray()[index];

        /*
         * getting element by index using iterator (most efficient)
         **/
        Iterator<CacheItem> it = cachedItems.values().iterator();

        int i = 0;
        while (it.hasNext()) {
            if (i == index) {
                return it.next();
            }
            ++i;
            it.next();
        }
        return null;
    }

    @Override
    public synchronized CacheItem getItem(String key) {
        return cachedItems.get(key);
    }
}
