package designpattern.observer.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.awt.*;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @Descriptions 事件总线实现
 * @Company
 * @Author Junqson
 * @Date 2020/4/14 18:39
 * @Version 1.0
 */
public class EventBus {

    private ObserverRegistry registry = new ObserverRegistry();
    private Executor executor;

    public EventBus() {
        this(MoreExecutors.directExecutor());
    }

    public EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object observer) {
        registry.register(observer);
    }

    public void post(Object event) {
        List<ObserverAction> matched = registry.getMatchedObserverActions(event);
        for (ObserverAction action : matched) {
            executor.execute(() -> {
                action.execute(event);
            });
        }
    }
}

class TestEventBus {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Os1());
        eventBus.register(new Os2());
        eventBus.post("hhhhh");

        EventBus asyncEventBus = new AsyncEventBus();
        asyncEventBus.register(new Os1());
        asyncEventBus.register(new Os2());
        asyncEventBus.post("hhhh");
    }
}

class Os1 {
    @Subscribe
    public void ob1(String msg) {
        System.out.println("[" + Thread.currentThread().getName() + "] os1 start consume");
        System.out.println(msg);
        System.out.println("consume over");
    }
}

class Os2 {
    @Subscribe
    public void ob2(Object msg) {
        System.out.println("[" + Thread.currentThread().getName() + "] os2 start consume");
        System.out.println(msg);
        System.out.println("consume over");
    }
}
