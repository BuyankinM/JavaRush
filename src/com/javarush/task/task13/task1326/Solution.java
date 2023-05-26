package com.javarush.task.task13.task1326;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        List<Integer> list = new ArrayList<>();

        boolean endLine = false;
        int num = 0;
        int sign = 1;

        FileInputStream fileInputStream = new FileInputStream(fileName);
        while (fileInputStream.available() > 0) {
            int b = fileInputStream.read();
            if (b <= 13) {
                if (!endLine) {
                    if (num % 2 == 0) list.add(num * sign);
                    num = 0;
                    sign = 1;
                    endLine = true;
                }
            } else if (b == (int) '-') {
                sign = -1;
            } else {
                int number = b - (int) '0';
                num = 10 * num + number;
                endLine = false;
            }
        }
        fileInputStream.close();

        if (num % 2 == 0) list.add(num * sign);

        Collections.sort(list);
        for (int x : list) {
            System.out.println(x);
        }
    }
}
