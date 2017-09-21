import com.kryx07.cache.item.CacheItem;
import com.kryx07.cache.view.CacheView;

public interface Cache {

    CacheItem cacheItem(Object item, String ket);

    void invalidateCache();

    CacheView getView();
}
