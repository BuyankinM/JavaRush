package com.javarush.task.task18.task1807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) {
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            FileInputStream fis = new FileInputStream(bufferedReader.readLine());
            while (fis.available() > 0){
                if (fis.read() == (int) ',') count++;
            }
            fis.close();
        } catch (IOException e) {
        }
        System.out.println(count);
    }
}
