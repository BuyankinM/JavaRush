package com.javarush.task.pro.task05.task0512;

/* 
Создаем мультимассив
*/

public class Solution {

    public static int[][][] multiArray = new int[][][]{{{4, 8, 15}, {16}}, {{23, 42}, {}}, {{1}, {2}, {3}, {4, 5}}};

    public static void main(String[] args) {
        for (int[][] level_2 : multiArray) {
            for (int[] level_1 : level_2) {
                for (int x : level_1) {
                    System.out.printf("%d ", x);
                }
            }
            System.out.println();
        }
    }
}
