package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Самый богатый
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
        double maxVal = Collections.max(treeMap.values());
        for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
            if (entry.getValue() == maxVal) System.out.println(entry.getKey());
        }
    }
}
