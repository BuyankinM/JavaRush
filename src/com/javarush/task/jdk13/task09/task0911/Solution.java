package com.javarush.task.jdk13.task09.task0911;

import java.util.HashMap;

/* 
Исключение при работе с коллекциями Map
*/

public class Solution {

    public static void main(String[] args) {
        try {
            HashMap<String, String> map = new HashMap<>(null);
            System.out.println(map);
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
    }
}
