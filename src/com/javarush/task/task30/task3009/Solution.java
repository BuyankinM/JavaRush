package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        BigInteger num;
        try {
            num = new BigInteger(number, 10);
        } catch (NumberFormatException e) {
            return result;
        }

        for (int i = 2; i < 37; i++) {
            String numStr = num.toString(i);
            int cnt = 0;
            int len = numStr.length();
            for (int j = 0; j < len / 2; j++) {
                if (numStr.charAt(j) == numStr.charAt(len - j - 1)) cnt++;
                else break;
            }
            if (cnt == len / 2) result.add(i);
        }
        return result;
    }
}