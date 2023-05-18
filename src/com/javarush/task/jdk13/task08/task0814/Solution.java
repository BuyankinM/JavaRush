package com.javarush.task.jdk13.task08.task0814;

import java.util.HashSet;
import java.util.Set;

/* 
Больше 10? Вы нам не подходите
*/

public class Solution {
    public static Set<Integer> createSet() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        set.add(11);
        set.add(21);
        set.add(31);
        set.add(41);
        set.add(51);

        set.add(111);
        set.add(211);
        set.add(311);
        set.add(411);
        set.add(511);

        set.add(1111);
        set.add(2111);
        set.add(3111);
        set.add(4111);
        set.add(5111);

        return set;
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        set.removeIf(x -> x > 10);
    }

    public static void main(String[] args) {

    }
}
