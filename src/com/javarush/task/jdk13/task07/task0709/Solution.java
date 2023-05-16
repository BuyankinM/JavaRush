package com.javarush.task.jdk13.task07.task0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/* 
Самая короткая строка
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> arrayList = new ArrayList<String>(5);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            String s = bufferedReader.readLine();
            if (s.length() < minLen) {minLen = s.length();}
            arrayList.add(s);
        }
        for (String s:arrayList) {
            if (s.length() == minLen) {
                System.out.println(s);
            }
        }
    }
}
