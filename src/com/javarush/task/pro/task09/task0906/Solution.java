package com.javarush.task.pro.task09.task0906;

/*
Двоичный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        int decimalNumber = 6;
        System.out.println("Десятичное число " + decimalNumber + " равно двоичному числу " + toBinary(decimalNumber));
        String binaryNumber = "110";
        System.out.println("Двоичное число " + binaryNumber + " равно десятичному числу " + toDecimal(binaryNumber));
    }

    public static String toBinary(int decimalNumber) {
        if (decimalNumber <= 0) {
            return "";
        }

        String binary = "";
        while (decimalNumber > 0) {
            binary = ((decimalNumber % 2 == 1) ? "1" : "0") + binary;
            decimalNumber /= 2;
        }
        return binary;
    }

    public static int toDecimal(String binaryNumber) {
        if (binaryNumber == "" || binaryNumber == null) {
            return 0;
        }

        int decimal = 0;
        int l = binaryNumber.length();
        for (int i = 0; i < l; i++) {
            decimal += (binaryNumber.charAt(i) == '1' ? 1 : 0) * Math.pow(2D, l - i - 1);
        }
        return decimal;
    }
}
