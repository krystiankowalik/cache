package com.kryx07.cache.view;

import com.kryx07.cache.item.CacheItem;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Iterator;

public class CacheViewImpl implements CacheView {


    private CircularFifoQueue<CacheItem> cachedItems;

    public CacheViewImpl(CircularFifoQueue<CacheItem> cachedItems) {
        this.cachedItems = cachedItems;
    }

    @Override
    public synchronized int size() {
        return cachedItems.size();
    }

    @Override
    public synchronized CacheItem getItem(int index) {
        return cachedItems.get(index);
    }

    @Override
    public synchronized CacheItem getItem(String key) {
       /*
       * Stream filter - less efficient
       * */
        /*return cachedItems
                .stream()
                .peek(CacheItem::getValue)
                .filter(i -> i.getKey()
                        .equals(key))
                .findFirst()
                .orElseGet(null);*/


      /*
      * Binary search
      * */
        /*Object[] keys =  cachedItems.stream().map(cacheItem -> cacheItem.getKey()).toArray();
        return getItem(Arrays.binarySearch(keys, key));*/

        /*
        * Iterator
        * */
        Iterator<CacheItem> it = cachedItems.iterator();
        CacheItem tmpItem = null;
        while (it.hasNext()) {
            tmpItem = it.next();
            if (tmpItem.getKey().equals(key)) {
                break;
            }
        }
        return tmpItem;


    }

}



