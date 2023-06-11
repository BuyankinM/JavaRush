package com.javarush.task.task18.task1817;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        double nunSpace = 0, numAll = 0;
        try (FileInputStream fis = new FileInputStream(args[0])) {
            while (fis.available() > 0) {
                char c = (char) fis.read();
                if (c == ' ') nunSpace++;
                numAll++;
            }
        }
        System.out.printf("%.2f\n", 100.0 * nunSpace / numAll);
    }
}
