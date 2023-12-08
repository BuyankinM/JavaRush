package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/

public class Solution {
    private static Random generator;

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        generator = new Random();
        char[] numbers = "0123456789".toCharArray();
        char[] lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        ByteArrayOutputStream passwordStream = new ByteArrayOutputStream();
        passwordStream.write(getRandomChar(numbers));
        passwordStream.write(getRandomChar(lowerCaseLetters));
        passwordStream.write(getRandomChar(upperCaseLetters));

        for (int i = 0; i < 5; i++) {
            int type = generator.nextInt(3);
            if (type == 0) passwordStream.write(getRandomChar(numbers));
            else if (type == 1) passwordStream.write(getRandomChar(lowerCaseLetters));
            else  passwordStream.write(getRandomChar(upperCaseLetters));
        }

        return passwordStream;
    }

    private static char getRandomChar(char[] chars) {
        int i = generator.nextInt(chars.length);
        return chars[i];
    }
}
