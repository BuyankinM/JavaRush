package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Дублируем текст
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             FileOutputStream fileOutputStream = new FileOutputStream(br.readLine())) {

            PrintStream console = System.out;

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);

            System.setOut(printStream);
            testString.printSomething();
            System.setOut(console);

            String s = byteArrayOutputStream.toString();
            System.out.print(s);

            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

