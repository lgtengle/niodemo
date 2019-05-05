package com.lg.thread;

/**
 * <p>
 * description: 测试死锁
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-11 11:42
 */
public class DeadLockTest {

    private static Object obj1 = new Object();
    private static Object obj2 = new Object();
    public static void main(String[] args){

        new Thread(() -> {
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName() + "----获取了obj1锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName() + "----我要获取obj2");
                }
            }
        }).start();


        new Thread(() -> {
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName() + "----获取了obj2锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName() + "----我要获取obj1");
                }
            }
        }).start();
    }
}
