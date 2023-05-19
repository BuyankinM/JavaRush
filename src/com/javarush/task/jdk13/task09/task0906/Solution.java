package com.javarush.task.jdk13.task09.task0906;

/* 
Логирование стек трейса
*/

public class Solution {

    public static void main(String[] args) {
        log("In method");
    }

    public static void log(String text) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.out.printf("%s: %s: %s",
                stackTraceElements[2].getClassName(),
                stackTraceElements[2].getMethodName(),
                text);
    }
}
