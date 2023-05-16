package com.javarush.task.jdk13.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/* 
Самое большое число
*/

public class Solution {

    private static ArrayList<Integer> integers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Reader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        for (int i = 0; i < 5; i++) {
            integers.add(Integer.parseInt(bufferedReader.readLine()));
        }
        int max_val = integers.stream().mapToInt(v->v).max().orElseThrow(Exception::new);
        System.out.println(max_val);
    }
}
