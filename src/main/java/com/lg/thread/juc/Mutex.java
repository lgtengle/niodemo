package com.lg.thread.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <p>
 * description: 独占式锁的示例
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-29 15:24
 */
public class Mutex implements Lock {


    /**
     * 同步器
     */
    static class Sync extends AbstractQueuedSynchronizer{

        /**
         * 锁是否处于占用状态
         * @return true表示是
         */
        @Override
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }

        /**
         * 获取锁
         * @param acquires
         * @return true表示成功
         */
        @Override
        public boolean tryAcquire(int acquires){
            if (compareAndSetState(0, 1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         * @param releases
         * @return true表示释放成功
         */
        @Override
        public boolean tryRelease(int releases){
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * 尝试获取锁，如果没有获取成功，直接返回false，否则返回true
     * @return
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    /**
     * 尝试获取锁并等待指定的时间，如果指定时间还没有获取到锁，则获取失败
     * @param time 等待的时间
     * @param unit 时间单位
     * @return true表示获取到锁
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
}

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    /**
     * 线程等待队列
     * @return
     */
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    /**
     * 是否处于锁的状态
     * @return
     */
    public boolean isLocked(){
        return sync.isHeldExclusively();
    }

    /**
     * 队列中是否还有等待的线程
     * @return
     */
    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }

    static int n = 0;

    static volatile int threadcount = 8;

    public static void main(String[] args){
        Mutex mutex = new Mutex();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadcount; i++) {
            new Thread(() -> {
                int count = 0;
                while (count++ < 10000000){
                    mutex.lock();
                    try {
                        n++;
                    }finally {
                        mutex.unlock();
                    }
                }
                mutex.lock();
                try {
                    threadcount--;
                }finally {
                    mutex.unlock();
                }

                //使用同步关键字
                /*while (count++ < 10000000){
                    synchronized (mutex){
                        n++;
                    }
                }
                synchronized (mutex) {
                    threadcount--;
                }*/

            }).start();
        }

        while (threadcount != 0){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(n);
    }

}
