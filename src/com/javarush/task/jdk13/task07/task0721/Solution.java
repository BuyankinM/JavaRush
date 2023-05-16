package com.javarush.task.jdk13.task07.task0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Максимальное и минимальное числа в массиве
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maximum = Integer.MIN_VALUE;
        int minimum = Integer.MAX_VALUE;

        int[] array = new int[20];
        for (int i = 0; i < 20; i++) {
            int x = Integer.parseInt(reader.readLine());
            array[i] = x;

            if (x > maximum) {
                maximum = x;
            }
            if (x < minimum) {
                minimum = x;
            }
        }
        System.out.print(maximum + " " + minimum);
    }
}
