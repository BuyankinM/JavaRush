package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bufferedFileReader = new BufferedReader(new FileReader(br.readLine()))) {
            while (bufferedFileReader.ready()) {
                String[] words = bufferedFileReader.readLine().split("[\\p{P}\\s]");
                count += Arrays.stream(words).filter(s -> s.equals("world")).count();
            }
        }
        System.out.println(count);
    }
}
