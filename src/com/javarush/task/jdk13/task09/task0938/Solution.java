package com.javarush.task.jdk13.task09.task0938;

/* 
Двоичная кодировка
*/

public class Solution {

    public static void main(String[] args) {
        String string = "JavaRush";
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            print(charArray[i]);
        }
    }

    private static void print(int number) {
        String result = String.format("Номер символа %s в таблице Unicode - %d, а в двоичной системе - %s", (char) number, number, toBinary(number));
        System.out.println(result);
    }

    public static String toBinary(int number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.insert(0, number % 2 == 1 ? "1" : "0");
            number /= 2;
        }
        return sb.toString();
    }
}
