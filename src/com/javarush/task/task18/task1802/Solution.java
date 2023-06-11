package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int minByte = Integer.MAX_VALUE;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            FileInputStream fileInputStream = new FileInputStream(bufferedReader.readLine());
            while (fileInputStream.available() > 0) {
                int b = fileInputStream.read();
                if (b < minByte) minByte = b;
            }
            fileInputStream.close();
        }
        System.out.println(minByte);
    }
}
