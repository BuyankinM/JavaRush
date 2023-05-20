package com.javarush.task.jdk13.task09.task0930;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArraySorter {

    public void sort(String[] array) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();
        for (String s : array) {
            if (isNumber(s)) {
                numbers.add(s);
            } else {
                words.add(s);
            }
        }

        // sorting words by bubble sort
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.size() - i - 1; j++) {
                if (isGreaterThan(words.get(j), words.get(j + 1))) {
                    Collections.swap(words, j, j + 1);
                }
            }
        }

        // sorting numbers by bubble sort
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size() - i - 1; j++) {
                int num1 = Integer.parseInt(numbers.get(j));
                int num2 = Integer.parseInt(numbers.get(j+1));
                if (num1 < num2) {
                    Collections.swap(numbers, j, j + 1);
                }
            }
        }

        Iterator<String> itWords = words.iterator();
        Iterator<String> itNumbers = numbers.iterator();
        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) {
                array[i] = itNumbers.next();
            } else {
                array[i] = itWords.next();
            }
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public boolean isNumber(String text) {
        if (text.length() == 0) {
            return false;
        }

        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char character = chars[i];
            if ((i != 0 && character == '-') // Строка содержит '-'
                    || (!Character.isDigit(character) && character != '-') // или не цифра и не начинается с '-'
                    || (chars.length == 1 && character == '-')) // или одиночный '-'
            {
                return false;
            }
        }
        return true;
    }
}
