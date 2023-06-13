package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        System.setOut(printStream);
        testString.printSomething();
        System.setOut(console);

        String[] parts = byteArrayOutputStream.toString().split(" ");
        String op = parts[1];
        int dig1 = Integer.parseInt(parts[0]);
        int dig2 = Integer.parseInt(parts[2]);
        int res = 0;

        switch (op) {
            case "+":
                res = dig1 + dig2;
                break;
            case "-":
                res = dig1 - dig2;
                break;
            case "*":
                res = dig1 * dig2;
                break;
        }

        System.out.printf("%d %s %d = %d", dig1, op, dig2, res);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

