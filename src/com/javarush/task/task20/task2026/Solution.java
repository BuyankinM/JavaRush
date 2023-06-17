package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int rows = a.length;
        int cols = a[0].length;

        int[][] map = new int[rows][cols];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int p = a[i][j];
                if (p == 0) continue;

                int idRect = 0;
                if (i > 0 && a[i - 1][j] == 1) {
                    idRect = map[i - 1][j];
                } else if (j > 0 && a[i][j - 1] == 1) {
                    idRect = map[i][j - 1];
                }
                map[i][j] = (idRect == 0) ? ++count : idRect;
            }
        }
        return count;
    }
}
