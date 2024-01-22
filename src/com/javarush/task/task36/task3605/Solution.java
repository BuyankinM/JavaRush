package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> characters = new TreeSet<>();

        String fileName = args[0];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                int read = bufferedReader.read();
                if (Character.isAlphabetic(read)) {
                    characters.add(Character.toLowerCase((char) read));
                }
            }
        }

        Iterator<Character> iterator = characters.iterator();
        for (int i = 0; i < 5 && iterator.hasNext(); i++) {
            System.out.print(iterator.next());
        }
    }
}
