package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(bufferedReader.readLine());
        int b = Integer.parseInt(bufferedReader.readLine());
        System.out.println(getNod(a, b));

        bufferedReader.close();
    }

    public static int getNod(int a, int b) {
        return (b == 0) ? a : getNod(b, a % b);
    }
}
