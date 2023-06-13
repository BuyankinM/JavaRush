package com.javarush.task.task19.task1909;

import java.io.*;
import java.util.ArrayList;

/* 
Замена знаков
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bufferedFileReader = new BufferedReader(new FileReader(br.readLine()));
             BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter(br.readLine()))) {

            while (bufferedFileReader.ready()) {
                char c = (char) bufferedFileReader.read();
                bufferedFileWriter.write(c == '.' ? '!' : c);
            }
        }
    }
}
