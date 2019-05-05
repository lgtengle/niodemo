package com.lg.thread.juc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-20 16:15
 */
public class ConditionTest {

    static ReentrantLock lock = new ReentrantLock();

    static Condition avaiable = lock.newCondition();

    static Condition notFull = lock.newCondition();

    static Condition notEmpty = lock.newCondition();

    static Queue<Integer> data = new LinkedList<>();

    static int size = 20;

    static class Producer{

        public void produce(int i){
            lock.lock();
            try {
                while (data.size() == size){
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                data.add(i);
                System.out.println(Thread.currentThread().getName()+"-我生产了" + i);
                notEmpty.signal();
            }finally {
                lock.unlock();
            }
        }
    }

    static class Consumer{

        public void consume() {
            lock.lock();
            try {
                while (data.size() == 0) {
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notFull.signal();
                System.err.println(Thread.currentThread().getName()+"-我消费了" + data.poll());
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args){
        final Producer producer = new Producer();
        final Consumer consumer = new Consumer();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                producer.produce(i);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (;;) {
                consumer.consume();
            }
        }).start();
        new Thread(() -> {
            for (;;) {
                consumer.consume();
            }
        }).start();
    }
}
