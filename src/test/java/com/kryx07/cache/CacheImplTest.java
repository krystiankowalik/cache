package com.kryx07.cache;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import com.kryx07.cache.view.CacheView;
import com.kryx07.cache.view.CacheViewImpl;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CacheImplTest {

    Map<String, Object> sampleCacheContainer;
    Cache cache;

    @Before
    public void setUp() throws Exception {
        sampleCacheContainer = new LinkedHashMap<>();
        cache = new CacheImpl(5);
    }

    @Test
    public void cachedItemShouldNotBeNull() throws Exception {
        /*generate a sample cache*/
        cache = generateSampleCache(3);

        /*check that the cache is full*/
        for (int i = 0; i < cache.getView().size(); ++i) {
            assertNotNull(cache.getView().getItem(0));
        }

    }


    @Test
    public void elementsOfClearedCacheShouldBeNull() throws Exception {

        cache = generateSampleCache(3);

        /*remove the cache's elements*/
        cache.invalidateCache();

        /*check that the cache is empty*/
        for (int i = 0; i < cache.getView().size(); ++i) {
            assertNull(cache.getView().getItem(0));
        }
    }

    @Test
    public void cacheViewShouldBeOfCacheViewType() throws Exception {

        cache = generateSampleCache(3);

        assertTrue(cache.getView() instanceof CacheView);
        assertTrue(cache.getView() instanceof CacheViewImpl);

    }

    @Test
    public void cacheSizeShouldNotExceedMaxCapacity() throws Exception {

        int cacheSize = 10;
        cache = generateSampleCache(cacheSize);

       assertEquals(cacheSize, cache.getView().size());

        cache.cacheItem(new Object(), "key1");
        assertEquals(cacheSize, cache.getView().size());


        cache.cacheItem(new Object(), "key2");
        assertEquals(cacheSize, cache.getView().size());


        cache.cacheItem(new Object(), "key3");
        assertEquals(cacheSize, cache.getView().size());


        cache.cacheItem(new Object(), "key4");
        assertEquals(cacheSize, cache.getView().size());
    }


    @Test(timeout = 450)
    public void stressTestObjectCacheTime() {
        for (int i = 0; i < 25; ++i) {
            cache = generateSampleCache(5_000);
        }
    }

    private Cache generateSampleCache(int capacity) {
        /*generate a sample cache*/
        cache = new CacheImpl(capacity);

        /*fill cache with sample values*/
        for (int i = 1; i <= capacity; ++i) {
            cache.cacheItem("val" + i, "key" + i);
        }

        return cache;
    }

    @Test
    public void getItemByKeyStressTest() throws Exception {
        //set max size of the cache
        int size = 65_000;

        //generate cache contents in a list
        List<CacheItem> list = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            list.add(new CacheItemImpl(Character.toString((char) i), i));
        }

        //rewrite the contents of the list to listOrderedMap
        ListOrderedMap<String, CacheItem> sampleCache = new ListOrderedMap<>();
        for (int i = 0; i < list.size(); ++i) {
            sampleCache.put(list.get(i).getKey(), list.get(i));
        }
        CacheView cacheView = new CacheViewImpl(sampleCache);

        /*
        * check if the item under the queried index matches the corresponding item from the list and a newly created
        * item with the same key and value - may catch problems within the equals method of the underlying model object
        */

        for (int i = 0; i < cacheView.size(); ++i) {
            assertEquals(list.get(i), cacheView.getItem(Character.toString((char) i)));
            assertEquals(new CacheItemImpl(Character.toString((char) i), i), cacheView.getItem(Character.toString((char) i)));
        }

    }


    private void printCache(Cache cache) {
        for (int i = 0; i < cache.getView().size(); ++i) {
            System.out.println(cache.getView().getItem(i).getKey() + ": " + cache.getView().getItem(i).getValue());
        }
    }

}