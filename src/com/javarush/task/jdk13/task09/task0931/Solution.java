package com.javarush.task.jdk13.task09.task0931;

/* 
Удаление пробелов
*/

public class Solution {
    public static void main(String[] args) {
        String stringWithoutSpaces = deleteSpaces("Добрый день! Как дела?");
        System.out.println(stringWithoutSpaces);
    }

    public static String deleteSpaces(String string) {
        return string.replace(" ", "");
    }
}
