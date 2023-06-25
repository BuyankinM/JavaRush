package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        try (BufferedReader fr1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName1), Charset.forName("Windows-1251")));
             BufferedWriter fw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName2), StandardCharsets.UTF_8));) {
            while (fr1.ready()) fw2.write(fr1.read());
        }
    }
}
