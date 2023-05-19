package com.javarush.task.jdk13.task09.task0905;

/* 
Метод возвращает результат – глубину его стек-трейса
*/

public class Solution {
    public static void main(String[] args) {
        int deep = getStackTraceDepth();
    }

    public static int getStackTraceDepth() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int l = stackTraceElements.length;
        System.out.println(l);
        return l;
    }
}

