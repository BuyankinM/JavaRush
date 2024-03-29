package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bfr = new BufferedReader(new FileReader(br.readLine()))) {

            while (bfr.ready()) {
                int count = 0;
                String line = bfr.readLine();
                String[] wordsArray = line.split(" ");
                for (String word : wordsArray) {
                    if (words.contains(word)) count++;
                }
                if (count == 2) System.out.println(line);
            }
        }
    }
}
