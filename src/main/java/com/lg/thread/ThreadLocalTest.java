package com.lg.thread;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-19 17:25
 */
public class ThreadLocalTest {

    static class LocalBean{
        private int n ;

        public int get(){
            return n;
        }

        public void incre(){
            n++;
        }
    }

    static ThreadLocal<LocalBean> LOCAL;

    public static void main(String[] args){
        LOCAL = new ThreadLocal<>();
    }
}
