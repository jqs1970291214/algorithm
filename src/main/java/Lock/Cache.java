package Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Descriptions 读写锁使用
 * @Company
 * @Author Junqson
 * @Date 2019/1/22 10:27
 * @Version 1.0
 */
public class Cache {
    private static Map<String, Object> data = new HashMap<>();
    private static ReadWriteLock rwl = new ReentrantReadWriteLock();

    private static Lock r = rwl.readLock();
    private static Lock w = rwl.writeLock();


    public static Object get(String key) {
        r.lock();
        try {
            return data.get(key);
        } finally {
            r.unlock();
        }
    }

    public static void set(String key, Object value) {
        w.lock();
        try {
            data.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static void clear() {
        w.lock();
        try {
            data.clear();
        } finally {
            w.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Map<String, Object> map = new HashMap<>();
        Thread s1 = new Thread(new Put(map));
        s1.start();
        Thread s2 = new Thread(new Put(map));
        s2.start();
        s1.join();
        s2.join();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
//        new Thread(new Put(map)).start();
        System.out.println();


    }


    static class Put implements Runnable {

        private Map<String, Object> map;

        public Put(Map<String, Object> map) {
            this.map = map;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                set(Thread.currentThread().getName() + " | " + String.valueOf(i), i);
            }
        }
    }
}
