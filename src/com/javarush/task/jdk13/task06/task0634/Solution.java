package com.javarush.task.jdk13.task06.task0634;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Шахматная доска
*/

public class Solution {
    public static char[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());

        array = new char[num][num];

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                array[i][j] = (i % 2 == j % 2) ? '#' : ' ';
            }
        }

        for (char[] row : array) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
