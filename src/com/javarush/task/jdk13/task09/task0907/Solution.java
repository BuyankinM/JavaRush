package com.javarush.task.jdk13.task09.task0907;

/* 
Исключение при работе с числами
*/

public class Solution {
    public static void main(String[] args) {
        try {
            int a = 42 / 0;
        }
        catch (ArithmeticException arithmeticException) {
            System.out.println(arithmeticException);
        }
    }
}
