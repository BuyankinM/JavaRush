package com.javarush.task.task36.task3602;

import java.util.Collections;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
        Class expClass = null;
        for (Class declClass : declaredClasses) {
            if (declClass.getSimpleName().equals("EmptyList")) {
                expClass = declClass;
                break;
            }
        }
        return expClass;
    }
}
