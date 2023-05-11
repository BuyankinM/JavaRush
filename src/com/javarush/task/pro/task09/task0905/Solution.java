package com.javarush.task.pro.task09.task0905;

/*
Восьмеричный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        int decimalNumber = 21;
        System.out.println("Десятичное число " + decimalNumber + " равно восьмеричному числу " + toOctal(decimalNumber));
        int octalNumber = 25;
        System.out.println("Восьмеричное число " + octalNumber + " равно десятичному числу " + toDecimal(octalNumber));
    }

    public static int toOctal(int decimalNumber) {
        int octal = 0;
        double i = 0.0;

        while (decimalNumber > 0) {
            octal += (decimalNumber % 8) * Math.pow(10D, i);
            decimalNumber /= 8;
            i++;
        }
        return octal;
    }

    public static int toDecimal(int octalNumber) {
        int decimal = 0;
        double i = 0.0;

        while (octalNumber > 0) {
            decimal += (octalNumber % 10) *  Math.pow(8D, i);
            octalNumber /= 10;
            i++;
        }
        return decimal;
    }
}
