package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();

        StringBuilder sb = new StringBuilder();
        int n = 0;

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == ' ') n++;
            if (n == 5) break;
            if (n > 0) sb.append(c);
        }
        if (n < 4) throw new TooShortStringException();

        return sb.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
