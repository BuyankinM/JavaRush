package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/

public class Solution {
    private static int n = 3;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) return 0;
        else if (n == 0) return 1;

        long[] dp = new long[n + 3];
        dp[2] = 1L;

        for (int i = 0; i < n; i++)
            dp[i + 3] = dp[i] + dp[i + 1] + dp[i + 2];

        return dp[n + 2];
    }
}

