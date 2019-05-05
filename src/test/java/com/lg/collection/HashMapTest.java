package com.lg.collection;

import java.util.HashMap;

/**
 * <p>
 * description: hashMap源码测试
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-20 16:59
 */
public class HashMapTest {

    public void testLoadFactor(){

        HashMap<String, String> map = new HashMap<>(16, 0.75f);
    }
}
