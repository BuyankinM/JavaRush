package com.javarush.task.jdk13.task02.task0219;

/* 
Вывод текста на экран
*/

public class Solution {
    public static void print3(String text) {
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s ", text);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print3("window");
        print3("file");
    }
}
