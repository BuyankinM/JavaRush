package com.javarush.task.jdk13.task09.task0909;

/* 
Исключение при работе с массивами
*/

public class Solution {

    public static void main(String[] args) {
        try {
            int[] array = new int[2];
            array[8] = 5;
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            System.out.println(aiobe);
        }
    }
}
