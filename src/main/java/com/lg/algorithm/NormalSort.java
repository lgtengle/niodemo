package com.lg.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * description: 常见排序算法
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-18 11:28
 */
public class NormalSort {

    public static void main(String[] args){
        int n = 20;
        int[] ints = randomArray(n);
        print(ints);

        int[] des = new int[n];
        System.arraycopy(ints, 0, des,0, n);
        quickSort(des, 0, des.length - 1);
        print(des);
        //bubble(ints);
        //selectSort(ints);
        System.out.println(quickSelect(ints, 0, ints.length - 1, 3, ints.length));
    }

    /**
     * 冒泡排序：
     * 通过相邻的元素比较和交换，使较大的元素逐渐从前面移动到后面
     * 时间复杂度为n*n
     * @param arrays
     */
    public static void bubble(int[] arrays){
        for (int i = 0; i < arrays.length; i++) {
            //减去i+1是之后的已经有序，
            for (int j = 0; j < arrays.length - i - 1; j++) {
                if (arrays[j] > arrays[j + 1]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = temp;
                }
            }
        }
    }


    /**
     * 选择排序：
     * 依次选取无序数组中的最大的元素，移动到最右端最终使得整个数组有序
     * 时间复杂度为n*n
     * @param arrays
     */
    public static void selectSort(int[] arrays){
        for (int i = 0; i < arrays.length; i++) {
            int max = arrays[0];
            int index = 0;
            for (int j = 1; j < arrays.length - i; j++) {
                if (arrays[j] > max) {
                    max = arrays[j];
                    index = j;
                }
            }

            arrays[index] = arrays[arrays.length - i - 1];
            arrays[arrays.length - i - 1] = max;
        }
    }


    /**
     * 找出top k的数
     * @param arrays
     * @param head
     * @param tail
     * @param k top k的元素
     * @return
     */
    public static int quickSelect(int[] arrays, int head, int tail, int k, int length){
        if (head == tail)
            return arrays[head];

        int mid = partition(arrays, head, tail);
        int top = length - mid;
        if (k < top){
            return quickSelect(arrays, mid +1, tail, k, length);
        }else if (k > top){
            return quickSelect(arrays, head, mid - 1, k, length);
        }else {
            return arrays[mid];
        }

    }

    /**
     * 快速排序：
     * 通过一轮排序将待排序的元素分割成独立的两部分，其中一部分所有元素比另一部分元素小，然后
     * 分别对这两部分在进行快速排序，知道整个序列有序，最坏复杂度O(n2)，平均复杂度O(nlogn)
     * @param arrays
     */
    public static void quickSort(int[] arrays, int head, int tail){
        if (arrays == null || arrays.length <= 1)
            return;
        if (head < tail){
            int mid = partition2(arrays, head, tail);
            quickSort(arrays, head, mid - 1);
            quickSort(arrays, mid + 1, tail);
        }
    }

    public static int partition(int[] arrays, int left, int right){
        int first = arrays[left];
        while (left < right) {
            while (left < right && arrays[right] >= first){
                right--;
            }
            swap(arrays, left, right);

            while (left < right && arrays[left] <= first){
                left++;
            }
            swap(arrays, left, right);
        }
        return left;

    }

    /**
     * 快速排序第二种分割方法
     * @param arrays
     * @param left
     * @param right
     * @return
     */
    public static int partition2(int[] arrays, int left, int right){
        int pivot = arrays[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arrays[j] <= pivot){
                i++;
                swap(arrays, i, j);
            }
        }
        swap(arrays, right, i+1);
        return i+1;
    }


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
