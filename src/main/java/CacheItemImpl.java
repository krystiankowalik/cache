import java.util.stream.Stream;

public class CacheItemImpl implements CacheItem {

    private String key;
    private Object value;

    CacheItemImpl(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
