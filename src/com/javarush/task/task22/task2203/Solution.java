package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();

        int ind1 = -1, ind2 = -1;
        ind1 = string.indexOf('\t');
        
        if (ind1 > -1) ind2 = string.indexOf('\t', ind1 + 1);
        if (ind1 < 0 || ind2 < 0) throw new TooShortStringException();

        return string.substring(ind1 + 1, ind2);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
