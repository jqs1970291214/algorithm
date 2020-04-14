package designpattern.observer.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2020/4/14 19:02
 * @Version 1.0
 */
public class AsyncEventBus extends EventBus {

    private static final int DEFAULT_THREAD_POOL_SIZE = 10;

    public AsyncEventBus() {
        super(Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE));
    }

    public AsyncEventBus(int poolSize) {
        super(Executors.newFixedThreadPool(poolSize));
    }
}

