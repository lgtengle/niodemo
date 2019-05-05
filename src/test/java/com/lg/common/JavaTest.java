package com.lg.common;

import org.junit.Test;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-09 12:00
 */
public class JavaTest {

    @Test
    public void testString(){
        String s0 = "11111" + "222";
        for (int i = 0; i < s0.length(); i++) {
            s0 += i;
        }
        String s1 = new String(s0);
        String s2 = new String(s0);
        System.out.println(s0 == s2.intern());
        System.out.println(s1 == s2);
    }

    @Test
    public void testPlusString(){
        //10000 321
        String s = "1";
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s += i;
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void testBufferString(){
        //10000 2
        StringBuffer s = new StringBuffer("1");
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s.append(i);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void testStrBuilderString(){
        //10000 2
        StringBuilder s = new StringBuilder("1");
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s.append(i);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void testIntger(){
        Integer i1 = Integer.parseInt("-129");
        Integer i2 = Integer.parseInt("-129");
        System.out.println(i1 == i2);
    }

    @Test
    public void testStrReverse(){
        StringBuffer stringBuffer = new StringBuffer("1234");
        StringBuffer reverse = stringBuffer.reverse();
        System.out.println(reverse);
    }
}
