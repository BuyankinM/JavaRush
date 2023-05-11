package com.javarush.task.jdk13.task07.task0703;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Два массива
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] masInt = new int[10];
        String[] masStr = new String[10];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            masStr[i] = s;
            masInt[i] = s.length();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(masInt[i]);
        }
    }
}
