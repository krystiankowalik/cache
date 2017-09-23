package com.kryx07.cache;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Demo {

    public static void main(String[] args) {
        Map<String, CacheItem> map = new LinkedHashMap<>();
        for (int i = 0; i < 500; ++i) {
            map.putIfAbsent("key" + i, new CacheItemImpl("key" + i, "val" + i));
        }
        System.out.println(map);
        System.out.println(map.size());


        int cacheSize = 10;

        Cache cache = generateSampleCache(cacheSize);

        for (int i = 0; i < cacheSize; ++i) {
            System.out.println(cache.getView().getItem(i).getValue());
        }

        System.out.println(cache.getView().size());

        assertEquals(cacheSize, cache.getView().size());

        cache.cacheItem(new Object(), "key1");

        System.out.println(cache.getView().size());


        cache.cacheItem(new Object(), "key2");

        System.out.println(cache.getView().size());


        cache.cacheItem(new Object(), "key3");

        System.out.println(cache.getView().size());


        cache.cacheItem(new Object(), "key4");
    }

    private static Cache generateSampleCache(int capacity) {
        /*generate a sample cache*/
        Cache cache;
        cache = new CacheImpl(capacity);

        /*fill cache with sample values*/
        for (int i = 1; i <= capacity; ++i) {
            cache.cacheItem("val" + i, "key" + i);
            System.out.println(cache.getView().getItem("key"+i));
        }

        return cache;
    }
}
