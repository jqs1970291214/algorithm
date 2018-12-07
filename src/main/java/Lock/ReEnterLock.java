package Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * descriptions
 *
 * @author Junqson
 * @date 2018/11/11 15:19
 */
public class ReEnterLock {
    private boolean isLock = false;
    private Thread lockBy = null;
    private int lockcount = 0;

    public synchronized void lock() throws InterruptedException {
        if (isLock && Thread.currentThread() != lockBy) {
            wait();
        } else {
            isLock = true;
            lockcount++;
            lockBy = Thread.currentThread();
        }
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == lockBy) {
            lockcount--;
            if (lockcount == 0) {
                lockBy = null;
                isLock = false;
                notify();
            }
        }
    }


    public static void main(String[] args) {

    }
}
