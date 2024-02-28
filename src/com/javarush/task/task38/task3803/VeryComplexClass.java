package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object primitives = new int[1];
        Integer[] integers = (Integer[]) primitives;
    }

    public void methodThrowsNullPointerException() {
        String s = null;
        s.equals("222");
    }

    public static void main(String[] args) {

    }
}
