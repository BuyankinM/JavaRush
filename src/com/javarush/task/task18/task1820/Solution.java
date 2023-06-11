package com.javarush.task.task18.task1820;

import java.io.*;
import java.nio.charset.StandardCharsets;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = bufferedReader.readLine();
        String name2 = bufferedReader.readLine();
        try (BufferedReader bufferedFileReader = new BufferedReader(new FileReader(name1));
             PrintWriter printWriter = new PrintWriter(new FileWriter(name2))) {

            while (bufferedFileReader.ready()) {
                String[] numStrings = bufferedFileReader.readLine().split(" ");
                for (String num : numStrings) {
                    double number = Double.parseDouble(num);
                    long roundedNumber = Math.round(number);
                    printWriter.print(roundedNumber + " ");
                }
            }
        }
    }
}
