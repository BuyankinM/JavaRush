package com.javarush.task.pro.task05.task0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Создаем двумерный массив
*/

public class Solution {
    public static int[][] multiArray;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());

        multiArray = new int[num][];
        for (int i = 0; i < num; i++) {
            int col = Integer.parseInt(reader.readLine());
            multiArray[i] = new int[col];
        }
    }
}
