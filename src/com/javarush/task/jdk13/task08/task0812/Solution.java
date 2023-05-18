package com.javarush.task.jdk13.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int n = 10;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(bufferedReader.readLine()));
        }

        int count = 1, max_count = 1;
        for (int i = 1; i < n; i++) {
            if (list.get(i).intValue() == list.get(i - 1).intValue()) {
                count++;
                if (count > max_count) {
                    max_count = count;
                }
            } else {
                count = 1;
            }
        }
        System.out.println(max_count);
    }
}
