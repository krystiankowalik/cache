package com.kryx07.cache.view;

import com.kryx07.cache.Cache;
import com.kryx07.cache.CacheImpl;
import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class CacheViewImplTest {

    private CacheView cacheView;
    private Cache cache;

    @Before
    public void setUp() throws Exception {
        cache = new CacheImpl(30);
        cacheView = cache.getView();
    }

    @Test
    public void sizeOfEmptyCollectionShouldBeZero() throws Exception {
        cache = new CacheImpl(0);
        assertEquals("Size of empty collection should be zero", 0, cacheView.size());
    }

    @Test
    public void sizeOfNonEmptyCollectionShouldReturnItsNumberOfElements() {
        int size = 3000;
        cache = new CacheImpl(size);
        int i = 0;
        for (i = 0; i < size; ++i) {
            cache.cacheItem(i, Character.toString((char) i));
        }

        assertEquals(i, cache.getView().size());
    }

    @Test
    public void getItemByIndexTest() throws Exception {
        //add a sample cacheItem to the newly created listOrderedMap
        CacheItemImpl cacheItem = new CacheItemImpl("a", "A");
        cache = new CacheImpl(10);
        cache.cacheItem("A", cacheItem.getKey());

        //check if you retrieved the same object
        assertEquals(cacheItem, cache.getView().getItem(0));
        assertEquals(new CacheItemImpl("a", "A"), cache.getView().getItem(0));


    }

    @Test//(timeout = 500)
    public void getItemByIndexStressTest() throws Exception {
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
        List<String> listOfKeys = list.stream().map(CacheItem::getKey).collect(Collectors.toList());
        cacheView = new CacheViewImpl(sampleCache, listOfKeys);

        /*
        * check if the item under the queried index matches the corresponding item from the list and a newly created
        * item with the same key and value - may catch problems within the equals method of the underlying model object
        */

        for (int i = 0; i < cacheView.size(); ++i) {
            assertEquals(list.get(i), cacheView.getItem(i));
            assertEquals(new CacheItemImpl(Character.toString((char) i), i), cacheView.getItem(i));
        }

    }

    @Test
    public void getItemByKeyTest() throws Exception {
        int size = 3000;
        ListOrderedMap<String, CacheItem> sampleCache = new ListOrderedMap<>();
        for (int i = 0; i <= size; ++i) {
            sampleCache.put(Character.toString((char) i), new CacheItemImpl(Character.toString((char) i), i));
        }
        List<CacheItem> list = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            list.add(new CacheItemImpl(Character.toString((char) i), i));
        }
        List<String> listOfKeys = list.stream().map(CacheItem::getKey).collect(Collectors.toList());
        cacheView = new CacheViewImpl(sampleCache, listOfKeys);

        int queriedChar = 500;
        assertEquals(new CacheItemImpl(Character.toString((char) queriedChar), queriedChar), cacheView.getItem(Character.toString((char) queriedChar)));
        assertEquals(new CacheItemImpl("a", 97), cacheView.getItem("a"));
        assertEquals(new CacheItemImpl("b", 98), cacheView.getItem("b"));
        assertEquals(new CacheItemImpl("c", 99), cacheView.getItem("c"));
        assertEquals(new CacheItemImpl("d", 100), cacheView.getItem("d"));

    }


}