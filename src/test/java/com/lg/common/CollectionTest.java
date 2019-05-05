package com.lg.common;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-09 14:23
 */
public class CollectionTest {

    public void test(){
    }

    public void testList(){

    }

    @Test
    public void testMap(){
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("3", "3");
        linkedHashMap.put("2", "2");
        linkedHashMap.put("1", "1");
        linkedHashMap.put("4", "4");

        linkedHashMap.forEach((k, v) -> System.out.println(k + "---" + v));
        System.out.println("###############################################");

        TreeMap<Object, Object> treeMap = new TreeMap<>();
        treeMap.put("3", "3");
        treeMap.put("2", "2");
        treeMap.put("1", "1");
        treeMap.put("4", "4");
        treeMap.forEach((k, v) -> System.out.println(k + "---" + v));
        System.out.println("###############################################");

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("3", "3");
        hashMap.put("2", "2");
        hashMap.put("1", "1");
        hashMap.put("4", "4");
        hashMap.forEach((k, v) -> System.out.println(k + "---" + v));
    }

    @Test
    public void testLinkedHashMap(){
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("3", "3");
        linkedHashMap.put("2", "2");
        linkedHashMap.put("1", "1");
        linkedHashMap.put("4", "4");

        linkedHashMap.forEach((k, v) -> System.out.println(k + "---" + v));
        System.out.println("###############################################");

        linkedHashMap.get("2");
        linkedHashMap.get("1");

        linkedHashMap.forEach((k, v) -> System.out.println(k + "---" + v));
    }
}
