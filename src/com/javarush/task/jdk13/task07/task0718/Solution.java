package com.javarush.task.jdk13.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Сортировка списка
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>(10);

        int minLen = 0, minInd = -1;
        for (int i = 0; i < 10; i++) {
            String s = bufferedReader.readLine();
            list.add(s);
            if (s.length() >= minLen) {
                minLen = s.length();
            } else if (minInd == -1) {
                minInd = i;
            }
        }

        if (minInd > -1) {
            System.out.println(minInd);
        }
    }
}

