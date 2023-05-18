package com.javarush.task.jdk13.task08.task0809;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/* 
Измерить сколько времени занимает 10 тысяч вставок для каждого списка
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getInsertTimeInMs(new ArrayList()));
        System.out.println(getInsertTimeInMs(new LinkedList()));
    }

    public static long getInsertTimeInMs(List list) {
        Date date1 = new Date();
        long t1 = date1.getTime();

        insert10000(list);

        Date date2 = new Date();
        long t2 = date2.getTime();

        return t2 - t1;
    }

    public static void insert10000(List list) {
        for (int i = 0; i < 10000; i++) {
            list.add(0, new Object());
        }
    }
}
