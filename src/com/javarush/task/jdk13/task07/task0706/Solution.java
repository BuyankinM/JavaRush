package com.javarush.task.jdk13.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улица и дома
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int[] mas = new int[15];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int counter = 0;
        for (int i = 0; i < mas.length; i++) {
            int people = Integer.parseInt(reader.readLine());
            mas[i] = people;
            counter += (i % 2 == 0) ? people: -people;
        }

        if (counter > 0) {
            System.out.println("В домах с четными номерами проживает больше жителей.");
        } else if (counter < 0) {
            System.out.println("В домах с нечетными номерами проживает больше жителей.");
        }
    }
}
