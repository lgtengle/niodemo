package com.lg.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-04 11:44
 */
public class FairAndUnFairLock {
    
    
    
    
    
    public static void main(String[] args){
        int n = 5;
        //System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(n, n, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < n; i++) {
            threadPoolExecutor.submit(() -> {
                sum.put(Thread.currentThread().getName(), 0);
                System.err.println(String.format("%s-----开始了", Thread.currentThread().getName()));
                while (true) {
                    if (test()){
                        System.out.println(String.format("%s-----break拉啦啦啦啦啦", Thread.currentThread().getName()));
                        break;
                    }
                    //System.out.println(Thread.currentThread().getName() + "---------" + count);
                }
                countDownLatch.countDown();
                System.out.println(String.format("%s执行完了。。。。。%d", Thread.currentThread().getName(), count));
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("关闭线程池前操作");
        threadPoolExecutor.shutdown();

        sum.forEach((k, v) -> System.out.println(String.format("%s执行了：%s次", k, v)));
    }
    
    static ReentrantLock lock = new ReentrantLock(false);
    
    static int count = 0;

    static Map<String, Integer> sum = new HashMap<>();
    
    public static boolean test(){
        lock.lock();
        try {
            count++;
            sum.put(Thread.currentThread().getName(), sum.get(Thread.currentThread().getName()) + 1);
            return count > 10000;
        }finally {
            lock.unlock();
        }
    }
}
