package com.lg.algorithm;

import org.junit.Test;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-03-13 09:13
 */
public class StringTest {


    /**
     * 比较字符串是否是字串
     */
    @Test
    public void compareTwoStr(){
        String s = "abafeafg";
        String target = "afg";

        int i = 0, j = 0;
        int pos = 0;
        while (i < s.length() && j < target.length() ) {
            if (s.charAt(i) == target.charAt(j)) {
                if (pos == 0 || pos == i -1) {
                    j++;
                    pos = i;
                } else {
                    j = pos = 0;
                }
            }else {
                j = pos = 0;
            }
            i++;
        }
        if (j == target.length()){
            System.out.println("匹配");
        }

    }
}
