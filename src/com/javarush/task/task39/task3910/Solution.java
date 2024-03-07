package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/

public class Solution {
    public static void main(String[] args) {
        System.out.printf("%d = %s\n", 9, isPowerOfThree(9));
        System.out.printf("%d = %s\n", 1, isPowerOfThree(1));
    }

    public static boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        while (n % 3 == 0)
            n /= 3;
        return n == 1;
    }
}
