package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String name1 = bufferedReader.readLine();
            String name2 = bufferedReader.readLine();

            FileInputStream fis1 = new FileInputStream(name1);
            byte[] bytes1 = new byte[fis1.available()];
            fis1.read(bytes1);
            fis1.close();

            FileInputStream fis2 = new FileInputStream(name2);
            byte[] bytes2 = new byte[fis2.available()];
            fis2.read(bytes2);
            fis2.close();

            FileOutputStream fos1 = new FileOutputStream(name1);
            fos1.write(bytes2);
            fos1.write(bytes1);
            fos1.close();
        }
    }
}
