package Lock;

/**
 * 不可重入锁
 * descriptions
 *
 * @author Junqson
 * @date 2018/11/11 15:15
 */
public class Lock {
    private boolean isLock = false;

    public synchronized void lock() throws InterruptedException {
        if (isLock) {
            wait();
        }
        this.isLock = true;
    }

    public synchronized void unlock() {
        isLock = false;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new Lock();
        lock.lock();
        // do something
        lock.unlock();
    }



}
