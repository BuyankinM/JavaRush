package com.javarush.task.task18.task1818;

import java.io.*;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             FileOutputStream fos1 = new FileOutputStream(bufferedReader.readLine());
             FileInputStream fis2 = new FileInputStream(bufferedReader.readLine());
             FileInputStream fis3 = new FileInputStream(bufferedReader.readLine());) {

            byte[] bytes2 = new byte[fis2.available()];
            fis2.read(bytes2);

            byte[] bytes3 = new byte[fis3.available()];
            fis3.read(bytes3);

            fos1.write(bytes2);
            fos1.write(bytes3);
        }
    }
}
