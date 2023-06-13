package com.javarush.task.task19.task1910;

import java.io.*;
import java.util.ArrayList;

/* 
Пунктуация
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bufferedFileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
             BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter(bufferedReader.readLine()))) {

            while (bufferedFileReader.ready()) {
                String line = bufferedFileReader.readLine();
                bufferedFileWriter.write(line.replaceAll("[\\p{P}\n]", ""));
            }
        }
    }
}
