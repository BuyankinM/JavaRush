package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeMap<String, Double> treeMap = new TreeMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            while (bufferedReader.ready()) {
                String[] parts = bufferedReader.readLine().split(" ");
                Double val = Double.parseDouble(parts[1]);
                treeMap.merge(parts[0], val, Double::sum);
            }
        }
        for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
