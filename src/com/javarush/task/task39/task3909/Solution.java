package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

public class Solution {
    public static void main(String[] args) {
        String first = "";
        String second = "a";
        System.out.printf("First = \"%s\", Second = \"%s\", Res = %b\n", first, second, isOneEditAway(first, second));

        first = "a";
        second = "";
        System.out.printf("First = \"%s\", Second = \"%s\", Res = %b\n", first, second, isOneEditAway(first, second));

        first = "aa";
        second = "";
        System.out.printf("First = \"%s\", Second = \"%s\", Res = %b\n", first, second, isOneEditAway(first, second));

        first = "abc";
        second = "ab";
        System.out.printf("First = \"%s\", Second = \"%s\", Res = %b\n", first, second, isOneEditAway(first, second));

        first = "abc";
        second = "abcd";
        System.out.printf("First = \"%s\", Second = \"%s\", Res = %b\n", first, second, isOneEditAway(first, second));

        first = "abcdefg";
        second = "abcdfg";
        System.out.printf("First = \"%s\", Second = \"%s\", Res = %b\n", first, second, isOneEditAway(first, second));

        first = "abcdefg";
        second = "abcdifg";
        System.out.printf("First = \"%s\", Second = \"%s\", Res = %b\n", first, second, isOneEditAway(first, second));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null
                || second == null
                || Math.abs(first.length() - second.length()) > 1) return false;
        else if (first.isEmpty() || second.isEmpty()) return true;

        int l1 = first.length();
        int l2 = second.length();
        for (int i = 0; i < Math.min(l1, l2); i++) {
            char c1 = (i < l1) ? first.charAt(i) : 256;
            char c2 = (i < l2) ? second.charAt(i) : 256;
            if (c1 != c2) {
                if (l1 == l2) return first.substring(i + 1).equals(second.substring(i + 1));
                else if (l1 < l2) return first.substring(i).equals(second.substring(i + 1));
                else return first.substring(i + 1).equals(second.substring(i));
            }
        }
        return true;
    }
}
