package com.javarush.task.pro.task05.task0516;

import java.util.Arrays;

/* 
Заполняем массив
*/

public class Solution {

    public static int[] array = new int[21];
    public static int valueStart = 10;
    public static int valueEnd = 13;

    public static void main(String[] args) {
        int l = array.length;
        Arrays.fill(array, 0, l / 2 + l % 2, valueStart);
        Arrays.fill(array, l / 2 + l % 2, l, valueEnd);
        System.out.println(Arrays.toString(array));
    }
}
