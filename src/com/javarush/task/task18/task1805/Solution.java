package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        TreeSet<Integer> treeSet = new TreeSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            FileInputStream fis = new FileInputStream(bufferedReader.readLine());
            while (fis.available() > 0) treeSet.add(fis.read());
            fis.close();
        }
        for (Integer b : treeSet) System.out.printf("%d ", b);
    }
}
