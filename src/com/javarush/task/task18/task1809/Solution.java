package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            FileInputStream fi = new FileInputStream(bufferedReader.readLine());
            FileOutputStream fo = new FileOutputStream(bufferedReader.readLine());

            byte[] bytes = new byte[fi.available()];
            for (int i = fi.available() - 1; i >= 0; i--) {
                bytes[i] = (byte) fi.read();
            }
            fo.write(bytes);

            fi.close();
            fo.close();
        } catch (IOException e) {
        }
    }
}
