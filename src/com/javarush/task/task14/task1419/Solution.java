package com.javarush.task.task14.task1419;

import java.io.*;
import java.util.*;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {
        try {
            float i = 1 / 0;
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            Object x = new Integer(0);
            System.out.println((String) x);
        } catch (ClassCastException e) {
            exceptions.add(e);
        }

        try {
            String x = "sss";
            System.out.println(x.charAt(4));
        } catch (StringIndexOutOfBoundsException e) {
            exceptions.add(e);
        }

        try {
            Object x[] = new String[3];
            x[0] = new Integer(0);
        } catch (ArrayStoreException e) {
            exceptions.add(e);
        }

        try {
            Object x[] = new String[3];
            System.out.println(x[4]);
        } catch (IndexOutOfBoundsException e) {
            exceptions.add(e);
        }

        try {
            String x = null;
            x.charAt(4);
        } catch (NullPointerException e) {
            exceptions.add(e);
        }

        try {
            Object x[] = new String[-3];
        } catch (NegativeArraySizeException e) {
            exceptions.add(e);
        }

        try {
            int x = Integer.parseInt("gggg");
        } catch (NumberFormatException e) {
            exceptions.add(e);
        }

        try {
            File file = new File("E://file.txt");
            FileReader fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            exceptions.add(e);
        }

        try {
            Set exampleleSet = new HashSet();
            Hashtable exampleTable = new Hashtable();
            exampleleSet.iterator().next();
            exampleTable.elements().nextElement();
        } catch (NoSuchElementException e) {
            exceptions.add(e);
        }
    }
}
