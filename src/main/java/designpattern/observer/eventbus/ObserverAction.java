package designpattern.observer.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Descriptions 观察者方法
 * @Company
 * @Author Junqson
 * @Date 2020/4/14 17:42
 * @Version 1.0
 */
public class ObserverAction {
    private Object target;

    private Method method;


    public ObserverAction(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
