package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(input));
        while (!input.equals("exit")) {
            input = bufferedReader.readLine();
            bufferedWriter.write(input);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
