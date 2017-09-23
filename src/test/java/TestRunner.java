import com.kryx07.cache.item.CacheItemImplTest;
import com.kryx07.cache.view.CacheViewImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CacheImplTest.class,
        CacheItemImplTest.class,
        CacheViewImplTest.class})
public class TestRunner {

}
