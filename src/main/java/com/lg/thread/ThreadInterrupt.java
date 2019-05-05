package com.lg.thread;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Date;
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
 * @date 2019-04-26 11:04
 */
public class ThreadInterrupt {


    public static void main(String[] args){

        /*SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(new RuntimePermission("modifyThread"));
        }

        Thread thread1 = new Thread(() -> {
            System.out.println("啊哈哈哈哈哈");
            Thread wt = Thread.currentThread();
            int n = 1;
            while (n++ < 10000){
                if (n==50) {
                    try {
                        wt.interrupt();
                    }finally {
                        System.out.println("enenenenene");
                    }
                }else if (n == 1000){
                    System.out.println(wt.isInterrupted());
                    System.out.println(Thread.interrupted());
                    System.out.println(Thread.interrupted());
                }
            }
            System.out.println("11111111111111-----" + n);

        });
        security.checkAccess(thread1);
        thread1.start();*/
        testThreadPoolShutdown();
    }


    public static void testThreadPoolShutdown(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        threadPoolExecutor.submit(() -> {
            while (true){
                URL url = new URL("http://localhost:8080/test/longRequest");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                System.out.println("responseCode:" + responseCode);
                if (responseCode == 200){
                    InputStream inputStream = connection.getInputStream();
                    byte[] buf = new byte[1];
                    int read = inputStream.read(buf);
                    System.out.println(buf[0]);
                    inputStream.close();
                }
            }
        });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdownNow();
        System.out.println("shutdown了");

    }
}
