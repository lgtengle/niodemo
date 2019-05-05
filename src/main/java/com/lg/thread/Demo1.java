package com.lg.thread;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-02-23 11:50
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        /*Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("嗯嗯嗯呃");
        });
        thread.start();
        thread.join(1000);
        System.out.println("哈哈哈");*/

        /*Thread thread1 = new Thread(new TestYield("1"));
        Thread thread2 = new Thread(new TestYield("2"));
        thread1.start();
        thread2.start();*/
        Pattern p3 = Pattern.compile("value=(\"|')([^'\"|.]{0,1})");
        Matcher m3 = p3.matcher("<input value=\"'\" >");
        System.out.println(m3.find());
        System.out.println(m3.group(2).length());
    }

    static Object object = new Object();

    static class TestYield implements Runnable{

        String name;

        public TestYield(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (object){
                System.out.println("-------" + name + "----start");
                while (true){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-------" + name + "---------------我放弃执行权利了");
                    Thread.yield();
                    System.out.println("-------" + name + "---------------end");
                }

            }
        }
    }
}
