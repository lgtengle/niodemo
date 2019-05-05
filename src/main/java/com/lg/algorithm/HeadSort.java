package com.lg.algorithm;

/**
 * <p>
 * description: 堆排序
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-16 15:05
 */
public class HeadSort {

    public static void main(String[] args){
        int[] ints = NormalSort.randomArray(20);
        NormalSort.print(ints);
        /*int[] headArr = convertoHeadArray(ints);
        NormalSort.print(headArr);

        headSort(headArr);
        NormalSort.print(headArr);*/

        int beginIndex = ints.length - 1;
        for (int i = beginIndex; i > -1; i--) {
            maxHeapify(ints, i, beginIndex);
        }
        NormalSort.print(ints);
        for (int j = beginIndex; j > 0; j--) {
            NormalSort.swap(ints, 0, j);
            maxHeapify(ints, 0, j - 1);
        }
        NormalSort.print(ints);
    }


    /**
     * 获取指定数组索引位置最大的堆
     * @param arrays
     * @param index  数组索引
     * @param len 未排序的堆的长度（索引）
     */
    public static void maxHeapify(int[] arrays, int index, int len){
        int left = (index << 1) + 1;
        int right = left + 1;

        if (left > len) return;
        int maxIndex = left;
        if (right <= len && arrays[left] < arrays[right]){
            maxIndex = right;
        }
        if (arrays[index] < arrays[maxIndex]){
            NormalSort.swap(arrays, maxIndex, index);
            maxHeapify(arrays, maxIndex, len);
        }
    }

    public static void headSort(int[] arrays){
        int topIndex = arrays.length - 1;
        while (topIndex > 0){
            NormalSort.swap(arrays, topIndex, 0);


            int k = 0;
            while (true){
                int leftIndex = 2 * k + 1;
                int rightIndex = 2 * k + 2;

                if (leftIndex >= topIndex)
                    break;
                int maxIndex = leftIndex;
                if (rightIndex < topIndex && arrays[maxIndex] < arrays[rightIndex]){
                    maxIndex = rightIndex;
                }
                if (arrays[k] < arrays[maxIndex]){
                    NormalSort.swap(arrays, maxIndex, k);
                    k = maxIndex;
                }else
                    break;
            }
            topIndex--;
        }
    }

    public static int[] convertoHeadArray(int[] arrays){
        int[] headArr = new int[arrays.length];
        for (int i = 0; i < headArr.length; i++) {
            headArrInsert(headArr, arrays[i], i);
        }
        return headArr;
    }

    public static void headArrInsert(int[] headArr, int e, int size){
        if (size == 0){
            headArr[0] = e;
        }else {
            headArr[size] = e;
            int k = size;
            while (k > 0){
                int parent = (k - 1) >>> 1;
                if (headArr[parent] < e) {
                    NormalSort.swap(headArr, parent, k);
                    k = parent;
                }else
                    break;
            }
        }

    }

}
