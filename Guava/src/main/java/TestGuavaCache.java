import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * guava cache使用案例
 */
public class TestGuavaCache {

    public static void main(String[] args) throws ExecutionException {

        /**
         * guava cache元素的创建方式推荐使用两种：
         * 1、cacheLoader方式
         * 2、callable方式
         */

        /**
         * 1、采用cacheLoader方式创建元素
         */

        //创建guava cache
//        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
//                //cache的初始容量
//                .initialCapacity(5)
//                //cache最大缓存数
//                .maximumSize(10)
//                //读/写过期时间，某个key在指定的时间内没有发生读/写操作，即为过期元素
//                .expireAfterAccess(10, TimeUnit.SECONDS)
//                //删除cache元素的监听器，即当监听到cache删除元素时，额外可以做些什么操作
//                .removalListener(new RemovalListener<String, String>() {
//                    @Override
//                    public void onRemoval(RemovalNotification<String, String> removalNotification) {
//                        //例如，我这里额外执行的操作是提示一下，此时删除的元素的key和value分别是什么；
//                        //当然，你可以根据实际情况做些有用的操作
//                        String key = removalNotification.getKey();
//                        String value = removalNotification.getValue();
//                        System.out.println("此时删除的key是: " + key + " , " + "此时删除的value是: " + value);
//                    }
//                })
//                //采用CacheLoader方式创建元素，当调用get从cache中获取指定key的值时，发现没有，则调用cacheLoader计算得到一个该key对应的值，并添加到cache中，然后返回该值
//                .build(new CacheLoader<String, String>() {
//                    @Override
//                    public String load(String key) throws Exception {
//                        return "我是" + key + "的值";
//                    }
//                });
//
//        /**
//         * get()从cache中获取指定key对应的元素，如果没有，则调用cacheLoader计算key对应的值（计算逻辑即为load()方法里的逻辑），并添加到cache，然后返回该值；
//         * 如果该key对应的值存在，则直接返回该值。
//         */
//        String a = loadingCache.get("a");
//        System.out.println(a);
//        String b = loadingCache.get("b");
//        System.out.println(b);
//
//        /**
//         * 显式删除cache中指定key对应的值；
//         * 当cache删除元素时，removalListener监听器可以监听到该操作，然后执行onRemoval方法；
//         * 当元素过期后，cache隐式删除它们时removalListener也是会监听到的，也会执行onRemoval方法;
//         * 如果指定key不存在，执行删除操作也不会抛异常。
//         */
//        loadingCache.invalidate("a");
//
//        /**
//         * put()方法也可以用于往cache中添加一个元素，但是put方式创建元素其value的计算调用cacheLoader的load()方法了，需要自行先生成值；
//         * 如果指定key已存在，则本次put操作将替换指定key原本的值；
//         * 否则，往cache中添加一个新元素；
//         * 但是put()方式创建元素的方式不被推荐。
//         */
//        String c = "我是c的值";
//        loadingCache.put("c",c);


        /**
         * 2、采用callable方式创建元素
         */
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(5)
                .maximumSize(10)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> removalNotification) {
                        String key = removalNotification.getKey();
                        String value = removalNotification.getValue();
                        System.out.println(key + "->" + value);
                    }
                })
                .build();

        /**
         * get()从cache中获取指定key对应的元素，如果没有，则调用callable的回调方法计算key对应的值，并添加到cache，然后返回该值；
         * 如果该key对应的值存在，则直接返回该值。
         */
        final String key_d = "hk";

        cache.put("hk","ggsmd");

        String d = cache.get(key_d, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "我是" + key_d + "的值";
            }
        });


        System.out.println("d： " + d);

        /**
         * 显式删除cache中指定key对应的值；
         * 当cache删除元素时，removalListener监听器可以监听到该操作，然后执行onRemoval方法；
         * 当元素过期后，cache隐式删除它们时removalListener也是会监听到的，也会执行onRemoval方法;
         * 如果指定key不存在，执行删除操作也不会抛异常。
         */
        //loadingCache.invalidate(key_d);

        /**
         * put()方法也可以用于往cache中添加一个元素，但是put方式创建元素其value的计算调用cacheLoader的load()方法了，需要自行先生成值；
         * 如果指定key已存在，则本次put操作将替换指定key原本的值；
         * 否则，往cache中添加一个新元素；
         * 但是put()方式创建元素的方式不被推荐。
         */
        //String e = "我是e的值";
        //loadingCache.put("e",e);
    }
}