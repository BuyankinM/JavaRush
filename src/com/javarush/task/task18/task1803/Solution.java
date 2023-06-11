package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            FileInputStream fileInputStream = new FileInputStream(bufferedReader.readLine());
            while (fileInputStream.available() > 0) {
                int b = fileInputStream.read();
                hashMap.merge(b, 1, Integer::sum);
            }
            fileInputStream.close();
        }

        int maxVal = Collections.max(hashMap.values());
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == maxVal) {
                System.out.printf("%d ", entry.getKey());
            }
        }
    }
}
