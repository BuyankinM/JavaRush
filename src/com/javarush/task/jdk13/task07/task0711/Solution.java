package com.javarush.task.jdk13.task07.task0711;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Удали последнюю строку и вставь её в начало
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arrayList = new ArrayList<String>(5);
        for (int i = 0; i < 5; i++) {
            arrayList.add(bufferedReader.readLine());
        }
        for (int i = 0; i < 13; i++) {
            String s = arrayList.remove(4);
            arrayList.add(0, s);
        }
        for (String s : arrayList) {
            System.out.println(s);
        }
    }
}
