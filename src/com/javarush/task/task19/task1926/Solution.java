package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Перевертыши
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bufferedFileReader = new BufferedReader(new FileReader(br.readLine()))) {
            while (bufferedFileReader.ready()) {
                StringBuilder sb = new StringBuilder(bufferedFileReader.readLine());
                System.out.println(sb.reverse());
            }
        }
    }
}
