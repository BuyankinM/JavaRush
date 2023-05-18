package com.javarush.task.jdk13.task08.task0801;

import java.util.HashSet;
import java.util.Set;

/* 
Set из растений
*/

public class Solution {
    public static void main(String[] args) {
        Set<String> plants = new HashSet<>(10);
        plants.add("арбуз");
        plants.add("банан");
        plants.add("вишня");
        plants.add("груша");
        plants.add("дыня");
        plants.add("ежевика");
        plants.add("женьшень");
        plants.add("земляника");
        plants.add("ирис");
        plants.add("картофель");
        for (String s : plants) {
            System.out.println(s);
        }
    }
}
