package Lock;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/3/29 17:52
 * @Version 1.0
 */
public class MyBlockingQueue {
    private int size = 16;
    private LinkedList<Object> queue;

    public MyBlockingQueue(int size) {
        if (size > 0) {
            this.size = size;
        }
        queue = new LinkedList<Object>();
    }

    public synchronized void put(Object object) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.offer(object);
        notifyAll();
    }

    public synchronized Object take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Object res = queue.poll();
        notifyAll();
        return res;
    }

    public static void main(String[] args) {
        MyBlockingQueue bq = new MyBlockingQueue(10);
        ExecutorService tp = Executors.newFixedThreadPool(10);


        tp.submit(() -> {
            try {
                Object r = null;
                while ((r = bq.take()) != null) {
                    System.out.println(r);
                    Thread.sleep(50);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 1; i < 100000; i++) {
            int finalI = i;
            tp.submit(() -> {
                final int ii = finalI;
                try {
                    bq.put(ii);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 1; i < 5; i++) {
            tp.submit(() -> {
                try {
                    Object r = null;
                    while ((r = bq.take()) != null) {
                        System.out.println(r);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


    }
}
