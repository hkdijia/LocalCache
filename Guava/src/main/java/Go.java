import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Go {

    private static java.lang.Object Object;

    public static void main(String[] args) throws Exception {
        Cache<Object, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
        try {
            cache.get(Object, new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    //return doThingsTheHardWay(key);
                    return null;
                }
            });
        } catch (ExecutionException e) {
            throw new Exception(e.getCause());
        }
    }

}
