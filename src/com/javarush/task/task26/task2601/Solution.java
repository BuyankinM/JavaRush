package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);

        int median;
        int len = array.length;
        if (len % 2 == 0) median = (array[len / 2] + array[len / 2 - 1]) / 2;
        else median = array[len / 2];

        Comparator<Integer> compMedian = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int d1 = Math.abs(o1 - median);
                int d2 = Math.abs(o2 - median);

                int res = d1 - d2;
                if (res == 0) res = o1 - o2;
                return res;
            }
        };
        Arrays.sort(array, compMedian);

        return array;
    }
}
