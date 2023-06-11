package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(args[1]);
             FileOutputStream fileOutputStream = new FileOutputStream(args[2])) {
            while (fileInputStream.available() > 0) {
                fileOutputStream.write(fileInputStream.read() ^ 999);
            }
        }
    }
}
