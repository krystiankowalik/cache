public interface Cache {

    CacheItem cacheItem(Object item,String ket);

    void invalidateCache();

    CacheView getView();
}
