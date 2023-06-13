package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]));
             BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]))) {
            List<String> wordsList = new ArrayList<>();
            while (br.ready()) {
                String[] words = br.readLine().split(" ");
                for (String word : words) {
                    if (word.length() > 6) wordsList.add(word);
                }
            }
            bw.write(String.join(",", wordsList));
        }
    }
}
