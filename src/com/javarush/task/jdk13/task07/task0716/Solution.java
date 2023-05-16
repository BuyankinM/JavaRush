package com.javarush.task.jdk13.task07.task0716;

import java.util.ArrayList;

/* 
Р или Л
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("роза");
        strings.add("лоза");
        strings.add("лира");
        strings = fix(strings);

        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> strings) {
        ArrayList<String> arrayList = new ArrayList<>(strings.size());
        for (String s : strings) {
            boolean hasR = s.indexOf('р') > -1;
            boolean hasL = s.indexOf('л') > -1;
            if (hasL && hasR || !hasL && !hasR) {
                arrayList.add(s);
            } else if (hasL) {
                arrayList.add(s);
                arrayList.add(s);
            }
        }
        return arrayList;
    }
}