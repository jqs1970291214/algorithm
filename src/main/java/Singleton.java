/**
 * @Descriptions 常见的几种单例模式的实现
 * @Company
 * @Author Junqson
 * @Date 2019/3/27 10:24
 * @Version 1.0
 */
public class Singleton {

}


// 懒汉
class Lazy {
    private static Lazy instance = null;

    private Lazy() {

    }

    public static Lazy getInstance() {
        if (instance == null) {
            return instance = new Lazy();
        } else {
            return instance;
        }
    }
}

// 饿汉
class Hungery {
    private static final Hungery instance = new Hungery();
    private Hungery(){}
    public Hungery getInstance() {
        return instance;
    }
}


// 双重检验
class DoubleCheck {
    private static DoubleCheck instance = null;

    private DoubleCheck() {
    }

    public static DoubleCheck getInstance() {
        if (instance == null) {
            synchronized (DoubleCheck.class) {
                if (instance == null) {
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }
}

// 内部类
class InnerClass {
    private static class Inner {
        public static InnerClass innerClass = new InnerClass();
    }

    private InnerClass() {}

    public static InnerClass getInstance() {
        // 懒加载，第一次调用时，才会加载进来
        return Inner.innerClass;
    }
}

// 枚举
enum EnumSingleton {
    INSTANCE
}

