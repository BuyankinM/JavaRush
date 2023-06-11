package com.javarush.task.task18.task1821;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        try (FileInputStream fis = new FileInputStream(args[0])) {
            while (fis.available() > 0) {
                treeMap.merge(fis.read(), 1, Integer::sum);
            }
        }
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            System.out.printf("%c %d\n", entry.getKey(), entry.getValue());
        }
    }
}
