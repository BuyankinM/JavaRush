package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 0, 1},
                {1, 1, 1},
                {0, 1, 0}};
        System.out.println(maxSquare(matrix));
    }

    public static int maxSquare(int[][] matrix) {
        int maxLen = 0;
        int h = matrix.length;
        int w = matrix[0].length;
        int[][] dp = new int[h][w];

        for (int i = 0; i < h; i++) {
            dp[i][0] = matrix[i][0];
            if (matrix[i][0] == 1) maxLen = 1;
        }

        for (int j = 0; j < w; j++) {
            dp[0][j] = matrix[0][j];
            if (matrix[0][j] == 1) maxLen = 1;
        }

        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    if (dp[i][j] > maxLen) maxLen = dp[i][j];
                }
            }
        }
        return maxLen * maxLen;
    }
}
