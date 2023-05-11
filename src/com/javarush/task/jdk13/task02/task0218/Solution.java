package com.javarush.task.jdk13.task02.task0218;

/* 
Дублирование строки
*/

public class Solution {
    public static void print3(String text) {
        for (int i = 0; i < 3; i++) {
            System.out.println(text);
        }
    }

    public static void main(String[] args) {
        print3("I love you!");
    }
}
