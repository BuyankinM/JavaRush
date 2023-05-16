package com.javarush.task.jdk13.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Три массива
*/

public class Solution {

    public static ArrayList<Integer> numbers = new ArrayList<>();
    public static ArrayList<Integer> divBy3 = new ArrayList<>();
    public static ArrayList<Integer> divBy2 = new ArrayList<>();
    public static ArrayList<Integer> others = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
        for (int i = 0; i < 20; i++) {
            int x = Integer.parseInt(bufferedReader.readLine());
            numbers.add(x);

            boolean div2 = (x % 2 == 0), div3 = (x % 3 == 0);

            if (div2) {
                divBy2.add(x);
            }
            if (div3) {
                divBy3.add(x);
            }
            if (!div2 && !div3) {
                others.add(x);
            }
        }
        printList(divBy3);
        printList(divBy2);
        printList(others);
    }

    public static void printList(ArrayList<Integer> list) {
        for (Integer x : list) {
            System.out.println(x);
        }
    }
}
