package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String path = bufferedReader.readLine();

        FileInputStream fileInputStream = new FileInputStream(path);
        while (fileInputStream.available() > 0) {
            System.out.write(fileInputStream.read());
        }
        bufferedReader.close();
        fileInputStream.close();
    }
}