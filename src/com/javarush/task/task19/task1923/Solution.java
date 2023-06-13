package com.javarush.task.task19.task1923;

import java.io.*;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))) {
            while (bufferedReader.ready()) {
                String[] words = bufferedReader.readLine().split(" ");
                for (String word : words) {
                    if (word.matches(".*\\d+.*")) {
                        bufferedWriter.write(word + " ");
                    }
                }
            }
        }
    }
}
