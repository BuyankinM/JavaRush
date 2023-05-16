package com.javarush.task.jdk13.task07.task0715;

import java.util.ArrayList;

/* 
Продолжаем мыть раму
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(3);
        arrayList.add("мама");
        arrayList.add("мыла");
        arrayList.add("раму");
        for (int i = 1; i <= 5; i += 2) {
            arrayList.add(i, "именно");
        }
        for (String s : arrayList) {
            System.out.println(s);
        }
    }
}
