package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
    }

    public static boolean isPalindromePermutation(String s) {
        int[] counter = new int[256];
        for (char c : s.toLowerCase().toCharArray())
            counter[c]++;
        return Arrays.stream(counter).filter(x -> (x % 2) == 1).count() <= 1;
    }
}
