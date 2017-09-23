import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.item.CacheItemImpl;
import com.kryx07.cache.view.CacheView;
import com.kryx07.cache.view.CacheViewImpl;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CacheImplTest {

    ListOrderedMap<String, Object> sampleCacheContainer;
    Cache cache;

    @Before
    public void setUp() throws Exception {
        sampleCacheContainer = new ListOrderedMap<>();
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

    @Test(timeout = 300)
    public void stressTestObjectCacheTime(){
        for(int i=0; i<40; ++i){
            cache=generateSampleCache(10_000);
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


    private void printCache(Cache cache) {
        for (int i = 0; i < cache.getView().size(); ++i) {
            System.out.println(cache.getView().getItem(i).getKey() + ": " + cache.getView().getItem(i).getValue());
        }
    }

}