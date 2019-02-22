import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolChooser;
import sun.nio.ch.ThreadPool;

import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/1/21 10:11
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        for (Integer i : hashSet) {
            System.out.println(i);
        }
    }
}


enum alpha {
    A,B,C;

    public void say() {
        System.out.println("sad");
    }


}
