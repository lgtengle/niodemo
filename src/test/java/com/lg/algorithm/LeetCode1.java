package com.lg.algorithm;

import com.lg.common.Utils;
import org.junit.Test;

import java.util.Arrays;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-20 09:25
 */
public class LeetCode1 {

    /**
     * 在有序数组中找出两个数，使它们的和为 target。
     *
     */
    @Test
    public void twoSum(){
        int target = 30;
        int[] arrays = Utils.randomArray(50);
        Arrays.sort(arrays);
        Utils.print(arrays);
        int left = 0, right = arrays.length - 1;
        boolean isBreak = false;
        while (left < right){
            if (arrays[left] + arrays[right] > target){
                right--;
            }else if (arrays[left] + arrays[right] < target){
                left++;
            }else {
                isBreak = true;
                break;
            }
        }
        if (isBreak){
            System.out.println(String.format("left:%d, %d;  right:%d, %d", left, arrays[left], right, arrays[right]));
        }
    }


}
