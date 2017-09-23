package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CacheViewImplTest {

    private CacheView cacheView;

    @Before

    public void setUp() throws Exception {
        cacheView = new CacheViewImpl(new CircularFifoQueue<>());
    }

    @Test
    public void sizeOfEmptyCollectionShouldBeZero() throws Exception {
        assertEquals("Size of empty collection should be zero", 0, cacheView.size());
    }

    @Test
    public void sizeOfNonEmptyCollectionShouldReturnItsNumberOfElements() {
        CircularFifoQueue<CacheItem> sampleCache = new CircularFifoQueue<>(3001);
        int i;
        for (i = 0; i <= 3000; ++i) {
            sampleCache.add(new CacheItemImpl(Character.toString((char) i), i));
        }
        cacheView = new CacheViewImpl(sampleCache);
        assertEquals(i, cacheView.size());
    }

    @Test
    public void sizeOfNonEmptyCollectionShouldReturnItsNumberOfElements2() {
        CircularFifoQueue<CacheItem> sampleCache = new CircularFifoQueue<>(3001);
        int i;
        int elementsCount = 0;
        for (i = 0; i <= 3000; ++i) {
            sampleCache.add(new CacheItemImpl(Character.toString((char) i), i));
            elementsCount++;
        }
        cacheView = new CacheViewImpl(sampleCache);
        assertEquals(elementsCount, cacheView.size());
    }

    @Test
    public void getItemByIndexTest() throws Exception {
        //add a sample cacheItem to the newly created listOrderedMap
        CircularFifoQueue sampleCache = new CircularFifoQueue();
        CacheItemImpl cacheItem = new CacheItemImpl("a", "A");
        sampleCache.add(cacheItem);
        cacheView = new CacheViewImpl(sampleCache);

        //check if you retrieved the same object
        assertEquals(sampleCache.get(0), cacheView.getItem(0));
        assertEquals(cacheItem, cacheView.getItem(0));
        assertEquals(new CacheItemImpl("a", "A"), cacheView.getItem(0));


    }

    @Test
    public void getItemByIndexStressTest() throws Exception {
        //set max size of the cache
        int size = 65_000;

        //generate cache contents in a list
        List<CacheItem> list = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            list.add(new CacheItemImpl(Character.toString((char) i), i));
        }

        //rewrite the contents of the list to listOrderedMap
        CircularFifoQueue<CacheItem> sampleCache = new CircularFifoQueue<>(size);
        sampleCache.addAll(list);
        cacheView = new CacheViewImpl(sampleCache);

        /*
        * check if the item under the queried index matches the corresponding item from the list and a newly created
        * item with the same key and value - may catch problems within the equals method of the underlying model object
        */

        for (int i = 0; i < cacheView.size(); ++i) {
            //System.out.println(list.get(i).getValue()+ "==" +cacheView.getItem(i).getValue());
            assertEquals(list.get(i), cacheView.getItem(i));
            assertEquals(new CacheItemImpl(Character.toString((char) i), i), cacheView.getItem(i));
        }

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
        CircularFifoQueue<CacheItem> sampleCache = new CircularFifoQueue<>(size);
        sampleCache.addAll(list);
        cacheView = new CacheViewImpl(sampleCache);

        /*
        * check if the item under the queried index matches the corresponding item from the list and a newly created
        * item with the same key and value - may catch problems within the equals method of the underlying model object
        */

        for (int i = 0; i < cacheView.size(); ++i) {
            //System.out.println(list.get(i)+"=="+cacheView.getItem(Character.toString((char) i)));
            assertEquals(list.get(i), cacheView.getItem(Character.toString((char) i)));
            assertEquals(new CacheItemImpl(Character.toString((char) i), i), cacheView.getItem(Character.toString((char) i)));
        }

    }

    @Test
    public void getItemByKeyTest() throws Exception {
        int size = 3000;
        CircularFifoQueue<CacheItem> sampleCache = new CircularFifoQueue<>(size);
        for (int i = 0; i < size; ++i) {
            sampleCache.add(new CacheItemImpl(Character.toString((char) i), i));
        }
        cacheView = new CacheViewImpl(sampleCache);

        int queriedChar = 500;
        assertEquals(new CacheItemImpl(Character.toString((char) queriedChar), queriedChar), cacheView.getItem(Character.toString((char) queriedChar)));
        assertEquals(new CacheItemImpl("a", 97), cacheView.getItem("a"));
        assertEquals(new CacheItemImpl("b", 98), cacheView.getItem("b"));
        assertEquals(new CacheItemImpl("c", 99), cacheView.getItem("c"));
        assertEquals(new CacheItemImpl("d", 100), cacheView.getItem("d"));

    }


}