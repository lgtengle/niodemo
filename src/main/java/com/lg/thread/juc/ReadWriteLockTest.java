package com.lg.thread.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * <p>
 * description: 读写锁实现
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-30 10:13
 */
public class ReadWriteLockTest implements ReadWriteLock, Lock{

    /**
     * 读模式
     */
    static int READ = 0;

    /**
     * 写模式
     */
    static int WRITE = 1;

    public RWSync rwSync = null;

    private int mode;

    public ReadWriteLockTest(int mode){
        this.mode = mode;
    }
    public ReadWriteLockTest(int mode, RWSync rwSync) {
        this.mode = mode;
        this.rwSync = new RWSync();
    }

    @Override
    public Lock readLock() {
        return new ReadWriteLockTest(READ, this.rwSync);
    }

    @Override
    public Lock writeLock() {
        return new ReadWriteLockTest(WRITE, this.rwSync);
    }

    @Override
    public void lock() {
        rwSync.acquireShared(mode);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        rwSync.acquireSharedInterruptibly(mode);
    }

    @Override
    public boolean tryLock() {
        return rwSync.tryReleaseShared(mode);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return rwSync.tryAcquireSharedNanos(mode, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        rwSync.releaseShared(mode);
    }

    @Override
    public Condition newCondition() {
        return rwSync.newCondition();
    }



    class RWSync extends AbstractQueuedSynchronizer{

        /**
         * 获取共享锁
         * @param mode 模式
         * @return
         */
        @Override
        public int tryAcquireShared(int mode){
            //如果当前是写锁
            int state = getState();
            if (state != 0 && mode == WRITE)
                return -1;
            else if (state == 0){ //此时没有锁，读写锁都可以获取
                if (mode == READ)
                    return compareAndSetState(0, -1) ? 1 : -1;
                if (mode == WRITE){
                    if (compareAndSetState(0, 1)) {
                        setExclusiveOwnerThread(Thread.currentThread());
                        return 1;
                    }
                }

            } else if (state < 0 && mode == READ){
                //获取读锁
                if (compareAndSetState(state, state - 1)){
                    return 1;
                }
            }
            return -1;
        }

        @Override
        public boolean tryReleaseShared(int mode){
            int state = getState();
            if (mode == READ && state < 0)
                return compareAndSetState(state, state + 1);
            else if (mode == WRITE && state == 1) {
                setState(0);
                setExclusiveOwnerThread(null);
                return true;
            }else
                return true;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }

    static int n = 0;

    static volatile int threadcount = 8;

    public static void main(String[] args){
        final ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest(-1, null);
        final Lock rk = readWriteLockTest.readLock();
        final Lock wk = readWriteLockTest.writeLock();
        for (int i = 0; i < threadcount; i++) {
            new Thread(() -> {
                int count = 0;
                while (count++ < 100000){
                    wk.lock();
                    try {
                        //System.out.println(Thread.currentThread().getName() + "----------" + n);
                        n++;
                    }finally {
                        wk.unlock();
                    }
                }

                wk.lock();
                try {
                    threadcount--;
                }finally {
                    wk.unlock();
                }

            }).start();
        }

        while (threadcount != 0){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(n);

    }
}
