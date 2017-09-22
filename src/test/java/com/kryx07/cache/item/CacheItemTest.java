package com.kryx07.cache.item;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheItemTest {

    private CacheItem cacheItem;

    @Before
    public void setUp() throws Exception {
        cacheItem = new CacheItemImpl("key", "value");
    }

    @After
    public void tearDown() throws Exception {
        cacheItem = null;
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