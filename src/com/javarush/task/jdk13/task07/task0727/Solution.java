package com.javarush.task.jdk13.task07.task0727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Нужно заменить функциональность программы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> strings = new ArrayList<String>();
        while (true) {
            String string = reader.readLine();
            if (string == null || string.isEmpty()) break;
            strings.add(string);
        }

        ArrayList<String> resultStrings = new ArrayList<String>();
        for (String string : strings) {
            StringBuilder stringBuilder = new StringBuilder();
            if (string.length() % 2 == 0) {
                stringBuilder.append(string);
                stringBuilder.append(" ");
                stringBuilder.append(string);
            } else {
                stringBuilder.append(string);
                stringBuilder.append(" ");
                stringBuilder.append(string);
                stringBuilder.append(" ");
                stringBuilder.append(string);
            }
            resultStrings.add(stringBuilder.toString());
        }

        for (String resultString : resultStrings) {
            System.out.println(resultString);
        }
    }
}
