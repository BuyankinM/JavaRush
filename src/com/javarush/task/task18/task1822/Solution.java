package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String id = args[0].toString() + " ";

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();

        try (BufferedReader bufferedFileReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedFileReader.ready()) {
                String line = bufferedFileReader.readLine();
                if (line.startsWith(id)) {
                    System.out.println(line);
                    return;
                }
            }
        }
    }
}
