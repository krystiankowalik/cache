package com.kryx07.cache.item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CacheItemImplTest {

    private CacheItem cacheItem;

    @Before
    public void setUp() throws Exception {
        cacheItem = new CacheItemImpl("key", "value");
    }

    @Test
    public void getKeyTest() throws Exception {
        String message = "should return key";
        String expected = "key";

        assertEquals(message, expected, cacheItem.getKey());

    }

    @Test
    public void getValue() throws Exception {
        String message = "should return value";
        String expected = "value";

        assertEquals(message, expected, cacheItem.getValue());
    }

}