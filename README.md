# Cache Implementation

Sample implementation of cache for java applications to improve performance for handling multiple deterministic requests.

## Specification

Application freezes due to recurring requests which yield identical results for the same sets of parameters. The cache to improve the performance in this case should implements the following interfaces:

```
public interface CacheItem {
    /**
     * @return the key of the cache item - a unique identifier of the item 
     * within the underlying cache's collection.
     */
    String getKey();

    /**
     * @return the value of the cached item - the underlying Object
     */
    Object getValue();
}

public interface CacheView {

    /**
     * @return the number of non-null elements within cache
     **/
    int size();

    /**
     * @param index integer, sequential number of the element of the cache
     * based on the order of addition
     * @return the cacheItem by index
     **/
    CacheItem getItem(int index);

    /**
     * @param key String unique identifier of the element
     * @return the cacheItem by its key.
     */
    CacheItem getItem(String key);
}

public interface Cache {

    /**
     * Saves an Object into the Cache under the specified key.
     *
     * @param item any object to be saved into the Cache
     * @param key  a String that the object will be associated with
     * @return returns the saved cacheItem
     */
    CacheItem cacheItem(Object item, String key);

    /**
     * Clears the cache
     */
    void invalidateCache();

    /**
     * @return CacheView
     */
    CacheView getView();
}


```

### Considerations

#### Computational complexity

Due to the nature of cache, it will require to save and retrieve operations. Therefore, the most importa

