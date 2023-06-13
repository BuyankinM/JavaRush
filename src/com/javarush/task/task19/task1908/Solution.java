package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bufferedFileReader = new BufferedReader(new FileReader(consoleReader.readLine()));
             BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter(consoleReader.readLine()))) {

            String line = bufferedFileReader.readLine();
            Pattern pattern = Pattern.compile("((?<=\\s)|(^))\\d+((?=\\s)|$)");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                bufferedFileWriter.write(matcher.group() + " ");
            }
        }
    }
}
