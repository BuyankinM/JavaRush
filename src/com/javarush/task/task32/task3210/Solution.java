package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long pos = Long.valueOf(args[1]);
        String text = args[2];

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            byte[] bytes = new byte[text.length()];

            raf.seek(pos);
            int read = raf.read(bytes, 0, text.length());
            String textFromFile = new String(bytes);

            String resStrCmp = String.valueOf(text.equals(textFromFile));
            raf.seek(raf.length());
            raf.write(resStrCmp.getBytes());
        }
    }
}
