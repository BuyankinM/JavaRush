package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        HashMap<Character, Integer> counter = new HashMap<>();
        int i = 0;
        int start = 0;
        int maxLen = 0;
        char[] ca = s.toCharArray();

        while (i < ca.length) {
            if (!counter.containsKey(ca[i])) {
                counter.put(ca[i], i++);
            } else {
                maxLen = Math.max(maxLen, i - start);
                i = start = counter.get(ca[i]) + 1;
                counter.clear();
            }
        }
        return Math.max(maxLen, i - start);
    }
}
