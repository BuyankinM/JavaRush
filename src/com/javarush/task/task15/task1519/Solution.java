package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        while (!"exit".equals(input)) {
            long countDot = input.chars().filter(ch -> ch == '.').count();

            if (countDot == 1 && input.matches("-?[0-9\\.]+")) {
                print(Double.parseDouble(input));
            } else if (input.matches("-?[0-9]+")) {
                int x = Integer.parseInt(input);
                if (x > 0 && x < 128) print((short) x);
                else print(x);
            } else {
                print(input);
            }
            input = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
