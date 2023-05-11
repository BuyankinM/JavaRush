package com.javarush.task.jdk13.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] masInts = new int[20];
        for (int i = 0; i < 20; i++) {
            masInts[i] = Integer.parseInt(reader.readLine());
        }

        int[] mas1 = new int[10];
        int[] mas2 = new int[10];
        for (int i = 0; i < 10; i++) {
            mas1[i] = masInts[i];
            mas2[i] = masInts[i+10];
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(mas2[i]);
        }
    }
}
