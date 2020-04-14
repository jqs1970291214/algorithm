package designpattern.observer.eventbus;

import com.google.common.base.Preconditions;

import javax.print.attribute.standard.PresentationDirection;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Descriptions 观察者方法和event对应关系注册
 * @Company
 * @Author Junqson
 * @Date 2020/4/14 17:48
 * @Version 1.0
 */
public class ObserverRegistry {

    private ConcurrentMap<Class<?>, CopyOnWriteArrayList<ObserverAction>> registry = new ConcurrentHashMap<>();


    /**
     * 注册observer
     * @param observer
     */
    public void register(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);
        for (Map.Entry<Class<?>, Collection<ObserverAction>> entry : observerActions.entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<ObserverAction> actions = entry.getValue();

            CopyOnWriteArrayList<ObserverAction> registered = registry.get(eventType);
            if (registered == null) {
                registered = new CopyOnWriteArrayList<>();
                registry.put(eventType, registered);
            }
            registered.addAll(actions);
        }
    }

    /**
     * 获取event匹配到的ObserverAction
     *
     * @param event
     * @return
     */
    public List<ObserverAction> getMatchedObserverActions(Object event) {
        List<ObserverAction> matched = new ArrayList<>();
        Class<?> postedEventType = event.getClass();
        for (Map.Entry<Class<?>, CopyOnWriteArrayList<ObserverAction>> entry : registry.entrySet()) {
            Class<?> eventType = entry.getKey();
            List<ObserverAction> actions = entry.getValue();
            // eventType is superclass of postedEventType
            if (eventType.isAssignableFrom(postedEventType)) {
                matched.addAll(actions);
            }
        }
        return matched;
    }

    /**
     * 获取 observer 对应的 <EventType,ObserverAction>关系
     * @param observer
     * @return
     */
    private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = new HashMap<>();
        Class<?> clazz = observer.getClass();
        for (Method method : getAnnotatedMethods(observer)) {
            Class<?>[] paramTypes = method.getParameterTypes();
            Class<?> eventType = paramTypes[0];
            if (!observerActions.containsKey(eventType)) {
                observerActions.put(eventType, new ArrayList<>());
            }
            observerActions.get(eventType).add(new ObserverAction(observer, method));
        }
        return observerActions;
    }


    /**
     * 获取所有@Subserice注解的方法，只能用一个参数
     * @param obj
     * @return
     */
    private List<Method> getAnnotatedMethods(Object obj) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        List<Method> annotated = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] clazz = method.getParameterTypes();
                Preconditions.checkArgument(clazz.length == 1,
                        "Method %s has @Subscibe annotation but has %s params, methods must has exactly 1 param"
                ,method, clazz.length);
                annotated.add(method);
            }
        }
        return annotated;
    }

}
