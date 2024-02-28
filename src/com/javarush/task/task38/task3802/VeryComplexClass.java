package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        new InputStreamReader(new FileInputStream(""));
    }

    public static void main(String[] args) {
    }
}
