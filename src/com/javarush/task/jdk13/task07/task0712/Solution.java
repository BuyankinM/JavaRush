package com.javarush.task.jdk13.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Минимальное или максимальное
*/

public class Solution {

    public static ArrayList<String> strings;

    public static void main(String[] args) throws IOException {
        strings = new ArrayList<>(10);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int minLen = Integer.MAX_VALUE;
        int maxLen = Integer.MIN_VALUE;
        int indexMinLen = 0, indexMaxLen = 0;

        for (int i = 0; i < 10; i++) {
            String s = bufferedReader.readLine();
            if (s.length() < minLen) {
                minLen = s.length();
                indexMinLen = i;

            }
            if (s.length() > maxLen) {
                maxLen = s.length();
                indexMaxLen = i;
            }
            strings.add(s);
        }

        int lenToFind = (indexMinLen < indexMaxLen) ? minLen : maxLen;
        for (String s : strings) {
            if (s.length() == lenToFind) {
                System.out.println(s);
                break;
            }
        }
    }
}
