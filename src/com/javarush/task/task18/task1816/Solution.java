package com.javarush.task.task18.task1816;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fi = new FileInputStream(args[0]);

        int i, count = 0;
        while ((i = fi.read()) != -1) {
            char c = ((char) i);
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') count++;
        }
        fi.close();

        System.out.println(count);
    }
}
