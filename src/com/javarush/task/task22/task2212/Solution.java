package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null
                || !telNumber.matches("[\\d\\(\\)\\+]+")) return false;

        if (telNumber.matches("\\+\\d{12}")
                || telNumber.matches("\\d{10}")
                || telNumber.matches("\\d*\\(\\d{3}\\)\\d+") && telNumber.length() == 12
                || telNumber.matches("\\+\\d+\\(\\d{3}\\)\\d+") && telNumber.length() == 15) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.printf("%s - %s%n", "+380501234567", checkTelNumber("+380501234567"));
        System.out.printf("%s - %s%n", "+38(050)1234567", checkTelNumber("+38(050)1234567"));
        System.out.printf("%s - %s%n", "(050)1234567", checkTelNumber("(050)1234567"));
        System.out.printf("%s - %s%n", "0(501)234567", checkTelNumber("0(501)234567"));
        System.out.printf("%s - %s%n", "+38)050(1234567", checkTelNumber("+38)050(1234567"));
        System.out.printf("%s - %s%n", "+38(050)123-45-67", checkTelNumber("+38(050)123-45-67"));
        System.out.printf("%s - %s%n", "050ххх4567", checkTelNumber("050ххх4567"));
        System.out.printf("%s - %s%n", "050123456", checkTelNumber("050123456"));
        System.out.printf("%s - %s%n", "(0)501234567", checkTelNumber("(0)501234567"));
    }
}
