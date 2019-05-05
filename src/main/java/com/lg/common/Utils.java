package com.lg.common;

import java.util.Random;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-20 09:27
 */
public class Utils {

    public static int[] randomArray(int n){
        int[] arrays = new int[n];
        Random random = new Random();
        int max = n < 100 ? 100 : n;
        for (int i = 0; i < n; i++) {
            arrays[i] = random.nextInt(max);
        }
        return arrays;
    }

    public static void print(int[] arrays){
        StringBuilder sb = new StringBuilder();
        for (int array : arrays) {
            sb.append(array);
            sb.append(",");
        }
        System.out.println(sb.toString());
    }

    public static void swap(int[] arrays, int i, int j){
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
}
