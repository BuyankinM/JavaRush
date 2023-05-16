package com.javarush.task.jdk13.task07.task0707;

import java.util.ArrayList;

/* 
5 различных строк в списке
*/

public class Solution {

    public static ArrayList<String> list;

    public static void main(String[] args) {
        list = new ArrayList<String>();
        list.add(new String("One"));
        list.add(new String("Two"));
        list.add(new String("Three"));
        list.add(new String("Four"));
        list.add(new String("Five"));

        System.out.println(list.size());
        for (String s : list) {
            System.out.println(s);
        }
    }
}
