import org.apache.commons.collections4.map.ListOrderedMap;

public class CacheViewImpl implements CacheView {


    private ListOrderedMap<String, CacheItem> cachedItems;

    CacheViewImpl(ListOrderedMap<String, CacheItem> cachedItems) {
        this.cachedItems = cachedItems;
    }

    @Override
    public int size() {
        return cachedItems.size();
    }

    @Override
    public CacheItem getItem(int index) {
        return cachedItems.getValue(index);
    }

    @Override
    public CacheItem getItem(String key) {
        return cachedItems.get(key);
    }
}
