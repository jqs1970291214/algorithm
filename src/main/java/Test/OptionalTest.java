package Test;

import javafx.beans.binding.ObjectBinding;
import sun.misc.Unsafe;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * summary
 * descriptions
 *
 * @author Junqson
 * @date 2018/11/13 14:11
 */
public class OptionalTest {

    private static void hashIt(int h) {
        System.out.println(Integer.toBinaryString(h));

        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >> 10);
        h += (h << 3);
        h ^= (h >> 6);
        h += (h << 2) + (h << 14);
        h = h ^ (h >>> 16);

        System.out.println(Integer.toBinaryString(h));

    }


    public static void main(String[] args) {
        hashIt(Integer.parseInt("10001111", 2));
        hashIt(Integer.parseInt("11001111", 2));
        hashIt(Integer.parseInt("10011111", 2));
        hashIt(Integer.parseInt("01011111", 2));


    }


    public void test() {
        System.out.println("hello world");

    }

}




